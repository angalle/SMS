package univ.sm.board;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import univ.sm.R;
import univ.sm.data.Comment;

public class BoardCommentListAdapter extends RecyclerView.Adapter<BoardCommentListAdapter.BaseViewHolder> {
    private List<Comment> commentsList = new ArrayList<>();    //List<Post> items = new ArrayList<>;
    private Context context;

    public BoardCommentListAdapter(List<Comment> mItems, Context context) {
        this.commentsList = mItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_comment_item_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindView(commentsList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public void setCommentArrayList(List<Comment> commentArrayList){
        //어뎁터 board 리스트 새로 받아오기
        commentsList.clear();
        commentsList.addAll(commentArrayList);
        notifyDataSetChanged();
    }

    public abstract class BaseViewHolder<ITEM> extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void onBindView(ITEM item);
    }

    public class ViewHolder extends BaseViewHolder<Comment> {

        private ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(Comment item) {
            /*TextView DEPARTURE = (TextView) itemView.findViewById(R.id.DEPARTURE);//출발지
            TextView DESTINATION = (TextView) itemView.findViewById(R.id.DESTINATION);//도착지
            TextView PASSENGER_NUM = (TextView) itemView.findViewById(R.id.passengerNum);
            TextView WRITE_NAME = (TextView) itemView.findViewById(R.id.name);
            TextView WAIT_TIME = (TextView) itemView.findViewById(R.id.waitTime);

            WAIT_TIME.setText(item.getRemain_time());
            DEPARTURE.setText(item.getDeparture());
            DESTINATION.setText(item.getDestination());
            PASSENGER_NUM.setText("총 "+item.getPassenger_num());
            WRITE_NAME.setText(item.getWrite_name());*/

            TextView comment_name = (TextView) itemView.findViewById(R.id.comment_writer_name);
            TextView comment_contents = (TextView) itemView.findViewById(R.id.comment_contents);
            TextView comment_time = (TextView) itemView.findViewById(R.id.comment_time);

            comment_name.setText(item.getWRITE_NAME());
            comment_contents.setText(item.getCONTENTS());
            comment_time.setText(item.getINSERT_TIME());
        }
    }

}
