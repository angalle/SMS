package univ.sm.view.detail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import univ.sm.model.Const;
import univ.sm.R;
import univ.sm.model.shuttle.Shuttle;

/**
 * Created by uaer on 2017-01-13.
 */
public class detailAdapter extends RecyclerView.Adapter<detailAdapter.ViewHolder>{
    Context context;
    ArrayList<Shuttle> items;
    ArrayList<String> compareString;
    ViewHolder vholder;
    public void setItems(ArrayList<Shuttle> items) {
        this.items = items;
    }

    int directionFlag = 0;
    String pivotTime,startTitle,middleTitle,endTitle;
    /*정방향 역방향 레이아웃 구분*/
    int[] directionLayout = {
            R.layout.sch_detail_holder_opposite,
            R.layout.sch_detail_holder_reverse
    };

    @Override
    public long getItemId(int position) {
        return position;
    }

    public detailAdapter(Context context, ArrayList<Shuttle> items, int directionFlag) {
        setHasStableIds(true);
        this.context = context;
        this.items = items;
        this.directionFlag = directionFlag;

        compareString = new ArrayList<String>();
        if(directionFlag == 0){
            for(int i=1; i<items.size();i++){
                compareString.add(items.get(i).getST_ONE());
            }
        }else{
            for(int i=1; i<items.size();i++){
                compareString.add(items.get(i).getST_TRE());
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(directionLayout[directionFlag],parent,false);
        vholder = new ViewHolder(v);
        return vholder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Shuttle item = items.get(position);

        if(position == 0){
            setViewHolderTitle(item.getSTOP_SITE_CODE(),directionFlag);
        }

        holder.indx.setText((position+1)+"");
        String tempPivotTime="";

        String tempMiddleTime="";
        if(directionFlag == 0){
            tempPivotTime = splite_5Length(item.getST_ONE());     //변수의 길이를 5길이까지 검사해서 자르는 메소드
            tempMiddleTime = filter_10Min(item.getST_TWO(),item.getST_ONE());      //중간 인덱스의 "10분"스트링값을 치환하는 메소드
        }else{
            tempPivotTime = splite_5Length(item.getST_TRE());     //변수의 길이를 5길이까지 검사해서 자르는 메소드
            tempMiddleTime = filter_10Min(item.getST_FOR(),item.getST_TRE());      //중간 인덱스의 "10분"스트링값을 치환하는 메소드
        }

        Log.e("tempPivotTime:::",""+tempPivotTime);

        holder.pivotTime.setText(tempPivotTime);
        holder.detail_line.setImageResource(R.drawable.line_point_s);

        if(directionFlag == 0){
            holder.textSchduleFirst.setText         (item.getST_ONE()+" "+startTitle);
            holder.textSchduleSecond.setText        (tempMiddleTime+" "+middleTitle);
            holder.textSchduleThird.setText         (item.getST_TRE()+" "+endTitle);
        }else{
            holder.textSchduleFirst.setText         (endTitle+" "+item.getST_TRE());
            holder.textSchduleSecond.setText        (middleTitle+" "+tempMiddleTime);
            holder.textSchduleThird.setText         (startTitle+" "+item.getST_FIV());
        }
    }

    public void setViewHolderTitle(String stopSitecode,int directionFlag){
        if(Const.CHEONAN_ASAN_ST_000.equals(stopSitecode) ){
            if(directionFlag == Const.OPPOSIT){
                pivotTime   =   Const.CHEONAN_ASAN[0];
                startTitle  =   Const.CHEONAN_ASAN[0];
                middleTitle =   Const.CHEONAN_ASAN[1];
                endTitle    =   Const.CHEONAN_ASAN[2];
            }
            if(directionFlag == Const.REVERSE){
                pivotTime   =   Const.CHEONAN_ASAN[2];
                startTitle  =   Const.CHEONAN_ASAN[2];
                middleTitle =   Const.CHEONAN_ASAN[3];
                endTitle    =   Const.CHEONAN_ASAN[4];
            }
        }
        if(Const.CHEONAN_TERMINAL_ST_001.equals(stopSitecode) ){
            if(directionFlag == Const.OPPOSIT){
                pivotTime   =   Const.CHEONAN_TERMINAL[0];
                startTitle  =   Const.CHEONAN_TERMINAL[0];
                middleTitle =   Const.CHEONAN_TERMINAL[1];
                endTitle    =   Const.CHEONAN_TERMINAL[2];
            }
            if(directionFlag == Const.REVERSE){
                pivotTime   =   Const.CHEONAN_TERMINAL[2];
                startTitle  =   Const.CHEONAN_TERMINAL[2];
                middleTitle =   Const.CHEONAN_TERMINAL[3];
                endTitle    =   Const.CHEONAN_TERMINAL[4];
            }
        }
        if(Const.ONYANG_CAMPAUSE_ST_002.equals(stopSitecode) ){
            if(directionFlag == Const.OPPOSIT){
                pivotTime   =   Const.ONYANG_TERMINAL[0];
                startTitle  =   Const.ONYANG_TERMINAL[0];
                middleTitle =   Const.ONYANG_TERMINAL[1];
                endTitle    =   Const.ONYANG_TERMINAL[2];
            }
            if(directionFlag == Const.REVERSE){
                pivotTime   =   Const.ONYANG_TERMINAL[2];
                startTitle  =   Const.ONYANG_TERMINAL[2];
                middleTitle =   Const.ONYANG_TERMINAL[3];
                endTitle    =   Const.ONYANG_TERMINAL[4];
            }
        }
    }

    public String filter_10Min(String compareStr, String addTime){
        Log.e("compareStr :::", compareStr);
        Log.e("addTime :::", addTime);
        String tempMiddleTime = "";
        DateFormat df = new SimpleDateFormat("H:mm");
        if("10분".contains(compareStr) || "10분예상".contains(compareStr)) {
            try {
                Date date = df.parse(addTime);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.MINUTE, 10);
                tempMiddleTime = df.format(cal.getTime()) + "(예상)";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if("15분".contains(compareStr)) {
            try {
                Date date = df.parse(addTime);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.MINUTE, 15);
                tempMiddleTime = df.format(cal.getTime()) + "(예상)";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            //compareStr :: 이곳에 원래 들어가야할 시간
            //addTime  :: 이 시간에 + 10분 or + 15분 해야하는 시간.

            tempMiddleTime = compareStr;
        }
        return tempMiddleTime;
    }

    public String splite_5Length(String data){
        String tempPivotTime="";
        if(data.length() < 5){
            tempPivotTime = data;
        }else{
            tempPivotTime = data.substring(0,5);
        }

        if(tempPivotTime.contains("(")){
            tempPivotTime = tempPivotTime .replace("(","")+ "\n(금Ⅹ)";
        }

        return tempPivotTime;
    }

    @Override
    public int getItemCount() {
        return this.items == null ? 0 : this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView pivotTime,
                indx,
                textSchduleFirst,
                textSchduleSecond,
                textSchduleThird;

        ImageView detail_line;
        LinearLayout background_reverse,background_opposite;
        public ViewHolder(View itemView) {
            super(itemView);
            if(directionFlag == 0){
                background_opposite     = (LinearLayout)itemView.findViewById   (R.id.background_opposite);
                pivotTime               = (TextView)itemView.findViewById       (R.id.pivotTime_opposite);
                textSchduleFirst        = (TextView)itemView.findViewById       (R.id.textSchduleFirst_opposite);
                textSchduleSecond       = (TextView)itemView.findViewById       (R.id.textSchduleSecond_opposite);
                textSchduleThird        = (TextView)itemView.findViewById       (R.id.textSchduleThird_opposite);
                indx                    = (TextView)itemView.findViewById       (R.id.indx_opposite);
                detail_line             = (ImageView)itemView.findViewById      (R.id.detail_line_opp);
            }else{
                background_reverse      = (LinearLayout)itemView.findViewById   (R.id.background_reverse);
                pivotTime               = (TextView)itemView.findViewById       (R.id.pivotTime_reverse);
                textSchduleFirst        = (TextView)itemView.findViewById       (R.id.textSchduleFirst_reverse);
                textSchduleSecond       = (TextView)itemView.findViewById       (R.id.textSchduleSecond_reverse);
                textSchduleThird        = (TextView)itemView.findViewById       (R.id.textSchduleThird_reverse);
                indx                    = (TextView)itemView.findViewById       (R.id.indx_reverse);
                detail_line             = (ImageView)itemView.findViewById      (R.id.detail_line_rev);
            }
        }

        @Override
        public void onClick(View v) {
            if(directionFlag == 0){
                background_opposite.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else{
                background_reverse.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }
        }
    }

    public int getMostFastIndex() {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("HH:mm");
        //비교할 대상 str
        int compare_time = Integer.parseInt((dayTime.format(new Date(time))).replace(":",""));
        int index=0,startIndex=0;
        Iterator<String> tempString = compareString.iterator();

        /*가장 가까운 시간의 인덱스를 가져오는 방법*/
        while(tempString.hasNext()){
            String tempData = tempString.next().replace(":","");
            if("*".equals(tempData)){
                startIndex++;
                continue;
            }

            /*Log.e("compare_time::::",compare_time+"");
            Log.e("time::::",tempData.replace("(금X)",""));*/
            if(compare_time < Integer.parseInt(tempData.replace("(금Ⅹ)","").replace("(금X)","").replace("(예상)",""))){
                index = startIndex;
                break;
            }
            startIndex++;
        }

        return index;
    }

}
