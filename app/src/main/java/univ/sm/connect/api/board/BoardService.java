package univ.sm.connect.api.board;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import univ.sm.connect.api.CommonCallbak;
import univ.sm.connect.api.schdule.SchCall;

/**
 * Created by heesun on 2017-12-06.
 */

public class BoardService {

    static BoardCall boardApi;
    static Context mContext;
    static String baseUrl = SchCall.BaseURL;
    private static Retrofit retrofit;

    private static class SingletonHolder{
        private static BoardService INSTANCE = new BoardService(mContext);
    }

    public static BoardService getInstance(Context context){
        if(context != null){
            mContext = context;
        }

        return SingletonHolder.INSTANCE;
    }

    private BoardService(Context context){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public BoardService createApi(){
        boardApi = create(BoardCall.class);
        return this;
    }

    public <T> T create(final Class<T> service){
        if(service == null){
            throw new RuntimeException("Api is nul:::::::::");
        }
        return retrofit.create(service);
    }


    /**
     * 콜벤 게시물 추가하기
     * params : WRITE_NAME 작성자, PASSWD 글 비밀번호, DEPARTMENT 학과, STUDENT_NO 학번,
     * DEPARTURE 출발지, DEPARTURE_DETAIL 출발지 설명, DESTINATION 목적지, DESTINATION_DETAIL 목적지 설명,
     * REG_ID gcm 기기 값, PASSENGER_NUM 총 탑승인원, WAIT_TIME 대기시간
     *
     * @param params
     */
    public void addPosting(RequestParams params, final CommonCallbak callback){
        Log.e("SchCall1 ::::::","call data");
        boardApi.addPosting(params).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    Log.e("success ::::::",response.toString()+":::success");
                    callback.onSuccess(response.code(),response.body());
                }else{
                    Log.e("error ::::::",response.code()+":::error");
                    callback.onFailure(response.code());
                    Toast.makeText(mContext,response.code()+":::error",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    /**
     * 콜벤 게시판 목록 호출하기
     * 댓글은 불포함
     *
     * @return JSONObject
     */
    public void getBoardList(HashMap<String,Object> parameters, final CommonCallbak callback){
        Log.e("SchCall1 ::::::","call data");
        boardApi.getBoardList().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    Log.e("success ::::::",response.toString()+":::success");
                    callback.onSuccess(response.code(),response.body());
                }else{
                    Log.e("error ::::::",response.code()+":::error");
                    callback.onFailure(response.code());
                    Toast.makeText(mContext,response.code()+":::error",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    /**
     * 게시글 한개 불러오기
     * 게시판 + 댓글 포함
     *
     * @param postNo (CALL_BOARD_NO : ex. ANONY2017041800042)
     * @return
     */
    public void getOnePost(RequestParams postNo, final CommonCallbak callback){
        Log.e("SchCall1 ::::::","call data");
        boardApi.getOnePost(postNo).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    Log.e("success ::::::",response.toString()+":::success");
                    callback.onSuccess(response.code(),response.body());
                }else{
                    Log.e("error ::::::",response.code()+":::error");
                    callback.onFailure(response.code());
                    Toast.makeText(mContext,response.code()+":::error",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    /**
     * 댓글 등록
     *
     * @param params CALL_BOARD_NO, COMMENT_LEVEL, CONTENTS, REG_ID,
     *               WRITE_NAME, BEFORE_COMMENT_NO, SEND_REG_ID
     */
    public void addComment(RequestParams params, final CommonCallbak callback){
        Log.e("SchCall1 ::::::","call data");
        boardApi.addComment(params).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    Log.e("success ::::::",response.toString()+":::success");
                    callback.onSuccess(response.code(),response.body());
                }else{
                    Log.e("error ::::::",response.code()+":::error");
                    callback.onFailure(response.code());
                    Toast.makeText(mContext,response.code()+":::error",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError(t);
            }
        });
    }


    /**
     * 댓글 등록
     *
     * @param params CALL_BOARD_NO, COMMENT_LEVEL, CONTENTS, REG_ID,
     *               WRITE_NAME, BEFORE_COMMENT_NO, SEND_REG_ID
     */
    public void approvalCallvan(RequestParams params, final CommonCallbak callback){
        Log.e("SchCall1 ::::::","call data");
        boardApi.approvalCallvan(params).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    Log.e("success ::::::",response.toString()+":::success");
                    callback.onSuccess(response.code(),response.body());
                }else{
                    Log.e("error ::::::",response.code()+":::error");
                    callback.onFailure(response.code());
                    Toast.makeText(mContext,response.code()+":::error",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
