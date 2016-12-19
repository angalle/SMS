package univ.sm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout sch_detail_btn,            //  상세 스케줄 버튼
                 sch_entry_btn,             //  전체정보 뿌려주는 버튼
                 app_info_btn;              //  앱 정보 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sch_detail_btn = (LinearLayout) findViewById(R.id.sch_detail_btn);
        sch_entry_btn = (LinearLayout) findViewById(R.id.sch_entry_btn);
        app_info_btn = (LinearLayout) findViewById(R.id.app_info_btn);


        sch_detail_btn.setOnClickListener(this);
        sch_entry_btn.setOnClickListener(this);
        app_info_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v.getId() == R.id.sch_detail_btn){
            intent.setClass(MainActivity.this,SchDetail.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.sch_entry_btn){
            intent.setClass(MainActivity.this,SchDetail.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.app_info_btn){
            final AppInfo dialog = new AppInfo(this);
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
            dialog.show();
        }

    }
}
