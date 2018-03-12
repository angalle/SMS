package univ.sm.Controller.api.schdule;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import univ.sm.Controller.CommonCallbak;
import univ.sm.Model.Const;
import univ.sm.Model.shuttle.Shuttle;

/**
 * Created by heesun on 2017-12-06.
 */

public class SchService {

    static SchCall schduleApi;
    static Context mContext;
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
                .baseUrl(Const.BASE_URL)
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
        parameters = new HashMap<String,Object>();
        parameters.put("UNIV_CODE","SMS");
        schduleApi.get_schdule(parameters).enqueue(new Callback<ArrayList<Shuttle>>() {
            @Override
            public void onResponse(Call<ArrayList<Shuttle>> call, Response<ArrayList<Shuttle>> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.code(), response.body());
                }else{
                    callback.onFailure(response.code());
                    Toast.makeText(mContext,response.code()+":::error",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Shuttle> > call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
