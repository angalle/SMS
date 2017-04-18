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
        }
    }
}
