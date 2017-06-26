package univ.sm;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import univ.sm.connect.Connection;
import univ.sm.data.Const;
import univ.sm.data.EntryRecyclerAdapter;
import univ.sm.data.RecyclerAdapter;
import univ.sm.data.Shuttle;
import univ.sm.data.Utility;

/**
 * Created by heesun on 2016-12-13.
 */

public class SchEntry extends CommonActivity implements View.OnClickListener,ViewTreeObserver.OnGlobalLayoutListener{
    TextView schDetailWeekDay,  schDetailSatureDay,  schDetailSunDay, KTX, Terminal,  Onyang, destination_title;
    ImageView schDetailTopBar,  detail_btn, top_btn, imgDestination;
    Context context;
    ArrayList<Shuttle>[] changeTemp;
    RecyclerView recyclerView;
    EntryRecyclerAdapter ra;

    //LinearLayout animationBox;
    int originHeight;
    boolean openFlag = false;

    ExpandableLayout expandableLayout;

    ArrayList<int[]> STATION;
    /*TERMINAL_C // ONYANG_C*/
    int STATION_FLAG = Const.CHEONANSTATION_C;
    /*SATURDAY // SUNDAY*/
    int DAY_FLAG = Const.WEEKDAY;
    /* OPPOSIT REVERSE*/
    int DIRECTION_FLAG = Const.OPPOSIT;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_entry);
        initView();

        context = getApplicationContext();
        changeTemp= Connection.positionShuttleArr;

        STATION = new ArrayList<>();
        /*역 관련 상수 초기화*/
        STATION.add(Const.CHEONANSTATION);
        STATION.add(Const.TERMINAL);
        STATION.add(Const.ONYANG);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        ra = new EntryRecyclerAdapter(context,changeTemp[STATION.get(STATION_FLAG)[DAY_FLAG]], Const.OPPOSIT);
        recyclerView.setAdapter(ra);
    }

    int destinationWitdh = 800;
    int destinationHeight = 50;

    @Override
    public void onClick(View v) {
        float toX = 0, toWidth = 0;      /* 이동해야할 x좌표 */
        if (v.getId() == R.id.sch_entry_weekDay) {
            DAY_FLAG = Const.WEEKDAY;
            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            moveImageBar(v);
        } else if (v.getId() == R.id.sch_entry_satureDay) {
            DAY_FLAG = Const.SATUREDAY;
            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            moveImageBar(v);
        } else if (v.getId() == R.id.sch_entry_sunDay) {
            DAY_FLAG = Const.SUNDAY;
            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            moveImageBar(v);
        } else if (v.getId() == R.id.detail_btn) {
            if(openFlag){
                expandableLayout.collapse();
                openFlag = false;
            }else if(!openFlag){
                expandableLayout.expand();
                openFlag = true;
            }
        }else if (v.getId() == R.id.KTX) {
            STATION_FLAG = Const.CHEONANSTATION_C;
            //Picasso.with(this).load(R.drawable.sch_detail_cheonan).resize(destinationWitdh,destinationHeight).into(imgDestination);
            //Picasso.with(this).load(R.drawable.sch_detail_cheonan).fit().centerCrop().into(imgDestination);
            imgDestination.setImageResource(R.drawable.sch_detail_cheonan);
            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            destination_title.setText(Const.CHEONANSTATION_STR);
        } else if (v.getId() == R.id.Terminal) {
            STATION_FLAG = Const.TERMINAL_C;
            //Picasso.with(this).load(R.drawable.sch_detail_terminal).resize(destinationWitdh,destinationHeight).into(imgDestination);
            //Picasso.with(this).load(R.drawable.sch_detail_terminal).fit().centerCrop().into(imgDestination);
            imgDestination.setImageResource(R.drawable.sch_detail_terminal);
            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            destination_title.setText(Const.TERMINALSTATION_STR);
        } else if (v.getId() == R.id.Onyang) {
            STATION_FLAG = Const.ONYANG_C;
            /* 온양시간은 동적*/
            if(changeTemp[STATION.get(STATION_FLAG)[DAY_FLAG]].get(1).getB()[4].contains(":"))
                //Picasso.with(this).load(R.drawable.sch_detail_onyang).resize(destinationWitdh,destinationHeight).into(imgDestination);
                imgDestination.setImageResource(R.drawable.sch_detail_onyang);
            else
                //Picasso.with(this).load(R.drawable.sch_detail_onyang_vacation).resize(destinationWitdh,destinationHeight).into(imgDestination);
                imgDestination.setImageResource(R.drawable.sch_detail_onyang_vacation);

            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            destination_title.setText(Const.ONYANGSTATION_STR);
        } else if(v.getId() == R.id.top_btn){
            recyclerView.scrollToPosition(0);
        }


    }
    /*주말선택의 빨간바를 이동하는 함수*/
    private void moveImageBar(View v){
        /* 이동해야할 x좌표 */
        float toX=v.getLeft();
        float toWidth=v.getWidth();

        float width = schDetailTopBar.getWidth();
        schDetailTopBar.animate().scaleX(toWidth / width);
        schDetailTopBar.animate().translationX(toX-(width-toWidth)/2.0f).withLayer();
    }

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


    private void initView(){
        /*주말/토요일/일요일 텍스트 이벤트*/
        destination_title = (TextView) findViewById(R.id.destination_title);
        schDetailWeekDay = (TextView) findViewById(R.id.sch_entry_weekDay);
        schDetailSatureDay = (TextView) findViewById(R.id.sch_entry_satureDay);
        schDetailSunDay = (TextView) findViewById(R.id.sch_entry_sunDay);
        detail_btn = (ImageView) findViewById(R.id.detail_btn);
        recyclerView = (RecyclerView) findViewById(R.id.sch_entry_list);
        top_btn = (ImageView) findViewById(R.id.top_btn);
        imgDestination = (ImageView) findViewById(R.id.imgDestination);

        KTX = (TextView) findViewById(R.id.KTX);
        Terminal = (TextView) findViewById(R.id.Terminal);
        Onyang = (TextView) findViewById(R.id.Onyang);
        //animationBox = (LinearLayout) findViewById(R.id.animationBox);
        expandableLayout = (ExpandableLayout)findViewById(R.id.expandableLayout);
        /* 각 버튼 마다 이벤트 리스너*/
        schDetailWeekDay.setOnClickListener(this);
        schDetailSatureDay.setOnClickListener(this);
        schDetailSunDay.setOnClickListener(this);
        detail_btn.setOnClickListener(this);
        top_btn.setOnClickListener(this);


        KTX.setOnClickListener(this);
        Terminal.setOnClickListener(this);
        Onyang.setOnClickListener(this);

        /* 기본좌표 재 설정 / xml 상에서 정확한 좌표를 표시 할 수 없음*/
        schDetailTopBar = (ImageView) findViewById(R.id.sch_entry_top_bar);
        schDetailTopBar.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }


    private void changeShuttleArr(int staionIndex,int dayIndex,int const_direction){
        STATION_FLAG =staionIndex;
        DAY_FLAG = dayIndex;
        DIRECTION_FLAG = const_direction;
        ra = new EntryRecyclerAdapter(context,changeTemp[STATION.get(STATION_FLAG)[DAY_FLAG]], DIRECTION_FLAG);
        recyclerView.setAdapter(ra);

        if(STATION_FLAG > Const.TERMINAL_C && DAY_FLAG > Const.WEEKDAY){
            Toast.makeText(getApplicationContext(),"주말 운행은 하지 않습니다.",Toast.LENGTH_SHORT).show();
        }else{
            ra = new EntryRecyclerAdapter(context,changeTemp[STATION.get(STATION_FLAG)[DAY_FLAG]], DIRECTION_FLAG);
            recyclerView.setAdapter(ra);
            //접혔다 폈다하는 레이아웃 동작/변수
            expandableLayout.collapse();
            openFlag = false;
        }
    }
}
