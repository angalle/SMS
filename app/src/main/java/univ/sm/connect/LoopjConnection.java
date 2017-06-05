package univ.sm.connect;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import univ.sm.board.BoardManager;
import univ.sm.board.Post;

/**
 * board 통신부분
 * Created by soo jeong.
 */

public class LoopjConnection {
    private static LoopjConnection instance;

    //    private JSONObject result;
    private Object result;
    private Post postResult;
    private static Context mContext;

    public static LoopjConnection getInstance(Context context) {
        if (instance == null) {
            instance = new LoopjConnection();
        }
        mContext = context;
        return instance;
    }


    private AsyncHttpClient client = new AsyncHttpClient();
    private SyncHttpClient syncHttpClient = new SyncHttpClient();
    private static String boardUrl = "http://52.78.113.18:40000";
    private static String getBoardListUrl = "/selectcallvan";
    private static String addPostingUrl = "/insertcallvan";
    private static String getonepostUrl = "/selectcallvaninfo";
    private static String addCommentUrl = "/insertcallvancomment";
    private BoardManager boardManager;


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
    public void addPosting(RequestParams params,final TextView tv) {
        client.post(boardUrl + addPostingUrl, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i("LoopjConnect", "http://52.78.113.18:40000/insertcallvan , status : " + statusCode + ", responseBody : " + responseBody.toString() + ", //onSuccess");
                tv.performClick();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("LoopjConnect", "http://52.78.113.18:40000/insertcallvan , status : " + statusCode + ", //onFailure");
                tv.performClick();
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
                boardManager = new BoardManager(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
        return (JSONObject) result;
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
    public Post getOnePost(RequestParams postNo) {

        syncHttpClient.post(boardUrl + getonepostUrl, postNo, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("LoopjConnect", boardUrl + getonepostUrl + ", status : " + statusCode + ", //onSuccess");
                Log.e("LoopjConnect", "response  : " + response);

                postResult = BoardManager.json2PostWithComment(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i("LoopjConnect", boardUrl + getonepostUrl + " , status : " + statusCode + ", //onFailure");
                postResult = null;
            }
        });
        return postResult;
    }

    /*
    //PARAMETER
    var CALL_BOARD_NO              //해당글의 INDEX
    var COMMENT_LEVEL             // 댓글의 댓글레벨
    var CONTENTS                         // 내용
    var REG_ID                                // 댓글 남긴사람의 기기값
    var WRITE_NAME                      // 작성자이름
    var DEPARTMENT                      // 학과
    var BEFORE_COMMENT_NO    // 이전 댓글 INDEX - 이전 인덱스를 찾아서 레벨로 댓글의 댓글기능 구성
    var SEND_REG_ID    	             // 타겟이 될 사람의 기기값
    // 탑승 신청시 -  글쓴이의 기기값
    // 글쓴이가 답변남길시 - 해당 댓글남긴사람의 기기값.*/

    public void addComment(RequestParams params) {
        client.post(boardUrl + addCommentUrl, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("LoopjConnect", boardUrl + addCommentUrl + " , status : " + statusCode + ", response : "+response +", //onSuccess");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i("LoopjConnect", boardUrl + addCommentUrl + " , status : " + statusCode + " , errorResponse : " + errorResponse + " //onFailure");
            }
        });
    }
}
