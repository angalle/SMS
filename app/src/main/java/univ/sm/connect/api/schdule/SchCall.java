package univ.sm.connect.api.schdule;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import univ.sm.data.Const;
import univ.sm.data.item.Shuttle;

/**
 * Created by com01 on 2018. 1. 7..
 */

public interface SchCall {
    /* BASE URL */
    final String BaseURL = Const.BASE_URL;

    @FormUrlEncoded
    @POST("/get_schdule")
    Call<JsonObject> get_schdule_p(@FieldMap HashMap<String,Object> parameters);
}
