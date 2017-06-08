package univ.sm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;

import univ.sm.board.BoardActivity;
import univ.sm.board.BoardManager;
import univ.sm.connect.LoopjConnection;
import univ.sm.data.Const;

public class MainActivity extends CommonActivity implements View.OnClickListener {
    LinearLayout sch_detail_btn,            //  상세 스케줄 버튼
            sch_entry_btn,             //  전체정보 뿌려주는 버튼
            app_info_btn;              //  앱 정보 버튼

    SharedPreferences g_limit_v;
    SharedPreferences.Editor editor;


    ImageView kakaoShare;

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
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Intent fake = new Intent();

        if (v.getId() == R.id.sch_detail_btn) {
            intent.setClass(MainActivity.this, SchDetail.class);
            startActivity(intent);
            if (g_limit_v.getString(Const.CAN_U_FIRST_1, null) == null) {
                fake.setClass(MainActivity.this, SchDetailFake.class);
                startActivity(fake);
            }
        } else if (v.getId() == R.id.sch_entry_btn) {
            intent.setClass(MainActivity.this, SchEntry.class);
            startActivity(intent);
            if (g_limit_v.getString(Const.CAN_U_FIRST_2, null) == null) {
                fake.setClass(MainActivity.this, SchEntryFake.class);
                startActivity(fake);
            }
        } else if (v.getId() == R.id.app_info_btn) {
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
        } else if (v.getId() == R.id.callboard_menu_btn) {
            intent.setClass(MainActivity.this, BoardActivity.class);
            startActivity(intent);
        }else if (v.getId() == R.id.kakaoShare) {
            shareKakao();
        }
    }

    private void shareKakao(){
        try{
            final KakaoLink kakaoLink = KakaoLink.getKakaoLink(this);
            final KakaoTalkLinkMessageBuilder kakaoMsgBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

            kakaoMsgBuilder.addText("SMS - 선문대셔틀버스 시간표\n콜벤게시판 기능을 추가한 SMS 입니다.\n 게시판에 글을 남기고 댓글을 남기면 해당 게시글의 모든 사용자에게 " +
                    " 댓글알람이 울립니다. 알람내용을 확인하면 해당 댓글로 이동하게 됩니다.");

            String url  = "https://lh3.googleusercontent.com/Rs_2Gp66OYlGpd8oLgdNtefYa7xHqFlaof33ena8A7M0Cv6DbywgyLG2vYm8awxim4g=h900-rw";
            String url2  = "https://lh3.googleusercontent.com/HWQPZPUdMvPpP_R5QQmxQiFgtFzfrlpV9mFHeQb56uhzgahxqUNHoGO_D00vsf3ACqA=h900-rw";
            String url3  = "https://lh3.googleusercontent.com/GIVzb9_SRj_JUQmL9vvZale6SaFQHsmfdswtRJ-0bZc3BwfYHYwIu3gUGryPHWMw8PnM=h900-rw";
            kakaoMsgBuilder.addImage(url,160,160);
            //kakaoMsgBuilder.addImage(url2,160,160);
            //kakaoMsgBuilder.addImage(url3,160,160);

            kakaoMsgBuilder.addAppButton("SMS - 선문대셔틀버스 시간표");

            kakaoLink.sendMessage(kakaoMsgBuilder,this);

            Toast.makeText(getApplicationContext(),"카카오톡 메세지 전송",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
