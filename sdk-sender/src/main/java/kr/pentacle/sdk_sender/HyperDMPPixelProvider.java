package kr.pentacle.sdk_sender;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tagmanager.CustomTagProvider;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HyperDMPPixelProvider implements CustomTagProvider {

    static String HYPER_DMP_PIXEL_URL = "https://test-pixel-hyper-dmp-v2.pentacle.co.kr/";

    static String HYPER_DMP_LOG_NAME = "HyperDMPLogger";
    static String HYPER_DMP_LOG_TYPE_ERROR = "ERROR: \n";
    static String HYPER_DMP_TRIGGER_NAME = "HyperDMP_PixelTrigger";

    protected static Context globalContext = null;
    protected static String kakaoAdTrackId = null;

    private static HyperDMPPixelProvider provider = null;

    public HyperDMPPixelProvider(){
        Log.i(HYPER_DMP_LOG_NAME, "HyperDMPPixelProvider no-arg context Initialization!!!");
    }

    public HyperDMPPixelProvider(Context context){
        Log.i(HYPER_DMP_LOG_NAME, "HyperDMPPixelProvider no-kakao context Initialization!!!");
        globalContext = context;
    }

    public HyperDMPPixelProvider(Context context, String kakaoAdTrackId){
        Log.i(HYPER_DMP_LOG_NAME, "HyperDMPPixelProvider all sdk context Initialization!!!");
        this.globalContext = context;
        this.kakaoAdTrackId = kakaoAdTrackId;
    }

    public static void initialization(Context context, String kakaoAdTrackId){
        Log.i(HYPER_DMP_LOG_NAME, "HyperDMPPixelProvider all sdk context Initialization!!!");
        if(provider == null){
            provider = new HyperDMPPixelProvider(context, kakaoAdTrackId);
        }
    }

    @Override
    public void execute(@NonNull Map<String, Object> map) {
        String epQueryString = "";
        String dynamicQueryString = "";
        String containerId = "";

        if(HyperDMPUtils.getConnectivityStatus() == HyperDMPUtils.TYPE_NOT_CONNECTED){
            return ;
        }

        try {
            containerId = HyperDMPUtils.getContainerId(map);
            epQueryString = HyperDMPUtils.getEPQueryString(map);
            dynamicQueryString = HyperDMPUtils.getEPQueryString(map);

            if(epQueryString.equals("") && dynamicQueryString.equals("")){
               throw new Exception("Bad Parameter Exception");
            }

            String page = HYPER_DMP_PIXEL_URL + containerId + "?ret=json&" + epQueryString + dynamicQueryString;
            URL url = new URL(page);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            // 결과값 저장 문자열
            final StringBuilder sb = new StringBuilder();
            if(conn != null) {
                conn.setRequestMethod("GET");
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    try(BufferedReader br = new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), "utf-8"));
                        ){
                            String line;
                            while ((line = br.readLine()) != null) {
                                sb.append(line);
                            }
                    }catch (Exception e){
                        Log.w(HYPER_DMP_LOG_NAME, "STREAM ERROR::"+e);
                        return ;
                    }
                    String response = sb.toString();

                    HyperDMPJsonResponse jsonResponse = new HyperDMPJsonResponse(response);
                    List<Bundle> parameters = jsonResponse.getAllParameters();

                    for(Bundle params : parameters){
                        FirebaseAnalytics firebaseAnalyticsEventSender = FirebaseAnalytics.getInstance(globalContext);
                        firebaseAnalyticsEventSender.logEvent(HYPER_DMP_TRIGGER_NAME, params);
                    }
                }
                // 연결 끊기
                conn.disconnect();
            }
            Log.i(HYPER_DMP_LOG_NAME, "end");
        }catch (Exception e) {
            Log.i(HYPER_DMP_LOG_NAME, "ERROR::"+e);
        }
    }

}
