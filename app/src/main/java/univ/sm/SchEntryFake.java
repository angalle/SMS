package univ.sm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

import univ.sm.connect.Connection;
import univ.sm.data.Const;
import univ.sm.data.EntryRecyclerAdapter;
import univ.sm.data.Shuttle;

/**
 * Created by heesun on 2016-12-13.
 */

public class SchEntryFake extends AppCompatActivity{
    SharedPreferences g_limit_v;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_entry_fake);
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
