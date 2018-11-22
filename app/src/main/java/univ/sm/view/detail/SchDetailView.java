package univ.sm.view.detail;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import univ.sm.util.AnimationUtil;
import univ.sm.R;
import univ.sm.StaticData;
import univ.sm.model.Const;
import univ.sm.view.detail.adapter.detailAdapter;
import univ.sm.model.Utility;
import univ.sm.model.shuttle.Shuttle;
import univ.sm.view.CommonView;

/**
 * Created by heesun on 2016-12-13.
 */

public class SchDetailView extends CommonView implements View.OnClickListener,ViewTreeObserver.OnGlobalLayoutListener,Spinner.OnItemSelectedListener{
    ImageView schDetailTopBar,  quickBtn,   changeDirection;
    TextView schDetailWeekDay,  schDetailSatureDay, schDetailSunDay;
    Spinner destination;

    RecyclerView recyclerView;

    int roatationDegree_quick = 0;
    int roatationDegree_change = 0;
    Context context;

    ArrayList<int[]> STATION;
    ArrayList<Shuttle>[] changeTemp;
    detailAdapter ra;

    /*TERMINAL_C // ONYANG_C*/
    String STATION_FLAG = Const.CHEONAN_ASAN_ST_000;
    /*SATURDAY // SUNDAY*/
    String DAY_FLAG = Const.WEK;
    /* OPPOSIT // REVERSE*/
    int DIRECTION_FLAG = Const.OPPOSIT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_detail);

        /* view 초기화 */
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        context = getApplicationContext();
        try{
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            ra = new detailAdapter(context,StaticData.getArrShuttle(Const.CHEONAN_ASAN_ST_000,Const.WEK), Const.OPPOSIT);
            recyclerView.setAdapter(ra);
        }catch (Exception e){
            Toast.makeText(getApplication(),"인터넷이 연결되지 않았거나, 데이터를 받아오지 못하였습니다.",Toast.LENGTH_SHORT).show();
        }
    }

    private void initView(){
         /*주말/토요일/일요일 텍스트 이벤트*/
        schDetailWeekDay = (TextView) findViewById(R.id.sch_detail_weekDay);
        schDetailSatureDay = (TextView) findViewById(R.id.sch_detail_satureDay);
        schDetailSunDay = (TextView) findViewById(R.id.sch_detail_sunDay);


        /* 역방향 기능 빨리찾기 기능 */
        quickBtn = (ImageView) findViewById(R.id.quickBtn);
        changeDirection = (ImageView) findViewById(R.id.changeDirection);

        Picasso.with(this).load(R.drawable.quick_btn).resize(100,110).into(quickBtn);


        /* 각 버튼 마다 이벤트 리스너*/
        quickBtn.setOnClickListener(this);
        changeDirection.setOnClickListener(this);
        schDetailWeekDay.setOnClickListener(this);
        schDetailSatureDay.setOnClickListener(this);
        schDetailSunDay.setOnClickListener(this);
        //destination.setOnClickListener(this);


        /* 기본좌표 재 설정 / xml 상에서 정확한 좌표를 표시 할 수 없음*/
        schDetailTopBar = (ImageView) findViewById(R.id.sch_detail_top_bar);
        schDetailTopBar.getViewTreeObserver().addOnGlobalLayoutListener(this);

        /*리스트뷰*/
        recyclerView = (RecyclerView) findViewById(R.id.sch_entry_list);

        /*Spinner*/
        destination = (Spinner) findViewById(R.id.destination);
        destination.setOnItemSelectedListener(this);
        ArrayAdapter<?> a8Adapter = ArrayAdapter.createFromResource(
                this, R.array.destination, R.layout.spinner_item);
        a8Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destination.setAdapter(a8Adapter);
    }



    /*GlobalLayout을 사용할때 계속 호출 되는 경우가 있는데 Listener를 remove 시켜줘야 계속 호출이 반복되지 않음.*/
    private static void removeOnGlobalLayoutListener(ViewTreeObserver observer, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (observer == null) {
            return ;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            observer.removeGlobalOnLayoutListener(listener);
        } else {
            observer.removeOnGlobalLayoutListener(listener);
        }
    }

    @Override
    public void onClick(final View v) {
        /*평일 기능*/
        if(v.getId() == R.id.sch_detail_weekDay){
            AnimationUtil.moveImageBar(v,schDetailTopBar);
            DAY_FLAG = Const.WEK;
            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            /*토요일 기능*/
        }else if(v.getId() == R.id.sch_detail_satureDay){
            AnimationUtil.moveImageBar(v,schDetailTopBar);
            DAY_FLAG = Const.SAT;
            changeShuttleArr(STATION_FLAG,DAY_FLAG,DIRECTION_FLAG);
            /*일요일 기능*/
        }else if(v.getId() == R.id.sch_detail_sunDay) {
            AnimationUtil.moveImageBar(v,schDetailTopBar);
            DAY_FLAG = Const.SUN;
            changeShuttleArr(STATION_FLAG,DAY_FLAG,DIRECTION_FLAG);
            /*빨리찾기 기능 구현*/
        }else if(v.getId() == R.id.quickBtn){
            roatationDegree_quick += 720;
            ObjectAnimator rotation = ObjectAnimator.ofFloat(v,"rotation", roatationDegree_quick).setDuration(1000);
            rotation.setRepeatCount(Animation.ABSOLUTE);
            rotation.start();

            findQuickTime();
            /*반대방향 구현*/
        }else if(v.getId() == R.id.changeDirection){
            roatationDegree_change += 180;
            ObjectAnimator rotation = ObjectAnimator.ofFloat(v,"rotationY", roatationDegree_change).setDuration(500);
            rotation.setRepeatCount(Animation.ABSOLUTE);
            rotation.start();

            if(DIRECTION_FLAG == Const.OPPOSIT){
                DIRECTION_FLAG = Const.REVERSE;
            }else{
                DIRECTION_FLAG = Const.OPPOSIT;
            }
            changeShuttleArr(STATION_FLAG,DAY_FLAG,DIRECTION_FLAG);
        }
    }

    private void changeShuttleArr(String staionIndex,String dayIndex,int const_direction){
        STATION_FLAG = staionIndex;
        DAY_FLAG = dayIndex;
        DIRECTION_FLAG = const_direction;
        if(STATION_FLAG.equals(Const.ONYANG_CAMPAUSE_ST_002)  && !Const.WEK.equals(DAY_FLAG)){
            Toast.makeText(getApplicationContext(),"주말 운행은 하지 않습니다.",Toast.LENGTH_SHORT).show();
        }else{
            ra = new detailAdapter(context,StaticData.getArrShuttle(STATION_FLAG,DAY_FLAG), DIRECTION_FLAG);
            recyclerView.setAdapter(ra);
        }
    }

    private void findQuickTime() {
        /*  Todo : 가장빠른 시간을 가져오기 -  미해결 이슈 : 인덱스의 view의 색상을 변경해야 하는 이슈    */
        int index = ra.getMostFastIndex()+1;
        if(index >= ra.getItemCount()) {
            recyclerView.smoothScrollToPosition(0);
            Toast.makeText(getApplicationContext(), "막차시간이 끝났습니다.", Toast.LENGTH_SHORT).show();
        }else{
            recyclerView.smoothScrollToPosition(index);
            Toast.makeText(getApplicationContext(), "가장 빠른시간은 " + (index + 1) + "번 입니다.", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0){
            STATION_FLAG = Const.CHEONAN_ASAN_ST_000;
        }else if(position == 1){
            STATION_FLAG = Const.CHEONAN_TERMINAL_ST_001;
        }else if(position == 2){
            STATION_FLAG = Const.ONYANG_CAMPAUSE_ST_002;
        }

        changeShuttleArr(STATION_FLAG,DAY_FLAG,DIRECTION_FLAG);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /*onCreate에서 먹지 않는 setWidth,getWidth 등의 함수들을 이 안에서 구현이 가능 하다.*/
    @Override
    public void onGlobalLayout() {
        if(Utility.getCurrentDate2int()==1){
            schDetailSunDay.performClick();
        }else if(Utility.getCurrentDate2int()==7){
            schDetailSatureDay.performClick();
        }else {
            schDetailWeekDay.performClick();
        }
        removeOnGlobalLayoutListener(schDetailTopBar.getViewTreeObserver(), this);
    }


}
