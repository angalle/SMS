package univ.sm.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import univ.sm.MainView;
import univ.sm.R;

/**
 * Created by heesun on 2017-02-23.
 * 공통 구현할 부분을 구현하시오.
 *
 * 1. 뒤로가기 키를 눌렀을때 광고와 종료버튼을 구현.
 */

public class CommonView extends AppCompatActivity {
    private AdView mAdView;
    Button backBtn,positiveBtn,negativeBtn;
    TextView backTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdRequest adRequest = new AdRequest.Builder().build();
        setContentView(R.layout.ad_dialog);
        mAdView = (AdView)findViewById(R.id.adView);
        mAdView.loadAd(adRequest);


        backBtn = (Button)findViewById(R.id.back);
        backTv = (TextView)findViewById(R.id.back_tv);
        positiveBtn = (Button)findViewById(R.id.positive);
        negativeBtn = (Button)findViewById(R.id.negative);

        /* 뒤로가기버튼 사라지기 */
        disappearView(backBtn);
        disappearView(backTv);

        /* 뒤로가기 */
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainPage = new Intent(CommonView.this,MainView.class);
                startActivity(mainPage);
                finish();
            }
        });
        /* 완전종료 */
        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        /* 취소버튼 엑스버튼*/
        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,CommonView.class);
        startActivityForResult(intent,1);
    }

    /* 상위의 엑티비티가  MainView 면 해당 view가 사라지는 기능.*/
    private void disappearView(View view){
        if(getCallingActivity() == null || getCallingActivity().getClassName().equals("univ.sm.MainView")){
            view.setVisibility(View.GONE);
        }
    }
}