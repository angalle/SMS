package univ.sm.View.board.list;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import univ.sm.Controller.CommonCallbak;
import univ.sm.Controller.api.board.BoardService;
import univ.sm.Model.Const;
import univ.sm.Model.board.Board;
import univ.sm.R;
import univ.sm.View.board.BoardView;

/**
 * Created by heesun on 2017-04-23.
 */
public class BoardList_FView extends Fragment {
    @BindView(R.id.recycler_view)
    public RecyclerView mRecyclerView;
    private ArrayList<Board> postArrayList = new ArrayList<Board>();
    BoardViewAdapter boardViewAdapter;
    LinearLayoutManager layoutManager = null;
    Context context;
    public static BoardList_FView instance;

    //loading msg dialog
    static ProgressDialog pd = null;

    public BoardList_FView(){
        super();
        this.context = BoardView.context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser){
            //visible
            getServerRequestData();
        }else{
            Log.e("visible","no");
            //unvisible
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    public static BoardList_FView newInstance(int index) {
        BoardList_FView f = new BoardList_FView();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout l_layout = (LinearLayout) inflater.inflate(R.layout.board_list,container,false);
        ButterKnife.bind(this,l_layout);
        set_initView();
        /*getServerRequestData();*/
        return l_layout;
    }

    private void set_initView(){
        layoutManager = new LinearLayoutManager(context);
        boardViewAdapter = new BoardViewAdapter(postArrayList, getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(boardViewAdapter);
    }


    /* 무명객체를 함수화 - adapter에 borad list를 받아옴.*/
    public void getServerRequestData() {
        pd = new ProgressDialog(BoardView.context);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage(Const.CALLVAN_LOADING_MSG);
        pd.show();

        HashMap<String, Object> param = new HashMap<String, Object>();
        BoardService.getInstance(context).createApi().getBoardList(param, callback);
    }

    CommonCallbak callback =  new CommonCallbak() {
        @Override
        public void onError(Throwable t) {
            Toast.makeText(BoardView.context, Const.MSG_ERROR, Toast.LENGTH_SHORT).show();
            t.printStackTrace();
        }

        @Override
        public void onSuccess(int code, Object receiveData) {
            if(code == 200){
                Gson gson = new Gson();
                JsonObject jObject = gson.fromJson(receiveData.toString(),JsonObject.class);
                String result = jObject.get("Result").getAsString();
                postArrayList.clear();

                if("true".equals(result)){
                    JsonArray array = jObject.get("CALLVANS").getAsJsonArray();
                    for(JsonElement number: array) {
                        JsonObject obj = number.getAsJsonObject();
                        Board temp = gson.fromJson(obj,Board.class);
                        postArrayList.add(temp);
                    }
                    if (pd.isShowing()) {
                        pd.dismiss();
                    }
                    /* 동적으로 할당되는 뷰를 그려주거나 이벤트 할당*/
                    boardViewAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(context, "오류가 발생하였습니다. 관리자에게 문의하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(int code) {
            Toast.makeText(BoardView.context, Const.MSG_FAIL, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
