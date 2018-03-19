package univ.sm.View.board.detail;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import univ.sm.Controller.CommonCallbak;
import univ.sm.Controller.api.board.BoardService;
import univ.sm.R;
import univ.sm.Model.board.Board;
import univ.sm.Util.CommonUtil;
import univ.sm.View.board.BoardView;
import univ.sm.connect.LoopjConnection;
import univ.sm.Model.board.BoardComment;
import univ.sm.Model.Const;

/**
 * 게시판 세부 페이지
 * 글 인덱스로 게시글 불러옴, 댓글기능 및\ 새로고침 기능
 * Created by kwonsoojeong on 2017-03-28.
 */

public class BoardDetailView extends AppCompatActivity implements View.OnClickListener {
    /* TODO:이런 공통변수는 Const에 넣어서 활용하면 좋음*/
    private static final String TAG = "BoardDetailView";

    @BindView(R.id.DEPARTMENT_view)TextView DEPARTMENT_view;
    @BindView(R.id.WRITE_NAME_view)TextView WRITE_NAME_view;
    @BindView(R.id.STUDENT_NO_view)TextView STUDENT_NO_view;
    @BindView(R.id.DEPARTURE_view)TextView DEPARTURE_view;
    @BindView(R.id.DEPARTURE_DETAIL_view)TextView DEPARTURE_DETAIL_view;
    @BindView(R.id.DESTINATION_view)TextView DESTINATION_view;
    @BindView(R.id.DESTINATION_DETAIL_view)TextView DESTINATION_DETAIL_view;
    @BindView(R.id.passengerNum_tv)TextView PASSENGER_NUM_view;
    @BindView(R.id.wait_time_tv)TextView WAIT_TIME_view;
    @BindView(R.id.remain_time_tv)TextView REMAIN_TIME_view;

    @BindView(R.id.comment_editText)EditText comment_editText;

    @BindView(R.id.passwd_ed)EditText passwd_ed;

    @BindView(R.id.comment_btn)Button comment_btn;
    @BindView(R.id.modify_btn)Button modify_btn;

    @BindView(R.id.callvan_flag)CheckBox callvan_flag;

    @BindView(R.id.comment_popup_btn)ImageButton comment_popup_btn;

    @BindView(R.id.commentRecyclerView)RecyclerView commentRCV;
    @BindView(R.id.foot_layout)LinearLayout foot_layout;

    private ArrayList<BoardComment> mCommentsList;
    private SharedPreferences userPref;

