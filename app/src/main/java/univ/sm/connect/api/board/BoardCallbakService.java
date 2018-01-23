package univ.sm.connect.api.board;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import univ.sm.CommonUtil;
import univ.sm.StaticData;
import univ.sm.connect.api.CommonCallbak;
import univ.sm.data.item.Shuttle;

/**
 * Created by com01 on 2018. 1. 7..
 */

public class BoardCallbakService implements CommonCallbak {


    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onSuccess(int code, Object receiveData) {
        JsonObject jsonObject = CommonUtil.cnvtJson2Obj(receiveData);
    }

    @Override
    public void onFailure(int code) {

    }


}
