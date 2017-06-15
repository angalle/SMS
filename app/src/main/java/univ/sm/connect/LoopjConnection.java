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
import univ.sm.board.BoardManager;
import univ.sm.board.Post;

/**
 * board 통신부분
 * Created by soo jeong.
 */

public class LoopjConnection {
    private static LoopjConnection instance;

    private Object result;
    private Post postResult;
    private static Context mContext;

    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private SyncHttpClient syncHttpClient = new SyncHttpClient();
    private static String boardUrl = "http://52.78.113.18:40000";
    private static String getBoardListUrl = "/selectcallvan";
    private static String addPostingUrl = "/insertcallvan";
    private static String getonepostUrl = "/selectcallvaninfo";
    private static String addCommentUrl = "/insertcallvancomment";
    private static String approvalCallvan = "/callvan_complete";

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
        asyncHttpClient.post(boardUrl + addPostingUrl, params, new AsyncHttpResponseHandler() {
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

        syncHttpClient.post(boardUrl + getBoardListUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("LoopjConnect", "http://52.78.113.18:40000/selectcallvan , status : " + statusCode + ", //onSuccess");
                Log.e("권수정", "result : " + response);
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



    /**
     * 댓글 등록
     *
     * @param params CALL_BOARD_NO, COMMENT_LEVEL, CONTENTS, REG_ID,
     *               WRITE_NAME, BEFORE_COMMENT_NO, SEND_REG_ID
     */

    public void addComment(RequestParams params) {
        syncHttpClient.post(boardUrl + addCommentUrl, params, new JsonHttpResponseHandler() {

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

    /**
     * 댓글 등록
     *
     * @param params CALL_BOARD_NO, COMMENT_LEVEL, CONTENTS, REG_ID,
     *               WRITE_NAME, BEFORE_COMMENT_NO, SEND_REG_ID
     */

    public void approvalCallvan(RequestParams params) {
        syncHttpClient.post(boardUrl + approvalCallvan, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("LoopjConnect", boardUrl + approvalCallvan + " , status : " + statusCode + ", response : "+response +", //onSuccess");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.i("LoopjConnect", boardUrl + approvalCallvan + " , status : " + statusCode + " , errorResponse : " + errorResponse + " //onFailure");
            }
        });
    }
}
