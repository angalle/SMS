package univ.sm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import univ.sm.connect.Connection;
import univ.sm.data.Shuttle;
import univ.sm.data.SplashData;

public class Splash extends Activity {
    public static ArrayList<Shuttle>[] positionShuttleArr = new ArrayList[SplashData.busUrl.length];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);



        Thread th = new Thread(){
            @Override
            public void run() {
                DataSetting();
            }
        };
        th.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent next = new Intent(Splash.this, MainActivity.class);
                startActivity(next);
                finish();
            }
        }, 5000);



    }

    private boolean DataSetting() {
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
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
