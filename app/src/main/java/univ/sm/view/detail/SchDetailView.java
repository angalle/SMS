package univ.sm.view.detail;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import java.util.HashMap;

import univ.sm.R;
import univ.sm.connect.Connection;
import univ.sm.connect.api.schdule.SchCall;
import univ.sm.connect.api.schdule.SchCallbakService;
import univ.sm.connect.api.schdule.SchService;
import univ.sm.data.Const;
import univ.sm.data.RecyclerAdapter;
import univ.sm.data.item.Shuttle;
import univ.sm.data.Utility;
import univ.sm.view.CommonView;
import univ.sm.view.SplashView;

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
    RecyclerAdapter ra;

    /*TERMINAL_C // ONYANG_C*/
    int STATION_FLAG = Const.CHEONANSTATION_C;
    /*SATURDAY // SUNDAY*/
    int DAY_FLAG = Const.WEEKDAY;
    /* OPPOSIT // REVERSE*/
    int DIRECTION_FLAG = Const.OPPOSIT;

    private SchService scheduleApi;
    SchCall schCallBack;

    private HashMap<String,Object> params;

    SchCallbakService schCallbakService = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_detail);

        scheduleApi = SchService.getInstance(this).createApi();
        schCallbakService = new SchCallbakService();

        params = new HashMap<String,Object>();
        //params.put("POSITION_FLAG","000");
        //params.put("WEEK_FLAG","WEK");
        Log.e("SchCall ::::::","call data");

        scheduleApi.getSchedule(params,schCallbakService);
        Log.e("SchCall ::::::","call data" + schCallbakService.getArrShuttle());

        /* view 초기화 */
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        context = getApplicationContext();
        changeTemp= Connection.positionShuttleArr;
        if(changeTemp == null){
            SplashView.DataSetting();
            changeTemp= Connection.positionShuttleArr;
        }
        STATION = new ArrayList<>();
        /*역 관련 상수 초기화*/
        STATION.add(Const.CHEONANSTATION);
        STATION.add(Const.TERMINAL);
        STATION.add(Const.ONYANG);
        /*STATION.add(Const.CHEONANCAMPUS);*/

        try{
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            ra = new RecyclerAdapter(context,changeTemp[STATION.get(STATION_FLAG)[DAY_FLAG]], Const.OPPOSIT);
            recyclerView.setAdapter(ra);
        }catch (Exception e){
            Toast.makeText(getApplication(),"인터넷이 연결되지 않았거나, 데이터를 받아오지 못하였습니다.",Toast.LENGTH_SHORT).show();
        }

    }

    /* 주말선택의 빨간바를 이동하는 함수 */
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
        if(changeTemp == null){
            SplashView.DataSetting();
            changeTemp= Connection.positionShuttleArr;
        }

        STATION_FLAG = staionIndex;
        DAY_FLAG = dayIndex;
        DIRECTION_FLAG = const_direction;

        if(STATION_FLAG > Const.TERMINAL_C && DAY_FLAG > Const.WEEKDAY){
            Toast.makeText(getApplicationContext(),"주말 운행은 하지 않습니다.",Toast.LENGTH_SHORT).show();
        }else{
            if(changeTemp == null){
                SplashView.DataSetting();
                changeTemp= Connection.positionShuttleArr;
            }
            ra = new RecyclerAdapter(context,changeTemp[STATION.get(STATION_FLAG)[DAY_FLAG]], DIRECTION_FLAG);
            recyclerView.setAdapter(ra);
        }
    }

    private void findQuickTime() {
        /*Todo : 가장빠른 시간을 가져오기 -  미해결 이슈 : 인덱스의 view의 색상을 변경해야 하는 이슈*/
        int index = ra.getMostFastIndex();
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
        STATION_FLAG = position;
        changeShuttleArr(STATION_FLAG,DAY_FLAG,DIRECTION_FLAG);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
