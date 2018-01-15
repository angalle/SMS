package univ.sm.connect.api.schdule;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import univ.sm.connect.api.CommonCallbak;
import univ.sm.data.item.Shuttle;

/**
 * Created by heesun on 2017-12-06.
 */

public class SchService {

    static SchCall schduleApi;
    static Context mContext;
    static String baseUrl = SchCall.BaseURL;
    private static Retrofit retrofit;

    private static class SingletonHolder{
        private static SchService INSTANCE = new SchService(mContext);
    }

    public static SchService getInstance(Context context){
        if(context != null){
            mContext = context;
        }

        return SingletonHolder.INSTANCE;
    }

    private SchService(Context context){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public SchService createApi(){
        schduleApi = create(SchCall.class);
        return this;
    }

    public <T> T create(final Class<T> service){
        if(service == null){
            throw new RuntimeException("Api is nul:::::::::");
        }
        return retrofit.create(service);
    }



    public void getSchedule(HashMap<String,Object> parameters, final CommonCallbak callback){
        Log.e("SchCall1 ::::::","call data");
        schduleApi.get_schdule_p(parameters).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    Log.e("success ::::::",response.toString()+":::success");
                    callback.onSuccess(response.code(),response.body());
                }else{
                    Log.e("error ::::::",response.code()+":::error");
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
