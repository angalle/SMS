package univ.sm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import univ.sm.connect.Connection;
import univ.sm.data.Shuttle;
import univ.sm.data.SplashData;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread th = new Thread(){
            @Override
            public void run() {
                try {
                    DataSetting();
                    sleep(5000);
                    Intent next = new Intent(Splash.this, MainActivity.class);
                    startActivity(next);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        th.start();

    }

    private void DataSetting() {
        int i = 0;
        for (String url : SplashData.busUrl) {
            Connection.positionShuttleArr[i] = new ArrayList<Shuttle>();
            Connection getBusArray = new Connection(url);
            if (i != SplashData.busUrl.length - 1) {
                getBusArray.HttpConnect();
                Connection.positionShuttleArr[i] = getBusArray.getBusArray();
                if (Connection.positionShuttleArr[i].get(0).getNo() == null) {
                    break;
                }
            } else {
                SplashData.setNotice_con(getBusArray.HttpInfoConnect());
            }
            i++;
        }
    }

}
