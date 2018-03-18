package univ.sm.View.board.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import univ.sm.R;
import univ.sm.Model.board.Board;
import univ.sm.View.board.detail.BoardDetailView;

public class BoardViewAdapter extends RecyclerView.Adapter<BoardViewAdapter.BaseViewHolder> {
    @BindView(R.id.WRITER) TextView WRITER;
    @BindView(R.id.department) TextView DEPARTMENT;
    @BindView(R.id.DEPARTURE) TextView DEPARTURE;
    @BindView(R.id.DESTINATION) TextView DESTINATION;
    @BindView(R.id.passengerNum) TextView PASSENGER_NUM;
    @BindView(R.id.waitTime) TextView WAIT_TIME;
    @BindView(R.id.cmntCnt) TextView COMMENT_CNT;

    @BindView(R.id.wait_time_img) ImageView waitTimeImg;

    private static final String TAG = "BoardViewAdapter";
    private List<Board> postArrayList = new ArrayList<>();    //List<Board> items = new ArrayList<>;
    private Context context;
    private Activity activity;

    public BoardViewAdapter(List<Board> mItems, Activity activity) {
        this.postArrayList = mItems;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item_view, parent, false);
        ButterKnife.bind(this,itemView);
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

    public class ViewHolder extends BaseViewHolder<Board> {
        private ViewHolder(View itemView) {
                    super(itemView);
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 디테일 페이지로 이동
                            Intent boardIntent = new Intent();
                            //boardIntent.setClass(context, BoardDetailView.class);
                            boardIntent.setClass(activity, BoardDetailView.class);
                            boardIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            boardIntent.putExtra("position", getLayoutPosition());
                            boardIntent.putExtra("board_no", postArrayList.get(getLayoutPosition()).getBoard_no());
                            Log.i(TAG, "position : " + getLayoutPosition() + ",  board_no : " + postArrayList.get(getLayoutPosition()).getBoard_no());
                            activity.startActivity(boardIntent);
                        }
                    });
        }

        @Override
        public void onBindView(Board item) {
            String time = item.getRemain_time();
            Log.e("time","message"+time);
            if(Integer.parseInt(time) <= 10 ){
                waitTimeImg.setImageResource(R.drawable.time_pink_btn);
                WAIT_TIME.setTextColor(Color.parseColor("#e06376"));
            }else{
                waitTimeImg.setImageResource(R.drawable.time_green_btn);
                WAIT_TIME.setTextColor(Color.parseColor("#52b3b3"));
            }

            WRITER.setText(item.getWrite_name());
            DEPARTMENT.setText(item.getDepartment());
            COMMENT_CNT.setText("관심댓글 "+item.getComment_cnt());
            WAIT_TIME.setText(item.getRemain_time());
            DEPARTURE.setText(item.getDeparture());
            DESTINATION.setText(item.getDestination());
            PASSENGER_NUM.setText("목표인원 "+item.getPassenger_num()+"명");

        }
    }

    public abstract class BaseViewHolder<ITEM> extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
        }
        public abstract void onBindView(ITEM item);
    }
}
