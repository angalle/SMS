package univ.sm;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.HashMap;

import univ.sm.connect.Connection;
import univ.sm.connect.api.schdule.SchCall;
import univ.sm.connect.api.schdule.SchCallbakService;
import univ.sm.connect.api.schdule.SchService;
import univ.sm.data.SplashData;

import static univ.sm.StaticData.positionShuttleArr;


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
    *   get schedule (file base)
    * */
    /*public static boolean DataSetting() {

        int i = 0;
        try{
            for (String url : SplashData.busUrl) {
                Connection getBusArray = new Connection(url);
                if (i != SplashData.busUrl.length - 1) {
                    Connection.positionShuttleArr[i] = getBusArray.HttpConnect();
                    positionShuttleArr[i] = getBusArray.HttpConnect();
                    //Connection.positionShuttleArr[i] = getBusArray.getBusArray();
                    if (Connection.positionShuttleArr[i].get(0).getNo() == null) {
                        break;
                    }
                } else {
                    SplashData.setNotice_con(getBusArray.HttpInfoConnect());
                }
                i++;
            }
        }catch (Exception e){
            //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }*/


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
        Log.e("SchCall ::::::", "call data");


        scheduleApi.getSchedule(params, schCallbakService);
    }


    /*
    *
    *   Animation function
    *
    * */

    /* 주말선택의 빨간바를 이동하는 함수 */
    public static void moveImageBar(View pivot,View target){
        /* 이동해야할 x좌표 */
        float toX       =   pivot.getLeft();
        float toWidth   =   pivot.getWidth();

        float width = target.getWidth();
        target.animate().scaleX(toWidth/width);
        target.animate().translationX(toX-(width-toWidth)/2.0f).withLayer();
    }
}
