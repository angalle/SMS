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
import univ.sm.data.Const;
import univ.sm.data.RecyclerAdapter;
import univ.sm.data.SplashData;

/**
 * Created by heesun on 2016-12-13.
 */

public class SchDetail extends AppCompatActivity implements View.OnClickListener,ViewTreeObserver.OnGlobalLayoutListener{
    ImageView schDetailTopBar,  quickBtn,   changeDirection;
    TextView schDetailWeekDay,  schDetailSatureDay, schDetailSunDay,    changeStation_front,    changeStation_back;
    RecyclerView recyclerView;
    int roatationDegree_quick = 0;
    int roatationDegree_change = 0;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_detail);
        initView();
        context = getApplicationContext();

        /*Recycle view에 대한 설정*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerAdapter(context,Connection.positionShuttleArr[0], Const.OPPOSIT));
    }

    @Override
    public void onClick(View v) {

        /*평일 기능*/
        if(v.getId() == R.id.sch_detail_weekDay){
            moveImageBar(v);
            /*토요일 기능*/
        }else if(v.getId() == R.id.sch_detail_satureDay){
            moveImageBar(v);
            /*일요일 기능*/
        }else if(v.getId() == R.id.sch_detail_sunDay) {
            moveImageBar(v);
            /*빨리찾기 기능 구현*/
        }else if(v.getId() == R.id.quickBtn){
            roatationDegree_quick += 360;
            ObjectAnimator rotation = ObjectAnimator.ofFloat(v,"rotation", roatationDegree_quick).setDuration(500);
            rotation.setRepeatCount(Animation.ABSOLUTE);
            rotation.start();
            /*반대방향 구현*/
        }else if(v.getId() == R.id.changeDirection){
            roatationDegree_change += 180;
            ObjectAnimator rotation = ObjectAnimator.ofFloat(v,"rotationY", roatationDegree_change).setDuration(500);
            rotation.setRepeatCount(Animation.ABSOLUTE);
            rotation.start();
        }else if(v.getId() == R.id.changeStation_front || v.getId() == R.id.changeStation_back){

        }
    }

    /*주말선택의 빨간바를 이동하는 함수*/
    private void moveImageBar(View v){
        /* 이동해야할 x좌표 */
        float toX=v.getLeft();
        float toWidth=v.getWidth();

        float width = schDetailTopBar.getWidth();
        schDetailTopBar.animate().scaleX(toWidth/width);
        schDetailTopBar.animate().translationX(toX-(width-toWidth)/2.0f).withLayer();
    }

    private void initView(){
         /*주말/토요일/일요일 텍스트 이벤트*/
        schDetailWeekDay = (TextView) findViewById(R.id.sch_detail_weekDay);
        schDetailSatureDay = (TextView) findViewById(R.id.sch_detail_satureDay);
        schDetailSunDay = (TextView) findViewById(R.id.sch_detail_sunDay);
        changeStation_front = (TextView) findViewById(R.id.changeStation_front);
        changeStation_back = (TextView) findViewById(R.id.changeStation_back);

        quickBtn = (ImageView) findViewById(R.id.quickBtn);
        changeDirection = (ImageView) findViewById(R.id.changeDirection);

        /* 각 버튼 마다 이벤트 리스너*/
        quickBtn.setOnClickListener(this);
        changeDirection.setOnClickListener(this);
        schDetailWeekDay.setOnClickListener(this);
        schDetailSatureDay.setOnClickListener(this);
        schDetailSunDay.setOnClickListener(this);
        changeStation_front.setOnClickListener(this);
        changeStation_back.setOnClickListener(this);

        /* 기본좌표 재 설정 / xml 상에서 정확한 좌표를 표시 할 수 없음*/
        schDetailTopBar = (ImageView) findViewById(R.id.sch_detail_top_bar);
        schDetailTopBar.getViewTreeObserver().addOnGlobalLayoutListener(this);

        /*리스트뷰*/
        recyclerView = (RecyclerView) findViewById(R.id.sch_entry_list);
    }

    @Override
    public void onGlobalLayout() {
        schDetailTopBar.setX(schDetailWeekDay.getX());
        schDetailTopBar.getLayoutParams().width = schDetailWeekDay.getWidth();
    }
}
