package univ.sm.view;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.Iterator;
import java.util.List;

import univ.sm.R;

/**
 * Created by heesun on 2017-02-23.
 * 공통 구현할 부분을 구현하시오.
 *
 * 1. 뒤로가기 키를 눌렀을때 광고와 종료버튼을 구현.
 */

public class CommonView extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.ad_dialog);
        NativeExpressAdView adView = (NativeExpressAdView)dialog.findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());

        Button backBtn = (Button)dialog.findViewById(R.id.back);
        TextView backTv = (TextView)dialog.findViewById(R.id.back_tv);
        Button positiveBtn = (Button)dialog.findViewById(R.id.positive);
        Button negativeBtn = (Button)dialog.findViewById(R.id.negative);

        /* 뒤로가기버튼 사라지기 */
        disappearView(backBtn);
        disappearView(backTv);
        /* 뒤로가기 */
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        /* 취소버튼 */
        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    /* 상위의 엑티비티가  MainView 면 해당 view가 사라지는 기능.*/
    private void disappearView(View view){
        List<ActivityManager.RunningTaskInfo > info;
        Context mContext  = getApplicationContext();
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(mContext.ACTIVITY_SERVICE);

        info = activityManager.getRunningTasks(7);

        for (Iterator iterator = info.iterator(); iterator.hasNext();)  {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) iterator.next();
            if(runningTaskInfo.topActivity.getClassName().equals("univ.sm.MainView")) {
                view.setVisibility(View.GONE);
            }
        }
    }
}
