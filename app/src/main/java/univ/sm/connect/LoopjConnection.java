package univ.sm.connect;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import univ.sm.data.Board;
import univ.sm.data.Const;

/**
 * board 통신부분
 * Created by soo jeong.
 */

/**
 * 공통부분을 수정할때 기능을 기준으로 메소드를 증가시키면 한 클래스에 많은 메소드가 생기게된다.
 * 그러면 어떻게 메소드를 안생기게 할까...?
 *
 * 크게 디비에 동작하는건 4가지이다. CRUD //
 * 이 4가지메소드를 만들어 놓고 파라미터로 URL과 PARAM을 받으면 공통소스는 늘어나지 않게 된다.
 * 그래서 사용할때 CONST 공통을 만들어 놓고 사용하면 URL수정시에도 간편하게 모든 URL을 수정 할 수 있다.
 */

public class LoopjConnection {
    private static LoopjConnection instance;

    private Object result;
    private Board postResult;
    private static Context mContext;

    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private SyncHttpClient syncHttpClient = new SyncHttpClient();    


    public static LoopjConnection getInstance(Context context) {
        if (instance == null) {
            instance = new LoopjConnection();
        }
        mContext = context;
        return instance;
    }

    /**
     * 콜벤 게시물 추가하기
     * params : WRITE_NAME 작성자, PASSWD 글 비밀번호, DEPARTMENT 학과, STUDENT_NO 학번,
     * DEPARTURE 출발지, DEPARTURE_DETAIL 출발지 설명, DESTINATION 목적지, DESTINATION_DETAIL 목적지 설명,
     * REG_ID gcm 기기 값, PASSENGER_NUM 총 탑승인원, WAIT_TIME 대기시간
     *
     * @param params
     */
    public void addPosting(RequestParams params,final TextView tv) {
        asyncHttpClient.post(Const.BOARD_URL + Const.INSERT_CALLVAN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
            }

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

        syncHttpClient.post(Const.BOARD_URL + Const.SELECT_CALLVAN, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("LoopjConnect", "http://52.78.113.18:40000/selectcallvan , status : " + statusCode + ", //onSuccess");
                result = response;
                new BoardManager(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                result = null;
            }
        });
        return (JSONObject) result;
    }


    /**
     * 게시글 한개 불러오기
     * 게시판 + 댓글 포함
     *
     * @param postNo (CALL_BOARD_NO : ex. ANONY2017041800042)
     * @return
     */
    public Board getOnePost(RequestParams postNo) {

        syncHttpClient.post(Const.BOARD_URL + Const.SELECT_ONE_CALLVAN, postNo, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("LoopjConnect", Const.BOARD_URL + Const.SELECT_ONE_CALLVAN + ", status : " + statusCode + ", //onSuccess");

                postResult = BoardManager.json2PostWithComment(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i("LoopjConnect", Const.BOARD_URL + Const.SELECT_ONE_CALLVAN + " , status : " + statusCode + ", //onFailure");
                postResult = null;
            }
        });
        return postResult;
    }



    /**
     * 댓글 등록
     *
     * @param params CALL_BOARD_NO, COMMENT_LEVEL, CONTENTS, REG_ID,
     *               WRITE_NAME, BEFORE_COMMENT_NO, SEND_REG_ID
     */

    public void addComment(RequestParams params) {
        syncHttpClient.post(Const.BOARD_URL + Const.INSERT_CALLVAN_COMMENT, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("LoopjConnect", Const.BOARD_URL + Const.INSERT_CALLVAN_COMMENT + " , status : " + statusCode + ", response : "+response +", //onSuccess");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i("LoopjConnect", Const.BOARD_URL + Const.INSERT_CALLVAN_COMMENT + " , status : " + statusCode + " , errorResponse : " + errorResponse + " //onFailure");
            }
        });
    }

    /**
     * 댓글 등록
     *
     * @param params CALL_BOARD_NO, COMMENT_LEVEL, CONTENTS, REG_ID,
     *               WRITE_NAME, BEFORE_COMMENT_NO, SEND_REG_ID
     */

    public void approvalCallvan(RequestParams params) {
        syncHttpClient.post(Const.BOARD_URL + Const.APPROAVAL_CALLVAN, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("LoopjConnect", Const.BOARD_URL + Const.APPROAVAL_CALLVAN + " , status : " + statusCode + ", response : "+response +", //onSuccess");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i("LoopjConnect", Const.BOARD_URL + Const.APPROAVAL_CALLVAN + " , status : " + statusCode + " , errorResponse : " + errorResponse + " //onFailure");
            }
        });
    }
}