    private Board mPost;
    private String board_no;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 무슨페이지인지 보기 쉽게 하기위해서 SETCONTENTVIEW함수는 맨앞으로 빼둠.
        setContentView(R.layout.board_post_detail);
        ButterKnife.bind(this);
        /* 초기화할때와 SET 할때를 구분해서 함수를 나눠놨음.*/
        init();
        downLoadBoard_setLayout();
        comment_editText.requestFocus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void init() {
        /** board_post_detail layout 구성*/

        comment_popup_btn.setOnClickListener(this);
        comment_btn.setOnClickListener(this);
        modify_btn.setOnClickListener(this);
        /** board comment 구성*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        commentRCV.setLayoutManager(layoutManager);

        mCommentsList = new ArrayList<>();
        userPref = getSharedPreferences("userPref", MODE_PRIVATE);

        Intent intent = getIntent();
        board_no = intent.getStringExtra("board_no");
        position = intent.getIntExtra("position",0);
    }

    void downLoadBoard_setLayout() {
        HashMap<String,Object> param = new HashMap<>();
        param.put("CALL_BOARD_NO", board_no);

        BoardService.getInstance(this).createApi().getOneBoard(param, new CommonCallbak() {
            @Override
            public void onError(Throwable t) {
                Toast.makeText(BoardView.context, Const.MSG_ERROR, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                finish();
            }

            @Override
            public void onSuccess(int code, Object receiveData) {
                if(code == 200){
                    Gson gson = new Gson();
                    JsonObject jObject = gson.fromJson(receiveData.toString(),JsonObject.class);
                    String result = jObject.get("Result").getAsString();
                    if("true".equals(result)){
                        JsonArray CALLVAN_INFO = jObject.get("CALLVAN_INFO").getAsJsonArray();
                        if(CALLVAN_INFO.size() != 0){

                            mPost = gson.fromJson(CALLVAN_INFO.get(0),Board.class);
                            mCommentsList.clear();
                            JsonArray COMMENTS = jObject.get("COMMENTS").getAsJsonArray();
                            for(JsonElement element : COMMENTS){
                                BoardComment temp  = new BoardComment();
                                temp = gson.fromJson(element,BoardComment.class);
                                mCommentsList.add(temp);
                            }

                            setLayout();
                        }
                    }else{
                        Toast.makeText(BoardView.context, Const.MSG_FAIL, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(int code) {
                Toast.makeText(BoardView.context, Const.MSG_FAIL, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setLayout() {
        BoardCommentListAdapter commentListAdapter = new BoardCommentListAdapter(mCommentsList, getApplicationContext());
        commentRCV.setAdapter(commentListAdapter);

        if (mPost.getBoard_no().equals(board_no)) {
            WRITE_NAME_view.setText(mPost.getWrite_name());
            DEPARTMENT_view.setText(mPost.getDepartment());
            STUDENT_NO_view.setText(mPost.getStudent_no());
            DEPARTURE_view.setText(mPost.getDeparture());
            DEPARTURE_DETAIL_view.setText(mPost.getDeparture_detail());
            DESTINATION_view.setText(mPost.getDestination());
            DESTINATION_DETAIL_view.setText(mPost.getDestination_detail());
            PASSENGER_NUM_view.setText(mPost.getPassenger_num() + "명");
            WAIT_TIME_view.setText(mPost.getWait_time()+ "분");
            REMAIN_TIME_view.setText(mPost.getRemain_time()+ "분");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_btn:
                HashMap<String,Object> appravalParams = new HashMap<>() ;
                appravalParams.put("CALL_BOARD_NO",board_no);
                appravalParams.put("PASSWD",passwd_ed.getText().toString());

                if(callvan_flag.isChecked()){
                    BoardService.getInstance(this).createApi().approvalCallvan(appravalParams, new CommonCallbak() {
                        @Override
                        public void onError(Throwable t) {
                            Toast.makeText(BoardView.context, Const.MSG_ERROR, Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }

                        @Override
                        public void onSuccess(int code, Object receiveData) {
                            Gson gson = new Gson();
                            JsonObject jObject = gson.fromJson(receiveData.toString(),JsonObject.class);
                            String result = jObject.get("Result").getAsString();
                            if("false".equals(result)){
                                Toast.makeText(BoardView.context, Const.MSG_FAIL, Toast.LENGTH_SHORT).show();
                            }else{
                                callvan_flag.setEnabled(false);
                                passwd_ed.setEnabled(false);
                                modify_btn.setEnabled(false);
                                Toast.makeText(getApplicationContext(),"승인완료 :: 해당항목은 사라집니다.",Toast.LENGTH_SHORT).show();
                                callvan_flag.setVisibility(View.INVISIBLE);
                                passwd_ed.setVisibility(View.INVISIBLE);
                                modify_btn.setVisibility(View.INVISIBLE);
                                finish();
                                CommonUtil.nextPage(new Intent(BoardDetailView.this,BoardView.class), BoardView.activity);
                            }
                        }

                        @Override
                        public void onFailure(int code) {
                            Toast.makeText(BoardView.context, Const.MSG_FAIL, Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"승인을 체크해야만 탑승완료처리가 됩니다.",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.comment_btn:
                HashMap<String,Object> comment_params = new HashMap<String,Object>();
                SharedPreferences sp                   = getSharedPreferences(Const.SHARED_USER, MODE_PRIVATE);

                comment_params.put("MEMBER_EMAIL", sp.getString(Const.SHARED_MEMBER_EMAIL,""));
                comment_params.put("MEMBER_NAME", sp.getString(Const.SHARED_MEMBER_NAME,""));
                comment_params.put("CALL_BOARD_NO", board_no);
                comment_params.put("CONTENTS", comment_editText.getText());
                comment_params.put("COMMENT_LEVEL", 1);
                comment_params.put("BEFORCOMMENT_NO", board_no);            //이희선씨의 보드넘버 요청
                comment_params.put("SEND_REG_ID", " ");                     //이희선씨의 공백 요청

                if(comment_editText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"댓글을 입력하세요",Toast.LENGTH_SHORT).show();
                    return ;
                }

                BoardService.getInstance(this).createApi().addComment(comment_params, new CommonCallbak() {
                    @Override
                    public void onError(Throwable t) {
                        Toast.makeText(BoardView.context, Const.MSG_ERROR, Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }

                    @Override
                    public void onSuccess(int code, Object receiveData) {
                        Gson gson = new Gson();
                        JsonObject jObject = gson.fromJson(receiveData.toString(),JsonObject.class);
                        String result = jObject.get("Result").getAsString();
                        if("false".equals(result)){
                            Toast.makeText(BoardView.context, Const.MSG_FAIL, Toast.LENGTH_SHORT).show();
                        }else{
                            // InputMethodManager를 가져옴
                            InputMethodManager imm =     (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                            Toast.makeText(BoardView.context, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                            // 감출 때
                            imm.hideSoftInputFromWindow(comment_editText.getWindowToken(), 0);
                            comment_editText.setText("");
                            downLoadBoard_setLayout();
                        }
                    }

                    @Override
                    public void onFailure(int code) {
                        Toast.makeText(BoardView.context, Const.MSG_FAIL, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

}