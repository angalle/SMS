package univ.sm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;

import univ.sm.board.BoardActivity;
import univ.sm.data.Const;


public class MainActivity extends CommonActivity implements View.OnClickListener {
    LinearLayout sch_detail_btn,            //  상세 스케줄 버튼
            sch_entry_btn,             //  전체정보 뿌려주는 버튼
            app_info_btn;              //  앱 정보 버튼

    SharedPreferences g_limit_v;
    SharedPreferences.Editor editor;

    ImageView kakaoShare, facebookShare, settingBtn;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typekit.getInstance().
                addNormal(Typekit.createFromAsset(this, "fonts/NanumBarunGothic.otf"));

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

        kakaoShare = (ImageView) findViewById(R.id.kakaoShare);
        kakaoShare.setOnClickListener(this);

        facebookShare = (ImageView) findViewById(R.id.facebookShare);
        facebookShare.setOnClickListener(this);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        /**setting button*/
        settingBtn = (ImageView) findViewById(R.id.setting_button);
        settingBtn.setOnClickListener(this);

        /***setting info init*/
        SharedPreferences sp = getApplicationContext().getSharedPreferences(Const.SETINFO, MODE_PRIVATE);
        if("first".equals(sp.getString(Const.NOTICE, "first"))){
            Toast.makeText(getApplicationContext(),"here is ?",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor spe = sp.edit();
            spe.putString(Const.NOTICE,Const.DEFAULTNOTI);
            spe.commit();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Intent fake = new Intent();


        switch (v.getId()) {
            case R.id.sch_detail_btn:
                intent.setClass(MainActivity.this, SchDetail.class);
                startActivity(intent);
                if (g_limit_v.getString(Const.CAN_U_FIRST_1, null) == null) {
                    fake.setClass(MainActivity.this, SchDetailFake.class);
                    startActivity(fake);
                }
                break;
            case R.id.sch_entry_btn:
                intent.setClass(MainActivity.this, SchEntry.class);
                startActivity(intent);
                if (g_limit_v.getString(Const.CAN_U_FIRST_2, null) == null) {
                    fake.setClass(MainActivity.this, SchEntryFake.class);
                    startActivity(fake);
                }
                break;
            case R.id.app_info_btn:
                 /*final AppInfo dialog = new AppInfo(this);
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    //나타날때 쓰는 효과
                }
            });
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    //사라질때 하는 효과
                }
            });
            dialog.show();*/
                intent.setClass(MainActivity.this, InfoActivity.class);
                startActivity(intent);
                break;
            case R.id.callboard_menu_btn:
                intent.setClass(MainActivity.this, BoardActivity.class);
                startActivity(intent);
                break;
            case R.id.kakaoShare:
                shareKakao();
                break;
            case R.id.facebookShare:
                shareFacebook();
                break;
            case R.id.setting_button:
                intent.setClass(MainActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void shareFacebook() {
        ShareLinkContent content = new ShareLinkContent.Builder()

                .setContentTitle("SMS - 선문대셔틀버스 시간표 & 콜벤합승")
                .setImageUrl(Uri.parse("https://lh3.googleusercontent.com/Rs_2Gp66OYlGpd8oLgdNtefYa7xHqFlaof33ena8A7M0Cv6DbywgyLG2vYm8awxim4g=h900-rw"))
                .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=univ.sm"))
                .setContentDescription("SMS - 선문대셔틀버스 시간표 & 콜벤합승\n콜벤합승기능이 추가되었습니다. \n처음한번 입력하면 입력정보는 계속 기억됩니다.\n테스트중에 있으니 많은 피드백 주시기 바랍니다.")
                .build();

        ShareDialog shareDialog = new ShareDialog(this);

        shareDialog.show(content, ShareDialog.Mode.FEED);
        Toast.makeText(getApplicationContext(), "페이스북 공유하기", Toast.LENGTH_SHORT).show();
    }

    private void shareKakao() {
        try {
            final KakaoLink kakaoLink = KakaoLink.getKakaoLink(this);
            final KakaoTalkLinkMessageBuilder kakaoMsgBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

            kakaoMsgBuilder.addText("SMS - 선문대셔틀버스 시간표 & 콜벤합승\n콜벤합승기능이 추가되었습니다. \n처음한번 입력하면 입력정보는 계속 기억됩니다.\n테스트중에 있으니 많은 피드백 주시기 바랍니다.");

            String url = "https://lh3.googleusercontent.com/Rs_2Gp66OYlGpd8oLgdNtefYa7xHqFlaof33ena8A7M0Cv6DbywgyLG2vYm8awxim4g=h900-rw";
            String url2 = "https://lh3.googleusercontent.com/HWQPZPUdMvPpP_R5QQmxQiFgtFzfrlpV9mFHeQb56uhzgahxqUNHoGO_D00vsf3ACqA=h900-rw";
            String url3 = "https://lh3.googleusercontent.com/GIVzb9_SRj_JUQmL9vvZale6SaFQHsmfdswtRJ-0bZc3BwfYHYwIu3gUGryPHWMw8PnM=h900-rw";
            kakaoMsgBuilder.addImage(url, 160, 160);
            //kakaoMsgBuilder.addImage(url2,160,160);
            //kakaoMsgBuilder.addImage(url3,160,160);
            //kakaoMsgBuilder.addAppLink("https://play.google.com/store/apps/details?id=univ.sm");
            kakaoMsgBuilder.addAppButton("SMS - 선문대셔틀버스 시간표 & 콜벤합승");

            kakaoLink.sendMessage(kakaoMsgBuilder, this);

            Toast.makeText(getApplicationContext(), "카카오톡 공유하기", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int exitCount = 0;

   /* @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (exitCount == 1) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            exitCount++;
        }
    }*/
}
