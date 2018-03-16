package univ.sm.View.board.list;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import univ.sm.Controller.CommonCallbak;
import univ.sm.Controller.api.board.BoardService;
import univ.sm.R;
import univ.sm.Controller.BoardManager;
import univ.sm.Model.board.Board;
import univ.sm.Util.CommonUtil;
import univ.sm.connect.LoopjConnection;
import univ.sm.Model.Const;
import univ.sm.View.board.BoardView;

/**
 * Created by heesun on 2017-04-23.
 */
public class BoardList_FView extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<Board> postArrayList = new ArrayList<>();
    BoardViewAdapter boardViewAdapter;
    LinearLayoutManager layoutManager = null;
    Context context;
    public static BoardList_FView instance;

    //loading msg dialog
    static ProgressDialog pd = null;

    public static BoardList_FView newInstatnce(){
        if(instance == null){
            instance = new BoardList_FView();
        }
        return instance;
    }

    public BoardList_FView(){
        super();
        this.context = BoardView.context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout l_layout = (LinearLayout) inflater.inflate(R.layout.board_list,container,false);
        getServerRequestData();
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


    /* 무명객체를 함수화 - adapter에 borad list를 받아옴.*/
    public void getServerRequestData() {
        pd = new ProgressDialog(BoardView.context);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage(Const.CALLVAN_LOADING_MSG);
        pd.show();

        HashMap<String, Object> param = new HashMap<String, Object>();
        BoardService.getInstance(context).createApi().getBoardList(param, new CommonCallbak() {
            @Override
            public void onError(Throwable t) {
                Toast.makeText(BoardView.context, Const.MSG_ERROR, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }

            @Override
            public void onSuccess(int code, Object receiveData) {
                try {
                    if(code == 200){
                        Gson gson = new Gson();
                        JsonObject jObject = gson.fromJson(receiveData.toString(),JsonObject.class);
                        String result = jObject.get("Result").getAsString();
                        if("true".equals(result)){
                            JsonArray array = jObject.get("CALLVANS").getAsJsonArray();
                            for(JsonElement number: array) {
                                JsonObject obj = number.getAsJsonObject();
                                Board temp = gson.fromJson(obj,Board.class);
                                postArrayList.add(temp);
                            }
                            //Toast.makeText(context, "등록되었습니다.", Toast.LENGTH_SHORT).show();

                            Log.e("array Size","Size"+postArrayList.size());

                            /* 동적으로 할당되는 뷰를 그려주거나 이벤트 할당*/
                            boardViewAdapter.setBoardArrayList(postArrayList);
                            mRecyclerView.setAdapter(boardViewAdapter);
                            if (pd.isShowing()) {
                                pd.dismiss();
                            }

                        }else{
                            Toast.makeText(context, "오류가 발생하였습니다. 관리자에게 문의하세요.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(int code) {
                Toast.makeText(BoardView.context, Const.MSG_FAIL, Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
