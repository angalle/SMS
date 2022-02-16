package univ.sm.view.entry;

import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import univ.sm.BuildConfig;
import univ.sm.R;
import univ.sm.view.CommonView;

/**
 * Created by heesun on 2016-12-13.
 */

public class SchEntryWebView extends CommonView{

    WebView webView;
    View mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_entry_web);


        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://sm.choi.link");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mAdView = findViewById(R.id.adView_main);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        if(BuildConfig.BUILD_TYPE == "debug"){
            adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        }else{
            adView.setAdUnitId("ca-app-pub-8944137857067935/8779439198");
        }
        ((LinearLayout)mAdView).addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}