package univ.sm.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.facebook.common.Common;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.HashMap;

import univ.sm.CommonUtil;
import univ.sm.MainView;
import univ.sm.R;
import univ.sm.connect.Connection;
import univ.sm.connect.api.schdule.SchCall;
import univ.sm.connect.api.schdule.SchCallbakService;
import univ.sm.connect.api.schdule.SchService;
import univ.sm.data.item.Shuttle;
import univ.sm.data.SplashData;
import univ.sm.gcm.RegistrationIntentService;

public class SplashView extends Activity {

    public InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        /* setting fabric  */
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());

        /* check google play service */
        if (CommonUtil.checkPlayServices(this)) {
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }else{
            finish();
        }

        /* 전역변수에 db 데이터 받아오기 */
        CommonUtil.DataSetting(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent next = new Intent(SplashView.this, MainView.class);
                startActivity(next);
                finish();
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


}
