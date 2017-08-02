package univ.sm;

import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

/**
 * Created by heesun on 2017-08-03.
 */
public class ad extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_dialog);


        NativeExpressAdView adView = (NativeExpressAdView)findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
    }
}
