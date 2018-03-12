package univ.sm.Controller;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by heesun on 2017-12-06.
 */

public interface CommonService {

    @FormUrlEncoded
    @POST("/posts")
    Call<JSONObject> getSchedule(@FieldMap HashMap<String,Object> parameters);
}
