package univ.sm.board;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import univ.sm.R;

public class BoardViewAdapter extends RecyclerView.Adapter<BoardViewAdapter.BaseViewHolder> {
    private List<Board> boardArrayList = new ArrayList<>();    //List<Board> items = new ArrayList<>;
    private Context context;

    public BoardViewAdapter(List<Board> mItems, Context context) {
        this.boardArrayList = mItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindView(boardArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return boardArrayList.size();
    }


    public abstract class BaseViewHolder<ITEM> extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void onBindView(ITEM item);
    }

    public class ViewHolder extends BaseViewHolder<Board> {

        private ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("권수정", "click....^_^   :" + getLayoutPosition());
                    // 디테일 페이지로 이동
                    Intent boardIntent = new Intent();
                    boardIntent.setClass(context, BoardDetailPage.class);
                    boardIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    boardIntent.putExtra("position", getLayoutPosition());
                    boardIntent.putExtra("board_no",boardArrayList.get(getLayoutPosition()).getBoard_no());
                    context.startActivity(boardIntent);

                }
            });
        }

        @Override
        public void onBindView(Board item) {
            TextView DEPARTURE = (TextView) itemView.findViewById(R.id.DEPARTURE);//출발지
            TextView DESTINATION = (TextView) itemView.findViewById(R.id.DESTINATION);//도착지
            TextView PASSENGER_NUM = (TextView) itemView.findViewById(R.id.passengerNum);
            TextView name = (TextView) itemView.findViewById(R.id.name);
            DEPARTURE.setText(item.getDeparture());
            DESTINATION.setText(item.getDestination());
            PASSENGER_NUM.setText(""+item.getPassenger_num());
            name.setText(item.getWrite_name());
        }
    }

}
