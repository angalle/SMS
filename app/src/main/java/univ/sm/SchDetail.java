package univ.sm;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.logging.Log;

import java.util.logging.Logger;

import univ.sm.connect.Connection;
import univ.sm.data.RecyclerAdapter;
import univ.sm.data.SplashData;

/**
 * Created by heesun on 2016-12-13.
 */

public class SchDetail extends AppCompatActivity implements View.OnClickListener,ViewTreeObserver.OnGlobalLayoutListener {
    ImageView schDetailTopBar,quickBtn;
    TextView schDetailWeekDay,schDetailSatureDay,schDetailSunDay;
    int roatationDegree = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_detail);
        schDetailWeekDay = (TextView) findViewById(R.id.sch_detail_weekDay);
        schDetailSatureDay = (TextView) findViewById(R.id.sch_detail_satureDay);
        schDetailSunDay = (TextView) findViewById(R.id.sch_detail_sunDay);
        quickBtn = (ImageView) findViewById(R.id.quickBtn);

        /* 기본좌표 재 설정 / xml 상에서 정확한 좌표를 표시 할 수 없음*/
        schDetailTopBar = (ImageView) findViewById(R.id.sch_detail_top_bar);
        schDetailTopBar.getViewTreeObserver().addOnGlobalLayoutListener(this);


        /* 각 버튼 마다 이벤트 리스너*/
        schDetailWeekDay.setOnClickListener(this);
        schDetailSatureDay.setOnClickListener(this);
        schDetailSunDay.setOnClickListener(this);
        quickBtn.setOnClickListener(this);

        //System.out.println("httpConnection::::result::::");
        //System.out.println(Connection.ShuttleArr);
        /*Recycle view에 대한 설정*/
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sch_entry_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(),Connection.ShuttleArr, R.layout.activity_main));
    }

    @Override
    public void onClick(View v) {
        float toX=0,toWidth=0;      /* 이동해야할 x좌표 */
        if(v.getId() == R.id.sch_detail_weekDay){
            toX = v.getLeft();
            toWidth = v.getWidth();
            moveImageBar(toWidth,toX);
        }else if(v.getId() == R.id.sch_detail_satureDay){
            toX = v.getLeft();
            toWidth = v.getWidth();
            moveImageBar(toWidth,toX);
        }else if(v.getId() == R.id.sch_detail_sunDay){
            toX = v.getLeft();
            toWidth = v.getWidth();
            moveImageBar(toWidth,toX);
        }else if(v.getId() == R.id.quickBtn){
            roatationDegree += 360;
            ObjectAnimator rotation = ObjectAnimator.ofFloat(quickBtn,"rotation", roatationDegree).setDuration(500);
            rotation.setRepeatCount(Animation.ABSOLUTE);
            rotation.start();
        }
    }

    private void moveImageBar(float toWidth,float toX){
        float width = schDetailTopBar.getWidth();
        schDetailTopBar.animate().scaleX(toWidth/width);
        schDetailTopBar.animate().translationX(toX-(width-toWidth)/2.0f).withLayer();
    }

    @Override
    public void onGlobalLayout() {
        schDetailTopBar.setX(schDetailWeekDay.getX());
        schDetailTopBar.getLayoutParams().width = schDetailWeekDay.getWidth();
    }
}
