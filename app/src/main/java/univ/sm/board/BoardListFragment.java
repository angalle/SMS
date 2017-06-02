package univ.sm.board;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import univ.sm.R;
import univ.sm.connect.LoopjConnection;

/**
 * Created by heesun on 2017-04-23.
 */
public class BoardListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<Post> postArrayList = new ArrayList<>();
    BoardViewAdapter boardViewAdapter;
    LinearLayoutManager layoutManager = null;
    Context context;
    Activity activity;

    public static BoardListFragment newInstatnce(){
        BoardListFragment b = new BoardListFragment();
        return b;
    }

    public BoardListFragment(){
        super();
        this.context = BoardActivity.context;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            context = (Activity)context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getServerRequestData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout l_layout = (LinearLayout) inflater.inflate(R.layout.board_list,container,false);
        set_initView(l_layout);
        return l_layout;
    }

    private void set_initView(View v){
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(context);
        boardViewAdapter = new BoardViewAdapter(postArrayList, getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(boardViewAdapter);
    }
    ProgressDialog pd = null;

    /* 무명객체를 함수화 - adapter에 borad list를 받아옴.*/
    public void getServerRequestData(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd = new ProgressDialog(getContext());
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.setMessage("Loading to list");
                pd.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                /** CallVan board data download */
                LoopjConnection connection = LoopjConnection.getInstance(context);
                connection.getBoardList();

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                try {
                    /* 동적으로 할당되는 뷰를 그려주거나 이벤트 할당*/
                    fn_dynamicLayout();
                    pd.dismiss();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.execute(null, null, null);
    }

    /* 동적 view 초기화 */
    private void fn_dynamicLayout() throws Exception{
        postArrayList = BoardManager.getPostArrayList();
        boardViewAdapter.setBoardArrayList(postArrayList);
        boardViewAdapter.notifyDataSetChanged();
    }
}
