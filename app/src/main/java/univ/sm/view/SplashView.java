package univ.sm.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.google.firebase.analytics.FirebaseAnalytics;

import io.fabric.sdk.android.Fabric;

import univ.sm.util.CommonUtil;
import univ.sm.MainView;
import univ.sm.R;
import univ.sm.fcm.RegistrationIntentService;

public class SplashView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        /* setting fabric  */
//        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());

        /* check google play service */
        if (CommonUtil.checkPlayServices(this)) {
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }else{
            finish();
        }


        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString("page", "스플레쉬 인트로 뷰");
        params.putString("event", "방문");
        params.putString("value", "스플레쉬");
        mFirebaseAnalytics.logEvent("app_hyper_dmp_v2", params);

        /* 전역변수에 db 데이터 받아오기 */
        CommonUtil.DataSetting(this);

        Intent next = new Intent(SplashView.this, MainView.class);
        startActivity(next);
        finish();
    }
}
