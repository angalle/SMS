package univ.sm;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

/**
 * Created by heesun on 2017-02-23.
 * 공통 구현할 부분을 구현하시오.
 */

public class CommonActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.ad_dialog);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        NativeExpressAdView adView = (NativeExpressAdView)dialog.findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
        Button positiveBtn = (Button)dialog.findViewById(R.id.positive);
        Button negativeBtn = (Button)dialog.findViewById(R.id.negative);


        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
