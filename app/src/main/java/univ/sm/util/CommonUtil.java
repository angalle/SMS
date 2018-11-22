package univ.sm.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import univ.sm.model.Const;
import univ.sm.controller.api.schdule.SchCall;
import univ.sm.controller.api.schdule.SchCallbakService;
import univ.sm.controller.api.schdule.SchService;

import static android.content.Context.MODE_PRIVATE;
import static com.kakao.util.helper.Utility.getPackageInfo;


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

        scheduleApi.getSchedule(params, schCallbakService);
    }

    public static JsonObject cnvtJson2Obj(Object obj){
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return  (JsonObject) gson.toJsonTree(obj);
    }


    /** get hash key */
    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                Log.w("test", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }

    /** implmetation RegistrationService
     * how to get registration id , not allow main thread */
    public String getRegistrationId(Context context) throws  Exception{
        ContextWrapper cw = new ContextWrapper(context);
        SharedPreferences sp = cw.getSharedPreferences(Const.SHARED_GCM, MODE_PRIVATE);
        //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String token = sp.getString(Const.SHARED_REG_ID,"");

        Log.i("GCM & Reg Id ::::: ", "GCM Registration Token: " + token);
        return token;
    }

    public static void nextPage(Intent intentInfo,Activity activity){
        /** 추가 정보를 받아야 함. 새로운 뷰를 제공해야함.*/
        Intent intent = intentInfo;
        activity.startActivity(intent);
        activity.finish();
    }

}





















