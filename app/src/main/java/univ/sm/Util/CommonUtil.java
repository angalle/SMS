package univ.sm.Util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;

import univ.sm.StaticData;
import univ.sm.Controller.api.schdule.SchCall;
import univ.sm.Controller.api.schdule.SchCallbakService;
import univ.sm.Controller.api.schdule.SchService;


/**
 * Created by PE_LHS on 2018-01-18.
 */

public class CommonUtil {

    /**
     * Google Play Service를 사용할 수 있는 환경이지를 체크한다.
     */
    public static boolean checkPlayServices(Context context) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, (Activity) context,9000).show();
            } else {
                Log.i("test", "This device is not supported.");
            }
            return false;
        }
        return true;
    }

    /*
    *
    *   db select data (base retrofit2)
    *
    * */
    static private SchService scheduleApi;
    static SchCall schCallBack;
    static private HashMap<String,Object> params;
    static SchCallbakService schCallbakService = null;

    public static void DataSetting(Context context) {
        scheduleApi = SchService.getInstance(context).createApi();
        schCallbakService = new SchCallbakService();

        params = new HashMap<String, Object>();
        Log.e("DataSetting ::::::", "call data");


        scheduleApi.getSchedule(params, schCallbakService);

        Log.e("DataSetting ::::::", StaticData.arrShuttle.size() + ":::");
    }

    public static JsonObject cnvtJson2Obj(Object obj){
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return  (JsonObject) gson.toJsonTree(obj);
    }
}





















