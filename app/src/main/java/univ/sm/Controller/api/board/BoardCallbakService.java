package univ.sm.Controller.api.board;

import com.google.gson.JsonObject;

import univ.sm.Util.CommonUtil;
import univ.sm.Controller.CommonCallbak;

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
