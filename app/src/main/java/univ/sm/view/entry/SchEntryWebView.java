package univ.sm.view.entry;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import univ.sm.R;
import univ.sm.StaticData;
import univ.sm.model.Const;
import univ.sm.model.Utility;
import univ.sm.model.shuttle.Shuttle;
import univ.sm.view.CommonView;
import univ.sm.view.entry.adapter.EntryRecyclerAdapter;

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
        webView.loadUrl("http://sm.choi.link");
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