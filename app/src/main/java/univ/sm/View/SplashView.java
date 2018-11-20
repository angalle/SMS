package univ.sm.View;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.fabric.sdk.android.Fabric;

import univ.sm.Util.CommonUtil;
import univ.sm.MainView;
import univ.sm.R;
import univ.sm.gcm.RegistrationIntentService;

public class SplashView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
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

        Intent next = new Intent(SplashView.this, MainView.class);
        startActivity(next);
        finish();
    }
}
