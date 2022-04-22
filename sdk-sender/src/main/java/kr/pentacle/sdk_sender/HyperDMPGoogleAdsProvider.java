package kr.pentacle.sdk_sender;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tagmanager.CustomTagProvider;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Map;

public class HyperDMPGoogleAdsProvider implements CustomTagProvider {

    static String HYPER_DMP_LOG_NAME = "[HyperDMPLogger]GOOGLE";
    static String HYPER_DMP_TRIGGER_NAME = "HyperDMP_DSPSegment";

    @Override
    public void execute(@NonNull Map<String, Object> map) {
        Bundle params = HyperDMPUtils.getBundleInGTMTrigger(map);

        FirebaseAnalytics firebaseAnalyticsEventSender = FirebaseAnalytics.getInstance(HyperDMPPixelProvider.globalContext);
        firebaseAnalyticsEventSender.logEvent(HYPER_DMP_TRIGGER_NAME, params);

        Log.i(HYPER_DMP_LOG_NAME, "END");
    }

}
