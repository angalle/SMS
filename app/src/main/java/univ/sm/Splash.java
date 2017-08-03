package univ.sm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;

import univ.sm.connect.Connection;
import univ.sm.data.Shuttle;
import univ.sm.data.SplashData;
import univ.sm.gcm.RegistrationIntentService;

public class Splash extends Activity {
    public static ArrayList<Shuttle>[] positionShuttleArr = new ArrayList[SplashData.busUrl.length];
    public InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        setContentView(R.layout.splash);
        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }

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
        }, 1000);
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

    /**
     * Google Play Service를 사용할 수 있는 환경이지를 체크한다.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,9000).show();
            } else {
                Log.i("test", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
