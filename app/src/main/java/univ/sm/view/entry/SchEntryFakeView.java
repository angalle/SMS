package univ.sm.view.entry;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import univ.sm.R;
import univ.sm.data.Const;

/**
 * Created by heesun on 2016-12-13.
 */

public class SchEntryFakeView extends AppCompatActivity{
    SharedPreferences g_limit_v;
    SharedPreferences.Editor editor;
    ImageView image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_entry_fake);
        image = (ImageView)findViewById(R.id.imageTT2);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        g_limit_v = getSharedPreferences(Const.SHARED_LIMETE_LAYOUT,MODE_PRIVATE);
        editor = g_limit_v.edit();
        editor.putString(Const.CAN_U_FIRST_2,Const.NO);
        editor.commit();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
