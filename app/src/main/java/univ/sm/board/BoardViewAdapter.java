package univ.sm.board;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import univ.sm.R;
import univ.sm.connect.LoopjConnection;

public class BoardViewAdapter extends RecyclerView.Adapter<BoardViewAdapter.BaseViewHolder> {
    private List<Post> postArrayList = new ArrayList<>();    //List<Post> items = new ArrayList<>;
    private Context context;

    public BoardViewAdapter(List<Post> mItems, Context context) {
        this.postArrayList = mItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindView(postArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public void setBoardArrayList(List<Post> boardArrayList){
        //어뎁터 board 리스트 새로 받아오기
        postArrayList.clear();
        postArrayList.addAll(boardArrayList);
        notifyDataSetChanged();
    }

    public abstract class BaseViewHolder<ITEM> extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void onBindView(ITEM item);
    }

    public class ViewHolder extends BaseViewHolder<Post> {

        private ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 디테일 페이지로 이동
                    Intent boardIntent = new Intent();
                    boardIntent.setClass(context, BoardDetailPage.class);
                    boardIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    boardIntent.putExtra("position", getLayoutPosition());
                    boardIntent.putExtra("board_no", postArrayList.get(getLayoutPosition()).getBoard_no());
                    context.startActivity(boardIntent);

                }
            });
        }

        @Override
        public void onBindView(Post item) {
            TextView DEPARTURE = (TextView) itemView.findViewById(R.id.DEPARTURE);//출발지
            TextView DESTINATION = (TextView) itemView.findViewById(R.id.DESTINATION);//도착지
            TextView PASSENGER_NUM = (TextView) itemView.findViewById(R.id.passengerNum);
            TextView WRITE_NAME = (TextView) itemView.findViewById(R.id.name);
            TextView WAIT_TIME = (TextView) itemView.findViewById(R.id.waitTime);

            WAIT_TIME.setText(item.getRemain_time());
            DEPARTURE.setText(item.getDeparture());
            DESTINATION.setText(item.getDestination());
            PASSENGER_NUM.setText("총 "+item.getPassenger_num());
            WRITE_NAME.setText(item.getWrite_name());
        }
    }

}
