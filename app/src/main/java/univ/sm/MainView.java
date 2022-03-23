package univ.sm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.analytics.FirebaseAnalytics;

import univ.sm.model.Const;
import univ.sm.util.ShareUtil;
import univ.sm.view.CommonView;
import univ.sm.view.SettingView;
import univ.sm.view.board.login.IndirectLoginView;
import univ.sm.view.detail.SchDetailFakeView;
import univ.sm.view.detail.SchDetailView;
import univ.sm.view.entry.SchEntryFakeView;
import univ.sm.view.entry.SchEntryView;
import univ.sm.view.entry.SchEntryWebView;
import univ.sm.view.question.InfoView;


public class MainView extends CommonView implements View.OnClickListener {
    LinearLayout sch_detail_btn,            //  상세 스케줄 버튼
            sch_entry_btn,             //  전체정보 뿌려주는 버튼
            app_info_btn;              //  앱 정보 버튼

    SharedPreferences g_limit_v;
    SharedPreferences.Editor editor;
    View mAdView;
    InterstitialAd mInterstitialAd;
    ImageView kakaoShare, facebookShare, settingBtn;

    FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        sch_detail_btn = (LinearLayout) findViewById(R.id.sch_detail_btn);
        sch_entry_btn = (LinearLayout) findViewById(R.id.sch_entry_btn);
        app_info_btn = (LinearLayout) findViewById(R.id.app_info_btn);

        /** 추가 기능-버튼 클릭시 게시판 */
        LinearLayout mainImageView = (LinearLayout) findViewById(R.id.callboard_menu_btn);
        mainImageView.setOnClickListener(this);

        sch_detail_btn.setOnClickListener(this);
        sch_entry_btn.setOnClickListener(this);
        app_info_btn.setOnClickListener(this);

        g_limit_v = getSharedPreferences(Const.SHARED_LIMETE_LAYOUT, MODE_PRIVATE);

        /** share button*/
        kakaoShare = (ImageView) findViewById(R.id.kakaoShare);
        kakaoShare.setOnClickListener(this);

        facebookShare = (ImageView) findViewById(R.id.facebookShare);
        facebookShare.setOnClickListener(this);

        /** sdk initialize */
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);

        /**setting button*/
        settingBtn = (ImageView) findViewById(R.id.setting_button);
        settingBtn.setOnClickListener(this);

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString("page", "메인");
        params.putString("event", "방문");
        params.putString("value", "메인");
        mFirebaseAnalytics.logEvent("app_hyper_dmp_v2", params);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Intent fake = new Intent();
        Bundle params = null;
        switch (v.getId()) {
            case R.id.sch_detail_btn:
                params = new Bundle();
                params.putString("page", "메인");
                params.putString("event", "클릭");
                params.putString("value", "버스시간표 상세 보기");
                mFirebaseAnalytics.logEvent("app_hyper_dmp_v2", params);
                intent.setClass(MainView.this, SchDetailView.class);
                startActivity(intent);
                if (g_limit_v.getString(Const.CAN_U_FIRST_1, null) == null) {
                    fake.setClass(MainView.this, SchDetailFakeView.class);
                    startActivity(fake);
                }
                break;
            case R.id.sch_entry_btn:
                params = new Bundle();
                params.putString("page", "메인");
                params.putString("event", "클릭");
                params.putString("value", "버스시간표 전체 보기");
                mFirebaseAnalytics.logEvent("app_hyper_dmp_v2", params);
                intent.setClass(MainView.this, SchEntryWebView.class);
                startActivity(intent);
//                intent.setClass(MainView.this, SchEntryView.class);
//                startActivity(intent);
//                if (g_limit_v.getString(Const.CAN_U_FIRST_2, null) == null) {
//                    fake.setClass(MainView.this, SchEntryFakeView.class);
//                    startActivity(fake);
//                }
                break;
            case R.id.app_info_btn:
                params = new Bundle();
                params.putString("page", "메인");
                params.putString("event", "클릭");
                params.putString("value", "앱 소개 페이지 보기");
                mFirebaseAnalytics.logEvent("app_hyper_dmp_v2", params);
                intent.setClass(MainView.this, InfoView.class);
                startActivity(intent);
                break;
            case R.id.callboard_menu_btn:
                params = new Bundle();
                params.putString("page", "메인");
                params.putString("event", "클릭");
                params.putString("value", "콜벤 페이지 보기");
                mFirebaseAnalytics.logEvent("app_hyper_dmp_v2", params);
                intent.setClass(MainView.this, IndirectLoginView.class);
                startActivity(intent);

                /*intent.setClass(MainView.this, BoardView.class);
                startActivity(intent);*/
                break;
            case R.id.kakaoShare:
                params = new Bundle();
                params.putString("page", "메인");
                params.putString("event", "클릭");
                params.putString("value", "카카오 공유하기");
                mFirebaseAnalytics.logEvent("app_hyper_dmp_v2", params);
                ShareUtil.shareStaticKakao(this);
                break;
            case R.id.facebookShare:
                params = new Bundle();
                params.putString("page", "메인");
                params.putString("event", "클릭");
                params.putString("value", "페이스북 공유하기");
                mFirebaseAnalytics.logEvent("app_hyper_dmp_v2", params);
                ShareUtil.shareFacebook(this);
                break;
            case R.id.setting_button:
                params = new Bundle();
                params.putString("page", "메인");
                params.putString("event", "클릭");
                params.putString("value", "설정하기");
                mFirebaseAnalytics.logEvent("app_hyper_dmp_v2", params);
                intent.setClass(MainView.this, SettingView.class);
                startActivity(intent);
                break;
        }
    }
}
