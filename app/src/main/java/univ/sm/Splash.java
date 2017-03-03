package univ.sm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;

import univ.sm.connect.Connection;
import univ.sm.data.Shuttle;
import univ.sm.data.SplashData;

public class Splash extends Activity {
    public static ArrayList<Shuttle>[] positionShuttleArr = new ArrayList[SplashData.busUrl.length];
    public InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        setContentView(R.layout.splash);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8944137857067935/8003898402");
        requestNewInterstitial();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(),"erroCode"+errorCode,Toast.LENGTH_SHORT).show();
            }
        });

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

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)

                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
