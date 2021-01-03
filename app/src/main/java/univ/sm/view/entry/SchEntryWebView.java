package univ.sm.view.entry;

import android.os.Bundle;
import androidx.annotation.Nullable;

import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import univ.sm.R;
import univ.sm.view.CommonView;

/**
 * Created by heesun on 2016-12-13.
 */

public class SchEntryWebView extends CommonView{

    WebView webView;
    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AdRequest adRequest = new AdRequest.Builder().build();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_entry_web);


        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://sm.choi.link");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mAdView = (AdView)findViewById(R.id.adView_entry);
        mAdView.loadAd(adRequest);


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