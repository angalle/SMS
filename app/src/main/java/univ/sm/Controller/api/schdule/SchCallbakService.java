package univ.sm.Controller.api.schdule;

import java.util.ArrayList;

import univ.sm.StaticData;
import univ.sm.Controller.CommonCallbak;
import univ.sm.Model.shuttle.Shuttle;

/**
 * Created by com01 on 2018. 1. 7..
 */

public class SchCallbakService implements CommonCallbak {


    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onSuccess(int code, Object receiveData) {
        StaticData.arrShuttle = (ArrayList<Shuttle>) receiveData;
    }

    @Override
    public void onFailure(int code) {

    }


}
