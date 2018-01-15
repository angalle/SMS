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

import univ.sm.connect.api.CommonCallbak;
import univ.sm.data.item.Shuttle;

/**
 * Created by com01 on 2018. 1. 7..
 */

public class SchCallbakService implements CommonCallbak {

    ArrayList<JsonObject> arrShuttle = null;

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

        for (JsonElement value: jsonArray ) {
            Log.e("value:",value.toString());
        }

        Log.e("Array Size ::::::",jsonObject.get("Shuttle").getAsJsonArray().size()+"");
    }

    @Override
    public void onFailure(int code) {

    }

    public ArrayList<JsonObject> getArrShuttle() {
        return arrShuttle;
    }
}
