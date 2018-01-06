package univ.sm.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import univ.sm.R;
import univ.sm.data.item.Shuttle;

/**
 * Created by uaer on 2017-01-13.
 */
public class EntryRecyclerAdapter extends RecyclerView.Adapter<EntryRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<Shuttle> items;

    public void setItems(ArrayList<Shuttle> items) {
        this.items = items;
    }

    int directionFlag = 0;
    String pivotTime,startTitle,middleTitle,endTitle;

    public EntryRecyclerAdapter(Context context, ArrayList<Shuttle> items, int directionFlag) {
        this.context = context;
        this.items = items;
        this.directionFlag = directionFlag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sch_entry_holder,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Shuttle item = items.get(position+1);
        String[] tempTime = new String[5];
        for(int i =0; i<5; i++){
            tempTime[i] = item.getB()[i].replace("(금Ⅹ)","\n(금Ⅹ)");
        }

        holder.entry_one.setText(tempTime[0]);
        holder.entry_two.setText(tempTime[1]);
        holder.entry_three.setText(tempTime[2]);
        holder.entry_four.setText(tempTime[3]);
        holder.entry_five.setText(tempTime[4]);

    }

    @Override
    public int getItemCount() {
        return this.items == null ? 0 : this.items.size()-1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView entry_one,entry_two,entry_three,entry_four,entry_five;

        public ViewHolder(View itemView) {
            super(itemView);
            entry_one=(TextView)itemView.findViewById(R.id.entry_one);
            entry_two=(TextView)itemView.findViewById(R.id.entry_two);
            entry_three=(TextView)itemView.findViewById(R.id.entry_three);
            entry_four=(TextView)itemView.findViewById(R.id.entry_four);
            entry_five=(TextView)itemView.findViewById(R.id.entry_five);
        }
    }
    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }
    public void addAll(ArrayList<Shuttle> list){
        items.addAll(list);
        notifyDataSetChanged();
    }
}
