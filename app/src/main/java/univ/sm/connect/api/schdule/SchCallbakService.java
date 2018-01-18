package univ.sm.connect.api.schdule;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import univ.sm.StaticData;
import univ.sm.connect.api.CommonCallbak;
import univ.sm.data.item.Shuttle;

/**
 * Created by com01 on 2018. 1. 7..
 */

public class SchCallbakService implements CommonCallbak {


    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onSuccess(int code, Object receiveData) {
        Log.e("Array ::::::",receiveData.toString());
        Gson gson = new Gson();
        String json = gson.toJson(receiveData);
        JsonObject jsonObject = (JsonObject) gson.toJsonTree(receiveData);
        JsonArray jsonArray = (JsonArray) jsonObject.get("Shuttle").getAsJsonArray();

        ArrayList<Shuttle> temp = new ArrayList<Shuttle>();

        for (JsonElement value: jsonArray ) {
            Shuttle shuttle = new Shuttle();
            Log.e("value:",value.toString());

            shuttle.setUNIV_CODE((      (JsonObject)value).get("UNIV_CODE").getAsString());
            shuttle.setSTOP_SITE_CODE(( (JsonObject)value).get("STOP_SITE_CODE").getAsString());
            shuttle.setWEEKDAY_CODE((   (JsonObject)value).get("WEEKDAY_CODE").getAsString());
            shuttle.setVACATION_FLAG((  (JsonObject)value).get("VACATION_FLAG").getAsString());

            shuttle.setST_ONE((         (JsonObject)value).get("ST_ONE").getAsString());
            shuttle.setST_TWO((         (JsonObject)value).get("ST_TWO").getAsString());
            shuttle.setST_TRE((         (JsonObject)value).get("ST_TRE").getAsString());
            shuttle.setST_FOR((         (JsonObject)value).get("ST_FOR").getAsString());
            shuttle.setST_FIV((         (JsonObject)value).get("ST_FIV").getAsString());
            shuttle.setINSERT_DATE((    (JsonObject)value).get("INSERT_DATE").getAsString());

            temp.add(shuttle);
        }

        StaticData.arrShuttle = temp;

        Log.e("Array Size ::::::",jsonObject.get("Shuttle").getAsJsonArray().size()+"");
    }

    @Override
    public void onFailure(int code) {

    }


}
