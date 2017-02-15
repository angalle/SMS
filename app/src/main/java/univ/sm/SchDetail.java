package univ.sm;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;

import univ.sm.connect.Connection;
import univ.sm.data.Const;
import univ.sm.data.RecyclerAdapter;
import univ.sm.data.Shuttle;

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

    ArrayList<int[]> STATION;
    ArrayList<Shuttle>[] changeTemp;
    RecyclerAdapter ra;

    /*TERMINAL_C // ONYANG_C*/
    int STATION_FLAG = Const.CHEONANSTATION_C;
    /*SATURDAY // SUNDAY*/
    int DAY_FLAG = Const.WEEKDAY;
    /* OPPOSIT // REVERSE*/
    int DIRECTION_FLAG = Const.OPPOSIT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_detail);
        initView();
        context = getApplicationContext();
        changeTemp= Connection.positionShuttleArr;

        STATION = new ArrayList<>();
        /*역 관련 상수 초기화*/
        STATION.add(Const.CHEONANSTATION);
        STATION.add(Const.TERMINAL);
        STATION.add(Const.ONYANG);
        /*STATION.add(Const.CHEONANCAMPUS);*/

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        ra = new RecyclerAdapter(context,changeTemp[STATION.get(STATION_FLAG)[DAY_FLAG]], Const.OPPOSIT);
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


        /* 역방향 기능 빨리찾기 기능 */
        quickBtn = (ImageView) findViewById(R.id.quickBtn);
        changeDirection = (ImageView) findViewById(R.id.changeDirection);

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

    /*onCreate에서 먹지 않는 setWidth,getWidth 등의 함수들을 이 안에서 구현이 가능 하다.*/
    @Override
    public void onGlobalLayout() {
        schDetailTopBar.setX(schDetailWeekDay.getX());
        schDetailTopBar.getLayoutParams().width = schDetailWeekDay.getWidth();
        removeOnGlobalLayoutListener(schDetailTopBar.getViewTreeObserver(), this);
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
            moveImageBar(v);
            DAY_FLAG = Const.WEEKDAY;
            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            /*토요일 기능*/
        }else if(v.getId() == R.id.sch_detail_satureDay){
            moveImageBar(v);
            DAY_FLAG = Const.SATUREDAY;
            changeShuttleArr(STATION_FLAG,DAY_FLAG,DIRECTION_FLAG);
            /*일요일 기능*/
        }else if(v.getId() == R.id.sch_detail_sunDay) {
            moveImageBar(v);
            DAY_FLAG = Const.SUNDAY;
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

    private void changeShuttleArr(int staionIndex,int dayIndex,int const_direction){
        STATION_FLAG = staionIndex;
        DAY_FLAG = dayIndex;
        DIRECTION_FLAG = const_direction;

        if(STATION_FLAG > Const.TERMINAL_C && DAY_FLAG > Const.WEEKDAY){
            Toast.makeText(getApplicationContext(),"주말 운행은 하지 않습니다.",Toast.LENGTH_SHORT).show();
        }else{
            ra = new RecyclerAdapter(context,changeTemp[STATION.get(STATION_FLAG)[DAY_FLAG]], DIRECTION_FLAG);
            recyclerView.setAdapter(ra);
        }
    }

    private void findQuickTime() {
        /*Todo : 가장빠른 시간을 가져오기*/
        //recyclerView.getChildAt(ra.getMostFastIndex()).setBackgroundColor(Color.parseColor("#f7f7f7"));
        //recyclerView.getChildAt(ra.getMostFastIndex() - 4).setBackgroundColor(Color.parseColor("#f7f7f7"));
        //recyclerView.swapAdapter(ra, true);
        //recyclerView.smoothScrollBy();
        int index = ra.getMostFastIndex();
        recyclerView.scrollToPosition(index);
        Toast.makeText(getApplicationContext(),"가장 빠른시간은 "+(index+1)+"번 입니다.",Toast.LENGTH_SHORT).show();
        //System.out.println("index1::::"+recyclerView.findViewHolderForAdapterPosition(index).itemView.getHeight());
        //System.out.println("index2::::"+recyclerView.findViewHolderForLayoutPosition(index).itemView.getHeight());
        //System.out.println("index3::::"+recyclerView.findViewHolderForPosition(index).itemView.getHeight());

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        STATION_FLAG = position;
        changeShuttleArr(STATION_FLAG,DAY_FLAG,DIRECTION_FLAG);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
