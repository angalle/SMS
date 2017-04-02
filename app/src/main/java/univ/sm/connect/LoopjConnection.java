package univ.sm.connect;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * board 통신부분
 * Created by soo jeong.
 */

public class LoopjConnection {
    private AsyncHttpClient client = new AsyncHttpClient();
    private static String url = "http://52.78.113.18:40000";
    private static String getBoardListUrl = "/selectcallvan";
    private static String addPostingUrl = "/insertcallvan";

    /**
     * 콜벤 게시판 목록 호출하기
     *
     * @return
     */
    public String getBoardList() {

        String result = "";
        client.post(url + getBoardListUrl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.i("LoopjConnect", "http://52.78.113.18:40000/selectcallvan , status : " + statusCode + ", //onSuccess");
                Log.e("권수정", "result : " + responseBody.toString());
                //todo json 파싱, board 객체 생성하기
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("Loopj", "http://52.78.113.18:40000/selectcallvan , status : " + statusCode + "//onFailure");

            }
        });
        return result;
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
