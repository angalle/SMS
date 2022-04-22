package kr.pentacle.sdk_sender;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.tagmanager.CustomTagProvider;

import java.util.Map;

public class HyperDMPFacebookProvider implements CustomTagProvider {

    static String HYPER_DMP_LOG_NAME = "[HyperDMPLogger]FACEBOOK";
    static String HYPER_DMP_TRIGGER_NAME = "HyperdmpSegment";

    @Override
    public void execute(@NonNull Map<String, Object> map) {
        Bundle params = HyperDMPUtils.getBundleInGTMTrigger(map);

        AppEventsLogger facebookEventSender = AppEventsLogger.newLogger(HyperDMPPixelProvider.globalContext);
        facebookEventSender.logEvent(HYPER_DMP_TRIGGER_NAME,params);

        Log.i(HYPER_DMP_LOG_NAME, "END");
    }

}
