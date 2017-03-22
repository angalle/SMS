package univ.sm;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BoardViewAdapter extends RecyclerView.Adapter<BoardViewAdapter.BaseViewHolder> {
    private List<String> mItems = new ArrayList<>();

    public BoardViewAdapter(List<String> mItems) {
        this.mItems = mItems;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindView(mItems.get(position));
    }


//    public void setItems(List<String> items) {
//        mItems.clear();
//        mItems.addAll(items);
//    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public abstract class BaseViewHolder<ITEM> extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
        }
        public abstract void onBindView(ITEM item);
    }

    public class ViewHolder extends BaseViewHolder<String> {
        private TextView mTextView;

        private ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text1);
        }

        @Override
        public void onBindView(String item) {
            mTextView.setText( "What is this? " + item);
        }
    }
}