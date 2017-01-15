package univ.sm;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
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

public class SchDetail extends AppCompatActivity implements View.OnClickListener {
    ImageView schDetailTopBar,quickBtn,changeDirection;
    TextView schDetailWeekDay,schDetailSatureDay,schDetailSunDay;
    RecyclerView recyclerView;
    int roatationDegree_quick = 0;
    int roatationDegree_change = 0;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_detail);

        context = getApplicationContext();

        quickBtn = (ImageView) findViewById(R.id.quickBtn);
        changeDirection = (ImageView) findViewById(R.id.changeDirection);


        /* 각 버튼 마다 이벤트 리스너*/
        quickBtn.setOnClickListener(this);
        changeDirection.setOnClickListener(this);

        //System.out.println("httpConnection::::result::::");
        //System.out.println(Connection.ShuttleArr);
        /*Recycle view에 대한 설정*/
        recyclerView = (RecyclerView) findViewById(R.id.sch_entry_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerAdapter(context,Connection.positionShuttleArr[0], R.layout.activity_main));
    }

    @Override
    public void onClick(View v) {
       if(v.getId() == R.id.quickBtn){
            roatationDegree_quick += 360;
            ObjectAnimator rotation = ObjectAnimator.ofFloat(v,"rotation", roatationDegree_quick).setDuration(500);
            rotation.setRepeatCount(Animation.ABSOLUTE);
            rotation.start();
        }else if(v.getId() == R.id.changeDirection){
            roatationDegree_change += 180;
            ObjectAnimator rotation = ObjectAnimator.ofFloat(v,"rotation", roatationDegree_change).setDuration(500);
            rotation.setRepeatCount(Animation.ABSOLUTE);
            rotation.start();
        }
    }


}
