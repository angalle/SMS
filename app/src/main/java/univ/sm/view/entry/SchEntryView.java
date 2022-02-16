package univ.sm.view.entry;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.github.aakira.expandablelayout.ExpandableLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import univ.sm.R;
import univ.sm.StaticData;
import univ.sm.model.Const;
import univ.sm.view.entry.adapter.EntryRecyclerAdapter;
import univ.sm.model.shuttle.Shuttle;
import univ.sm.model.Utility;
import univ.sm.view.CommonView;

/**
 * Created by heesun on 2016-12-13.
 */

public class SchEntryView extends CommonView implements View.OnClickListener,ViewTreeObserver.OnGlobalLayoutListener{
    TextView schDetailWeekDay,  schDetailSatureDay,  schDetailSunDay, KTX, Terminal,  Onyang, destination_title;
    ImageView schDetailTopBar,  detail_btn, top_btn, imgDestination;
    Context context;
    ArrayList<Shuttle>[] changeTemp;
    RecyclerView recyclerView;
    EntryRecyclerAdapter ra;
    private AdView mAdView;

    //LinearLayout animationBox;
    int originHeight;
    boolean openFlag = false;

//    ExpandableLayout expandableLayout;

    ArrayList<int[]> STATION;
    /*TERMINAL_C // ONYANG_C*/
    String STATION_FLAG = Const.CHEONAN_ASAN_ST_000;
    /*SATURDAY // SUNDAY*/
    String DAY_FLAG = Const.WEK;
    /* OPPOSIT REVERSE*/
    int DIRECTION_FLAG = Const.OPPOSIT;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_entry);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        context = getApplicationContext();
        try{
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            ra = new EntryRecyclerAdapter(context, StaticData.getArrShuttle(Const.CHEONAN_ASAN_ST_000,Const.WEK), Const.OPPOSIT);
            recyclerView.setAdapter(ra);
        }catch (Exception e){
            Toast.makeText(getApplication(),"인터넷이 연결되지 않았거나, 데이터를 받아오지 못하였습니다.",Toast.LENGTH_SHORT).show();
        }
    }

    int destinationWitdh = 800;
    int destinationHeight = 50;

    @Override
    public void onClick(View v) {
        float toX = 0, toWidth = 0;      /* 이동해야할 x좌표 */
        if (v.getId() == R.id.sch_entry_weekDay) {
            DAY_FLAG = Const.WEK;
            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            moveImageBar(v);
        } else if (v.getId() == R.id.sch_entry_satureDay) {
            DAY_FLAG = Const.SAT;
            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            moveImageBar(v);
        } else if (v.getId() == R.id.sch_entry_sunDay) {
            DAY_FLAG = Const.SUN;
            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            moveImageBar(v);
        } else if (v.getId() == R.id.detail_btn) {
            if(openFlag){
//                expandableLayout.collapse();
                openFlag = false;
            }else if(!openFlag){
//                expandableLayout.expand();
                openFlag = true;
            }
        }else if (v.getId() == R.id.KTX) {
            STATION_FLAG = Const.CHEONAN_ASAN_ST_000;
            //Picasso.with(this).load(R.drawable.sch_detail_cheonan).resize(destinationWitdh,destinationHeight).into(imgDestination);
            //Picasso.with(this).load(R.drawable.sch_detail_cheonan).fit().centerCrop().into(imgDestination);
            imgDestination.setImageResource(R.drawable.sch_detail_cheonan);
            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            destination_title.setText(Const.CHEONANSTATION_STR);
        } else if (v.getId() == R.id.Terminal) {
            STATION_FLAG = Const.CHEONAN_TERMINAL_ST_001;
            //Picasso.with(this).load(R.drawable.sch_detail_terminal).resize(destinationWitdh,destinationHeight).into(imgDestination);
            //Picasso.with(this).load(R.drawable.sch_detail_terminal).fit().centerCrop().into(imgDestination);
            imgDestination.setImageResource(R.drawable.sch_detail_terminal);
            changeShuttleArr(STATION_FLAG,DAY_FLAG, DIRECTION_FLAG);
            destination_title.setText(Const.TERMINALSTATION_STR);
        } else if (v.getId() == R.id.Onyang) {
            STATION_FLAG = Const.ONYANG_CAMPAUSE_ST_002;
            /* 온양시간은 동적*/
            if(StaticData.getArrShuttle(Const.CHEONAN_ASAN_ST_000,Const.WEK).size() == 0)
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
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView = (AdView)findViewById(R.id.adView_entry);
        mAdView.loadAd(adRequest);
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
//        expandableLayout = (ExpandableLayout)findViewById(R.id.expandableLayout);
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


    private void changeShuttleArr(String station,String day,int const_direction){
        STATION_FLAG =station;
        DAY_FLAG = day;
        DIRECTION_FLAG = const_direction;
        ra = new EntryRecyclerAdapter(context, StaticData.getArrShuttle(STATION_FLAG,DAY_FLAG), Const.OPPOSIT);
        recyclerView.setAdapter(ra);

        if(STATION_FLAG.equals(Const.ONYANG_CAMPAUSE_ST_002)  && !Const.WEK.equals(DAY_FLAG)){
            Toast.makeText(getApplicationContext(),"주말 운행은 하지 않습니다.",Toast.LENGTH_SHORT).show();
        }else{
            ra = new EntryRecyclerAdapter(context, StaticData.getArrShuttle(STATION_FLAG,DAY_FLAG), Const.OPPOSIT);
            recyclerView.setAdapter(ra);
            //접혔다 폈다하는 레이아웃 동작/변수
//            expandableLayout.collapse();
            openFlag = false;
        }
    }
}
