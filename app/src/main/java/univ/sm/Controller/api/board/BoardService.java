package univ.sm.Controller.api.board;

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
import univ.sm.Controller.CommonCallbak;
import univ.sm.Model.Const;
import univ.sm.Model.User;

/**
 * Created by heesun on 2017-12-06.
 */

public class BoardService {

    static BoardCall boardApi;
    static Context mContext;
    private static Retrofit retrofit;

    private static class SingletonHolder{
        private static BoardService INSTANCE = new BoardService();
    }

    public static BoardService getInstance(Context context){
        if(context != null){
            mContext = context;
        }

        return SingletonHolder.INSTANCE;
    }

    private BoardService(){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Const.BASE_URL)
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
    public void addBoard(HashMap<String,Object> params, final CommonCallbak callback){
        Log.e("SchCall1 ::::::","call data");
        params.put("test","empty");
        try{
            boardApi.addBoard(params).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.e("ok reponse","yet");
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
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 콜벤 게시판 목록 호출하기
     * 댓글은 불포함
     *
     * @return JSONObject
     */
    public void getBoardList(HashMap<String,Object> parameters, final CommonCallbak callback){
        Log.e("SchCall1 ::::::","call data");
        parameters.put("test","empty");
        boardApi.getBoardList(parameters).enqueue(new Callback<JsonObject>() {
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
     * @param params (CALL_BOARD_NO : ex. ANONY2017041800042)
     * @return
     */
    public void getOneBoard(HashMap<String,Object> params, final CommonCallbak callback){
        Log.e("SchCall1 ::::::","call data");
        boardApi.getOneBoard(params).enqueue(new Callback<JsonObject>() {
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



    /**
     * 유저 가입
     *
     * @param map
     */
    public void insertUser(HashMap<String,Object> map, final CommonCallbak callback){
        Log.e("insertUser","user");
        boardApi.insertUser(map).enqueue(new Callback<JsonObject>() {
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
     * 이메일 체크
     *
     * @param map
     */
    public void checkEmail(HashMap<String,Object> map, final CommonCallbak callback){
        Log.e("checkEmail","checkEmail");
        boardApi.checkEmail(map).enqueue(new Callback<JsonObject>() {
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
     * 이메일 체크
     *
     * @param map
     */
    public void loginUser(HashMap<String,Object> map, final CommonCallbak callback){
        Log.e("loginUser","loginUser");
        boardApi.loginUser(map).enqueue(new Callback<JsonObject>() {
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
