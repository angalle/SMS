package univ.sm;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import butterknife.ButterKnife;

public class AppInfo extends Dialog{
    public AppInfo(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.app_info);
        //ButterKnife.bind(this);

        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new DialogViewAdapter(getContext()));
/*
        //Bind the title indicator to the adapter
        CirclePageIndicator circleIndicator = (CirclePageIndicator)findViewById(R.id.titles);
        circleIndicator.setViewPager(pager);*/

    }
}

