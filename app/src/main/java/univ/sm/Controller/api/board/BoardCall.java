package univ.sm.Controller.api.board;

import com.google.gson.JsonObject;
import com.loopj.android.http.RequestParams;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import univ.sm.Model.Const;
import univ.sm.Model.User;

/**
 * Created by com01 on 2018. 1. 7..
 */

public interface BoardCall {
    /* BASE URL */
    final String BaseURL = Const.BASE_URL;/* 40002 */

    @FormUrlEncoded
    @POST("/insertcallvan")
    Call<JsonObject> addPosting(@FieldMap RequestParams postNo);

    @FormUrlEncoded
    @POST("/selectcallvan")
    Call<JsonObject> getBoardList();

    @FormUrlEncoded
    @POST("/selectcallvaninfo")
    Call<JsonObject> getOnePost(@FieldMap RequestParams postNo);

    @FormUrlEncoded
    @POST("/insertcallvancomment")
    Call<JsonObject> addComment(@FieldMap RequestParams postNo);

    @FormUrlEncoded
    @POST("/callvan_complete")
    Call<JsonObject> approvalCallvan(@FieldMap RequestParams postNo);

    @FormUrlEncoded
    @POST("/user")
    Call<JsonObject> insertUser(@FieldMap HashMap<String,Object> postNo);

    @FormUrlEncoded
    @POST("/checking/email")
    Call<JsonObject> checkEmail(@FieldMap HashMap<String,Object> postNo);

    @FormUrlEncoded
    @POST("/login/user")
    Call<JsonObject> loginUser(@FieldMap HashMap<String,Object> postNo);

}
