package univ.sm.view.board.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import univ.sm.R;
import univ.sm.model.board.Board;
import univ.sm.view.board.detail.BoardDetailView;

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
    private static final int AD_TYPE = 4;
    private static final int CONTENT_TYPE = 5;
    private List<Board> postArrayList = new ArrayList<>();    //List<Board> items = new ArrayList<>;
    private Context context;
    private Activity activity;
    private AdView mAdView;

    public BoardViewAdapter(List<Board> mItems, Activity activity) {
        this.postArrayList = mItems;
        this.activity = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        Log.e("viewType",viewType+"::::");
        if (viewType == AD_TYPE)
        {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item_ad_view, parent, false);
        }else{
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item_view, parent, false);
            ButterKnife.bind(this,itemView);
        }
        return new ViewHolder(itemView,viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindView(postArrayList.get(position));
    }

    @Override
    public int getItemViewType(int position)
    {
        Log.e("board_list",position+"::::");
        if (position == 0)
            return AD_TYPE;
        return CONTENT_TYPE;
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public class ViewHolder extends BaseViewHolder<Board> {
        int viewType = 0;
        private ViewHolder(View itemView,int viewType) {
            super(itemView);
            this.viewType = viewType;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(viewType == AD_TYPE){
                        return;
                    }
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
            if(viewType == AD_TYPE){
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView = (AdView)(itemView.findViewById(R.id.board_adView));
                mAdView.loadAd(adRequest);
                return;
            }
            String time = item.getRemain_time();

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
