package univ.sm;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BoardViewAdapter extends RecyclerView.Adapter<BoardViewAdapter.ViewHolder> {

    public BoardViewAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            TextView textView1 = (TextView) itemView.findViewById(R.id.text1);
            TextView textView2 = (TextView) itemView.findViewById(R.id.text2);
            TextView textView3 = (TextView) itemView.findViewById(R.id.text3);
        }
    }
}