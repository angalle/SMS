package kr.pentacle.sdk_sender;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tagmanager.CustomTagProvider;
import com.kakao.ad.common.json.CompleteRegistration;
import com.kakao.ad.tracker.KakaoAdTracker;

import java.util.Map;

public class HyperDMPKaKaoProvider implements CustomTagProvider {

    static String HYPER_DMP_LOG_NAME = "[HyperDMPLogger]KAKAO";

    @Override
    public void execute(@NonNull Map<String, Object> map) {

        Bundle params = HyperDMPUtils.getBundleInGTMTrigger(map);

        KakaoAdTracker.getInstance().init(HyperDMPPixelProvider.globalContext, HyperDMPPixelProvider.kakaoAdTrackId);
        CompleteRegistration event = new CompleteRegistration();
        event.tag = "PageView"; // 분류
        KakaoAdTracker.getInstance().sendEvent(event);

        Log.i(HYPER_DMP_LOG_NAME, "END");
    }
}
