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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.commons.logging.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import univ.sm.connect.Connection;
import univ.sm.data.Const;
import univ.sm.data.RecyclerAdapter;
import univ.sm.data.Shuttle;
import univ.sm.data.SplashData;

import static univ.sm.Splash.positionShuttleArr;

/**
 * Created by heesun on 2016-12-13.
 */

public class SchDetail extends AppCompatActivity implements View.OnClickListener,ViewTreeObserver.OnGlobalLayoutListener,Spinner.OnItemSelectedListener{
    ImageView schDetailTopBar,  quickBtn,   changeDirection;
    TextView schDetailWeekDay,  schDetailSatureDay, schDetailSunDay;
    Spinner destination;
    RecyclerView recyclerView;
    int roatationDegree_quick = 0;
    int roatationDegree_change = 0;
    Context context;

    ArrayList<Shuttle>[] changeTemp= Splash.positionShuttleArr;
    RecyclerAdapter ra;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_detail);
        initView();
        context = getApplicationContext();
        //changeTemp = Splash.positionShuttleArr.clone();
        //System.arraycopy(Splash.positionShuttleArr, 0, changeTemp, 0, Splash.positionShuttleArr.length);
        /*Recycle view에 대한 설정*/
        //changeShuttleArr(0,Const.OPPOSIT);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        ra = new RecyclerAdapter(context,changeTemp[0], Const.OPPOSIT);
        //ra.addAll(changeTemp[0]);
        recyclerView.setAdapter(ra);
        changeShuttleArr(0,0);
    }

    private void changeShuttleArr(int ShuttleIdex,int const_direction){
        ra = new RecyclerAdapter(context,changeTemp[ShuttleIdex], Const.OPPOSIT);
        recyclerView.setAdapter(ra);
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
        destination = (Spinner) findViewById(R.id.destination);


        quickBtn = (ImageView) findViewById(R.id.quickBtn);
        changeDirection = (ImageView) findViewById(R.id.changeDirection);

        /* 각 버튼 마다 이벤트 리스너*/
        quickBtn.setOnClickListener(this);
        changeDirection.setOnClickListener(this);
        schDetailWeekDay.setOnClickListener(this);
        schDetailSatureDay.setOnClickListener(this);
        schDetailSunDay.setOnClickListener(this);
        //destination.setOnClickListener(this);
        destination.setOnItemSelectedListener(this);

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

    @Override
    public void onClick(final View v) {
        /*평일 기능*/
        if(v.getId() == R.id.sch_detail_weekDay){
            moveImageBar(v);
            startThread(this,0);
            //changeShuttleArr(0,Const.OPPOSIT);
            /*토요일 기능*/
        }else if(v.getId() == R.id.sch_detail_satureDay){
            moveImageBar(v);
            startThread(this,7);
            //changeShuttleArr(7,Const.OPPOSIT);
            /*일요일 기능*/
        }else if(v.getId() == R.id.sch_detail_sunDay) {
            moveImageBar(v);
            startThread(this,8);
            //changeShuttleArr(8,Const.OPPOSIT);
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
        }else if(v.getId() == R.id.destination ){

        }
    }

    public void startThread(SchDetail schDetail,final int indx){
        schDetail.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                changeShuttleArr(indx,Const.OPPOSIT);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
