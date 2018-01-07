package univ.sm.connect;

import android.content.Context;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import univ.sm.data.item.Shuttle;

/**
 * Created by heesun on 2017-12-06.
 */

public class CommonConnection {

    static CommonService apiService;
    static Context mContext;
    static String mUrl;
    private static Retrofit retrofit;

    private static class SingletonHolder{
        private static CommonConnection INSTANCE = new CommonConnection(mContext,mUrl);
    }

    public static CommonConnection getInstance(Context context,String url){
        if(context != null || url != null){
            mContext = context;
            mUrl = url;
        }

        return SingletonHolder.INSTANCE;
    }

    private CommonConnection(Context context,String url){
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
    }

    public CommonConnection createApi(){
        apiService = create(CommonService.class);
        return this;
    }

    public <T> T create(final Class<T> service){
        if(service == null){
            throw new RuntimeException("Api is nul:::::::::");
        }

        return retrofit.create(service);
    }



    public void getSchedule(HashMap<String,Object> parameters, final  CommonCallbak callback){
        apiService.getSchedule(parameters).enqueue(new Callback<Shuttle>() {
            @Override
            public void onResponse(Call<Shuttle> call, Response<Shuttle> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.code(),response.body());
                }else{
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<Shuttle> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
