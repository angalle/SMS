package kr.pentacle.sdk_sender;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.StringJoiner;

public class HyperDMPUtils {

    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 3;

    protected static int getConnectivityStatus(){ //해당 context의 서비스를 사용하기위해서 context객체를 받는다.
        ConnectivityManager manager = (ConnectivityManager) HyperDMPPixelProvider.globalContext.getSystemService(HyperDMPPixelProvider.globalContext.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null){
            int type = networkInfo.getType();
            if(type == ConnectivityManager.TYPE_MOBILE){//쓰리지나 LTE로 연결된것(모바일을 뜻한다.)
                return TYPE_MOBILE;
            }else if(type == ConnectivityManager.TYPE_WIFI){//와이파이 연결된것
                return TYPE_WIFI;
            }
        }
        return TYPE_NOT_CONNECTED;  //연결이 되지않은 상태
    }

    protected static String urlEncodeUTF8(Object s) {
        try {

            if(s instanceof Float || s instanceof Double){
                return new DecimalFormat("#.##########").format(s);
            }

            return URLEncoder.encode(s.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    protected static String getContainerId(Map<String, Object> map) throws Exception {
        try{
            // containerId type is Double
            Object containerId = map.get("hyper_dmp_container_id");

            return new DecimalFormat("#").format(containerId);
        }catch(Exception e){
            Log.i(HyperDMPPixelProvider.HYPER_DMP_LOG_NAME, HyperDMPPixelProvider.HYPER_DMP_LOG_TYPE_ERROR+e);
            throw new Exception("getContainerId:: No Supply Container ID Key");
        }
    }

    protected static String getEPQueryString(Map<String, Object> map) throws Exception {
        try{

            StringBuilder epQueryString = new StringBuilder();
            Iterator<String> keys = map.keySet().iterator();
            while(keys.hasNext()){
                String key = keys.next();
                Object value = map.get(key);

                if(!key.startsWith("ep_")){
                    if(!keys.hasNext()){
                        epQueryString.deleteCharAt(epQueryString.length()-1);
                        break;
                    }else{
                        continue;
                    }
                }
                if(key.startsWith("hyper_dmp_container_id")){
                    if(!keys.hasNext()){
                        epQueryString.deleteCharAt(epQueryString.length()-1);
                        break;
                    }else{
                        continue;
                    }
                }

                if(!keys.hasNext()){
                    epQueryString.append("ep=" + HyperDMPUtils.urlEncodeUTF8(value.toString()));
                    break;
                }
                epQueryString.append("ep=" + HyperDMPUtils.urlEncodeUTF8(value.toString()) + "&");
            }

            return epQueryString.toString();
        }catch(Exception e){
            Log.i(HyperDMPPixelProvider.HYPER_DMP_LOG_NAME, HyperDMPPixelProvider.HYPER_DMP_LOG_TYPE_ERROR+e);
            throw new Exception("getEPQueryString:: ep_ parameter not valid");
        }
    }

    protected static String getDynamicQueryString(Map<String, Object> map) throws Exception {
        try{
            StringBuilder dynamicQueryString = new StringBuilder();
            Iterator<String> keys = map.keySet().iterator();
            while(keys.hasNext()){
                String key = keys.next();
                Object value = map.get(key);

                if(key.startsWith("ep_")){
                    if(!keys.hasNext()){
                        dynamicQueryString.deleteCharAt(dynamicQueryString.length()-1);
                        break;
                    }else{
                        continue;
                    }
                }
                if(key.startsWith("hyper_dmp_container_id")){
                    if(!keys.hasNext()){
                        dynamicQueryString.deleteCharAt(dynamicQueryString.length()-1);
                        break;
                    }else{
                        continue;
                    }
                }

                if(!keys.hasNext()){
                    dynamicQueryString.append(key + "=" + HyperDMPUtils.urlEncodeUTF8(value.toString()));
                    break;
                }
                dynamicQueryString.append(key + "=" + HyperDMPUtils.urlEncodeUTF8(value.toString()) + "&");
            }

            return dynamicQueryString.toString();
        }catch(Exception e){
            Log.i(HyperDMPPixelProvider.HYPER_DMP_LOG_NAME, HyperDMPPixelProvider.HYPER_DMP_LOG_TYPE_ERROR+e);
            throw new Exception("getDynamicQueryString:: dynamic parameter not valid");
        }
    }

    protected static Bundle getBundleInGTMTrigger(Map<String,Object> map){
        Bundle params = new Bundle();
        for(String key: map.keySet()){
            Object value = map.get(key);

            if(value != null){
                continue;
            }

            if(value.equals("")){
                continue;
            }

            Log.i(HyperDMPPixelProvider.HYPER_DMP_LOG_NAME, key + "////" + value.toString());
            params.putString(key, value.toString());
        }


        return params;
    }

}
