package univ.sm.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import univ.sm.R;

/**
 * Created by uaer on 2017-01-13.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    Context context;
    ArrayList<Shuttle> items;
    int directionFlag = 0;
    String pivotTime,startTitle,middleTitle,endTitle;
    /*정방향 역방향 레이아웃 구분*/
    int[] directionLayout = {
            R.layout.sch_detail_holder_opposite,
            R.layout.sch_detail_holder_reverse
    };

    public RecyclerAdapter(Context context, ArrayList<Shuttle> items, int directionFlag) {
        this.context = context;
        this.items = items;
        this.directionFlag = directionFlag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(directionLayout[directionFlag],null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Shuttle item = items.get(position+1);
        if(position == 0){
            item = items.get(0);
            pivotTime=item.getB()[0];
            startTitle=item.getB()[0];
            middleTitle=item.getB()[2];
            endTitle=item.getB()[4];
            item = items.get(position+1);
        }
        holder.indx.setText(item.getNo());
        holder.pivotTime.setText(pivotTime);
        holder.textSchduleFirst.setText(item.getB()[0]+" "+startTitle);
        holder.textSchduleSecond.setText(item.getB()[2]+" "+middleTitle);
        holder.textSchduleThird.setText(item.getB()[4]+" "+endTitle);
    }

    @Override
    public int getItemCount() {
        return this.items.size()-1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView pivotTime,textSchduleFirst,textSchduleSecond,textSchduleThird,indx;

        public ViewHolder(View itemView) {
            super(itemView);
            if(directionFlag == 0){
                pivotTime=(TextView)itemView.findViewById(R.id.pivotTime_opposite);
                textSchduleFirst=(TextView)itemView.findViewById(R.id.textSchduleFirst_opposite);
                textSchduleSecond=(TextView)itemView.findViewById(R.id.textSchduleSecond_opposite);
                textSchduleThird=(TextView)itemView.findViewById(R.id.textSchduleThird_opposite);
                indx=(TextView)itemView.findViewById(R.id.indx_opposite);
            }else{
                pivotTime=(TextView)itemView.findViewById(R.id.pivotTime_reverse);
                textSchduleFirst=(TextView)itemView.findViewById(R.id.textSchduleFirst_reverse);
                textSchduleSecond=(TextView)itemView.findViewById(R.id.textSchduleSecond_reverse);
                textSchduleThird=(TextView)itemView.findViewById(R.id.textSchduleThird_reverse);
                indx=(TextView)itemView.findViewById(R.id.indx_reverse);
            }
        }
    }
}
