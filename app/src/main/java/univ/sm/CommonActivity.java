package univ.sm;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by heesun on 2017-02-23.
 * 공통 구현할 부분을 구현하시오.
 */

public class CommonActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.ad_dialog);
        //dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        NativeExpressAdView adView = (NativeExpressAdView)dialog.findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
        Button backBtn = (Button)dialog.findViewById(R.id.back);
        Button positiveBtn = (Button)dialog.findViewById(R.id.positive);
        Button negativeBtn = (Button)dialog.findViewById(R.id.negative);
        /* 뒤로가기버튼 사라지기 */
        disappearView(backBtn);
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

    /* 상위의 엑티비티가  MainActivity 면 해당 view가 사라지는 기능.*/
    private void disappearView(View view){
        Context mContext  = getApplicationContext();
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(mContext.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo > info;
        info = activityManager.getRunningTasks(7);
        for (Iterator iterator = info.iterator(); iterator.hasNext();)  {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) iterator.next();
            if(runningTaskInfo.topActivity.getClassName().equals("univ.sm.MainActivity")) {
                view.setVisibility(View.GONE);
            }
        }
    }
}
