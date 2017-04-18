package univ.sm.connect;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import univ.sm.board.BoardManager;

/**
 * board 통신부분
 * Created by soo jeong.
 */

public class LoopjConnection {
    private static LoopjConnection instance;
    private JSONObject result;

    public static LoopjConnection getInstance() {
        if (instance == null) {
            instance = new LoopjConnection();
        }
        return instance;
    }

    private AsyncHttpClient client = new AsyncHttpClient();
    private SyncHttpClient syncHttpClient = new SyncHttpClient();
    private static String boardUrl = "http://52.78.113.18:40000";
    private static String getBoardListUrl = "/selectcallvan";
    private static String addPostingUrl = "/insertcallvan";
    private static String getonepostUrl = "/selectcallvaninfo'";



    /*RequestParams params = new RequestParams();
    params.put("key", "value");
    params.put("more", "data");*/


    /**
     * 콜벤 게시물 추가하기
     * params : WRITE_NAME 작성자, PASSWD 글 비밀번호, DEPARTMENT 학과, STUDENT_NO 학번,
     * DEPARTURE 출발지, DEPARTURE_DETAIL 출발지 설명, DESTINATION 목적지, DESTINATION_DETAIL 목적지 설명,
     * REG_ID gcm 기기 값, PASSENGER_NUM 총 탑승인원, WAIT_TIME 대기시간
     *
     * @param params
     */
    public void addPosting(RequestParams params) {
        client.post(boardUrl + addPostingUrl, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i("LoopjConnect", "http://52.78.113.18:40000/insertcallvan , status : " + statusCode + ", responseBody : " + responseBody.toString() + ", //onSuccess");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("LoopjConnect", "http://52.78.113.18:40000/insertcallvan , status : " + statusCode + ", //onFailure");
            }
        });
    }

    /**
     * 콜벤 게시판 목록 호출하기
     * 댓글은 불포함
     *
     * @return JSONObject
     */
    public JSONObject getBoardList() {

        syncHttpClient.post(boardUrl + getBoardListUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("LoopjConnect", "http://52.78.113.18:40000/selectcallvan , status : " + statusCode + ", //onSuccess");
                Log.e("권수정", "result : " + response);
                result = response;
                BoardManager boardManager = new BoardManager(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
        return result;
    }
    /*{
  "CALLVAN_INFO": [
    {
      "CALL_BOARD_NO": "ANONY2017041800042",
      "WRITE_NAME": "ㅇㄹ",
      "PASSWD": "11",
      "DEPARTMENT": "ㅇㅇ",
      "STUDENT_NO": "ㅇㄷㅇ",
      "DEPARTURE": "ㅇㅇ",
      "DEPARTURE_DETAIL": "ㅇㅇ",
      "DESTINATION": "ㅇㅇ",
      "DESTINATION_DETAIL": "ㅇㅇ",
      "REG_ID": "undefined",
      "USE_FLAG": "Y",
      "PASSENGER_NUM": "ㅇ",
      "WAIT_TIME": "062833",
      "INSERT_TIME": "061333",
      "INSERT_DATE": "20170418"
    }
  ],
  "COMMENTS": []
}*/

    /*RequestParams params = new RequestParams();
    params.put("CALL_BOARD_NO", "ANONY2017041800042");*/
    /**
     * 게시글 한개 불러오기
     * 게시판 + 댓글 포함
     *
     * @param postNo (CALL_BOARD_NO : ex. ANONY2017041800042)
     * @return
     */
    public String getonePost(RequestParams postNo) {
        String result = "";
        client.post(boardUrl + getonepostUrl, postNo, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("LoopjConnect", "http://52.78.113.18:40000/selectcallvaninfo , status : " + statusCode + ", //onSuccess");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i("LoopjConnect", "http://52.78.113.18:40000/selectcallvaninfo , status : " + statusCode + ", //onFailure");
            }
        });
        return result;
    }
}
