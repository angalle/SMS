package univ.sm.connect;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import univ.sm.board.Board;

/**
 * board 통신부분
 * Created by soo jeong.
 */

public class LoopjConnection {
    private static LoopjConnection instance;
    public static LoopjConnection getInstance() {
        if(instance == null){
            instance = new LoopjConnection();
        }
        return instance;
    }

    private AsyncHttpClient client = new AsyncHttpClient();
    private SyncHttpClient syncHttpClient = new SyncHttpClient();
    private static String url = "http://52.78.113.18:40000";
    private static String getBoardListUrl = "/selectcallvan";
    private static String addPostingUrl = "/insertcallvan";
    ArrayList<Board> boardArrayList = new ArrayList<>();


    /**
     * 콜벤 게시판 목록 호출하기
     *
     * @return
     */
    public  ArrayList<Board> getBoardList() {

        syncHttpClient.post(url+getBoardListUrl, new JsonHttpResponseHandler(){
          @Override
          public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
              super.onSuccess(statusCode, headers, response);
              Log.i("LoopjConnect", "http://52.78.113.18:40000/selectcallvan , status : " + statusCode + ", //onSuccess");
              Log.e("권수정", "result : " + response );
              JSONObject jsonObject = response;
              try {
                  JSONArray callvanArray= jsonObject.getJSONArray("callvan");
                  Log.e("권수정", "callvanArray : " + callvanArray );
                  for(int i = 0; i < callvanArray.length(); i ++){
                      JSONObject jsonBaord = callvanArray.getJSONObject(i);
                      Log.e("권수정", "jsonBaord : " + jsonBaord );
                      Log.e("권수정", "jsonBaord.getString(\"CALL_BOARD_NO\") : " + jsonBaord.getString("CALL_BOARD_NO") );

                       Board board = new Board(jsonBaord.getString("CALL_BOARD_NO"), jsonBaord.getString("WRITE_NAME"),
                              jsonBaord.getString("PASSWD"),  jsonBaord.getString("DEPARTMENT"),
                              jsonBaord.getString("STUDENT_NO"),  jsonBaord.getString("DEPARTURE"),
                              jsonBaord.getString("DEPARTURE_DETAIL"),  jsonBaord.getString("DESTINATION"),
                              jsonBaord.getString("DESTINATION_DETAIL"),  jsonBaord.getString("REG_ID"),
                              jsonBaord.getString("USE_FLAG"),  jsonBaord.getString("PASSENGER_NUM"),
                              jsonBaord.getString("WAIT_TIME"), jsonBaord.getString("INSERT_TIME"), jsonBaord.getString("INSERT_DATE") );

                      boardArrayList.add(board);
                  }
              } catch (JSONException e) {
                  e.printStackTrace();
              }

          }

          @Override
          public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
              super.onFailure(statusCode, headers, throwable, errorResponse);
          }
      });
        return boardArrayList;
    }

    /*RequestParams params = new RequestParams();
    params.put("key", "value");
    params.put("more", "data");*/

    /*WRITE_NAME			//작성자
PASSWD				//글 비밀번호
DEPARTMENT			//학과
STUDENT_NO			//학번
DEPARTURE			//출발지
DEPARTURE_DETAIL	//출발지 설명
DESTINATION			//목적지
DESTINATION_DETAIL	//목적지 설명
REG_ID				//gcm 기기 값
PASSENGER_NUM		//총 탑승인원
WAIT_TIME			//대기시간*/

    public void addPosting(RequestParams params) {
        client.post(url+ addPostingUrl, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i("LoopjConnect", "http://52.78.113.18:40000/insertcallvan , status : " + statusCode + ", responseBody : "+responseBody.toString()+", //onSuccess");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i("LoopjConnect", "http://52.78.113.18:40000/insertcallvan , status : " + statusCode + ", //onFailure");
            }
        });
    }
}
