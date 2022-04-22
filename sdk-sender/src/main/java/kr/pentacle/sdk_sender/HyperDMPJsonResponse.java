package kr.pentacle.sdk_sender;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HyperDMPJsonResponse {
    static String HYPER_DMP_LOG_NAME = "[JSON]HyperDMPLogger";

    String jsonTarget = "";
    JSONArray jArray = null;

    public HyperDMPJsonResponse(String json){
        try {
            this.jsonTarget = json;
            this.jArray = new JSONArray(json);
        } catch (JSONException e) {
            Log.i(HYPER_DMP_LOG_NAME, "ERROR::HyperDMPJsonResponse"+e);
            return ;
        }
    }

    public List<Bundle> getAllParameters(){
        List<Bundle> parameters = new ArrayList<>();
        try {
            for(int index = 0 ; index < jArray.length(); index++) {
                JSONObject jObj = null;
                jObj = jArray.getJSONObject(index);

                String segmentId = jObj.get("segmentId").toString();
                JSONArray destinationsArr = jObj.getJSONArray("destinations");
                parameters.addAll(getListBundle(destinationsArr, segmentId));
            }
        } catch (JSONException e) {
            Log.i(HYPER_DMP_LOG_NAME, "ERROR::getDestination"+e);
            return null;
        }
        return parameters;
    }

    private List<Bundle> getListBundle(JSONArray destinationsArr, String segmentId){
        List<Bundle> parameters = new ArrayList<>();
        try {
            for(int dIndex = 0 ; dIndex < destinationsArr.length(); dIndex++){
                Bundle params = new Bundle();
                JSONObject destinationObj = destinationsArr.getJSONObject(dIndex);
                String destinationId = destinationObj.get("destinationId").toString();
                JSONArray templateMapperArr = destinationObj.getJSONArray("template_mappers");

                for(int tmIndex = 0 ; tmIndex < templateMapperArr.length(); tmIndex++){
                    JSONObject templateObj = templateMapperArr.getJSONObject(tmIndex);
                    params = addToParameters(templateObj, params);
                }

                params.putString("segmentId", segmentId);
                params.putString("destinationId", destinationId);
                parameters.add(params);
            }
        } catch (JSONException e) {
            Log.i(HYPER_DMP_LOG_NAME, "ERROR::getParameter"+e);
            return null;
        }
        return parameters;
    }

    private Bundle addToParameters(JSONObject templateObj, Bundle params){
        try {
            for (Iterator<String> it = templateObj.keys(); it.hasNext(); ) {
                String key = it.next();
                Object object = templateObj.get(key);
                params.putString(key, object.toString());
            }
        } catch (JSONException e) {
            Log.i(HYPER_DMP_LOG_NAME, "ERROR::addToParameters"+e);
            return null;
        }
        return params;
    }
}
