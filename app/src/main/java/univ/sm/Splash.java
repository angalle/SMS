package univ.sm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import univ.sm.connect.Connection;
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
                    System.out.println("access1::::");
                    Connection connection = new Connection(SplashData.ip);
                    connection.HttpConnect();
                    System.out.println("httpConnection::::result::::");
                    System.out.println(Connection.ShuttleArr);
                    //connection for 문돌리는곳이 따로있음 ip 끝주소 다시 한번 더 돌려줘야 데이터 다 들어옴
                    // 이부분 세팅
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

}
