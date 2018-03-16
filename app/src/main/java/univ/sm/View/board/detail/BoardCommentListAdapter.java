package univ.sm.View.board.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import univ.sm.R;
import univ.sm.Model.board.BoardComment;

public class BoardCommentListAdapter extends RecyclerView.Adapter<BoardCommentListAdapter.BaseViewHolder> {
    private List<BoardComment> commentsList = new ArrayList<>();    //List<Board> items = new ArrayList<>;
    private Context context;

    public BoardCommentListAdapter(List<BoardComment> mItems, Context context) {
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

    public void setCommentArrayList(List<BoardComment> commentArrayList){
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

    public class ViewHolder extends BaseViewHolder<BoardComment> {

        private ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindView(BoardComment item) {

            TextView comment_name = (TextView) itemView.findViewById(R.id.comment_writer_name);
            TextView comment_contents = (TextView) itemView.findViewById(R.id.comment_contents);

            comment_name.setText(item.getWRITE_NAME());
            comment_contents.setText(item.getCONTENTS());
        }
    }

}
