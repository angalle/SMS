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
    int item_layout;

    public RecyclerAdapter(Context context, ArrayList<Shuttle> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sch_detail_holder,null);
        return new ViewHolder(v);
    }
    String title,startTitle,middleTitle,endTitle;
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Shuttle item = items.get(position+1);
        if(position == 0){
            item = items.get(0);
            title=item.getB()[0];
            startTitle=item.getB()[0];
            middleTitle=item.getB()[2];
            endTitle=item.getB()[4];
            item = items.get(position+1);
        }
        holder.indx.setText(item.getNo());
        holder.pivotTime.setText(item.getB()[0]);
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
            pivotTime=(TextView)itemView.findViewById(R.id.pivotTime);
            textSchduleFirst=(TextView)itemView.findViewById(R.id.textSchduleFirst);
            textSchduleSecond=(TextView)itemView.findViewById(R.id.textSchduleSecond);
            textSchduleThird=(TextView)itemView.findViewById(R.id.textSchduleThird);
            indx=(TextView)itemView.findViewById(R.id.indx);
        }
    }
}
