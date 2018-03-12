package univ.sm.View.board.detail;

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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import univ.sm.R;
import univ.sm.Model.board.Board;
import univ.sm.connect.LoopjConnection;
import univ.sm.Model.board.BoardComment;
import univ.sm.Model.Const;

/**
 * 게시판 세부 페이지
 * 글 인덱스로 게시글 불러옴, 댓글기능 및\ 새로고침 기능
 * Created by kwonsoojeong on 2017-03-28.
 */

/**
 *
 *
 *          코드값에대해서 코멘트를 좀 달아봤습니다. 코드는 다 이유가 있어서 쓴거라 코드의 내용에 대해서 말하지 않음.
 *          코드를 정리하거나 코드스타일에 대해서 조금 코멘트 달아봤습니다.
 *          더 좋은 방법이나 문제점 이슈등이 있으면 톡주시면 되겠습니다.
 *          WRITE BY HS   UNTILL 2017 06 25 AFTER DELETE THIS CONTENTS
 *
 *
 * */

public class BoardDetailView extends AppCompatActivity implements View.OnClickListener {
    /* TODO:이런 공통변수는 Const에 넣어서 활용하면 좋음*/
    private static final String TAG = "BoardDetailView";

    private TextView WRITE_NAME_view, DEPARTMENT_view, STUDENT_NO_view, DEPARTURE_view,REMAIN_TIME_view,
    DEPARTURE_DETAIL_view, DESTINATION_view, DESTINATION_DETAIL_view, PASSENGER_NUM_view, WAIT_TIME_view;
    private EditText comment_editText, comment_name_editText,passwd_ed;
    private Button comment_btn, comment_info_ok_btn,modify_btn;
    private CheckBox callvan_flag;
    private RecyclerView commentRCV;
    private ImageButton comment_popup_btn;
    private LinearLayout comment_info_layout;

    private ArrayList<BoardComment> mCommentsList;
    private RequestParams comment_params = new RequestParams();
    private RequestParams appravalParams = new RequestParams();
    private SharedPreferences userPref;

    private Board mPost;
    private String board_no;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 무슨페이지인지 보기 쉽게 하기위해서 SETCONTENTVIEW함수는 맨앞으로 빼둠.
        setContentView(R.layout.board_post_detail);
        /* 초기화할때와 SET 할때를 구분해서 함수를 나눠놨음.*/
        init();
        downLoadBoard_setLayout();
        comment_editText.requestFocus();
    }

    private void init() {
        /** board_post_detail layout 구성*/
        comment_info_layout     = (LinearLayout) findViewById(R.id.comment_info_Layout);

        DEPARTMENT_view         = (TextView) findViewById(R.id.DEPARTMENT_view);
        WRITE_NAME_view         = (TextView) findViewById(R.id.WRITE_NAME_view);
        STUDENT_NO_view         = (TextView) findViewById(R.id.STUDENT_NO_view);
        DEPARTURE_view          = (TextView) findViewById(R.id.DEPARTURE_view);
        DEPARTURE_DETAIL_view   = (TextView) findViewById(R.id.DEPARTURE_DETAIL_view);
        DESTINATION_view        = (TextView) findViewById(R.id.DESTINATION_view);
        DESTINATION_DETAIL_view = (TextView) findViewById(R.id.DESTINATION_DETAIL_view);
        PASSENGER_NUM_view      = (TextView) findViewById(R.id.passengerNum_tv);
        WAIT_TIME_view          = (TextView) findViewById(R.id.wait_time_tv);
        REMAIN_TIME_view        = (TextView) findViewById(R.id.remain_time_tv);

        comment_name_editText   = (EditText) findViewById(R.id.comment_name);
        comment_editText        = (EditText) findViewById(R.id.comment_editText);
        passwd_ed               = (EditText) findViewById(R.id.passwd_ed);

        comment_info_ok_btn     = (Button) findViewById(R.id.comment_info_ok_btn);
        comment_btn             = (Button) findViewById(R.id.comment_btn);
        modify_btn              = (Button) findViewById(R.id.modify_btn);

        callvan_flag            = (CheckBox) findViewById(R.id.callvan_flag);

        comment_popup_btn       = (ImageButton) findViewById(R.id.comment_popup_btn);

        commentRCV              = (RecyclerView) findViewById(R.id.commentRecyclerView);

        comment_info_ok_btn.setOnClickListener(this);
        comment_popup_btn.setOnClickListener(this);
        comment_btn.setOnClickListener(this);
        modify_btn.setOnClickListener(this);
        /** board comment 구성*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        commentRCV.setLayoutManager(layoutManager);

        mCommentsList = new ArrayList<>();
        userPref = getSharedPreferences("userPref", MODE_PRIVATE);
        comment_name_editText.setText(userPref.getString("name",""));
    }

    void downLoadBoard_setLayout() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Intent intent = getIntent();
                /* TODO: 인텐트를 활용하는 FLAG값도 CONST클래스를 활용해서 사용할 것.*/
                position = intent.getExtras().getInt("position");
                board_no = intent.getExtras().getString("board_no");
            }

            @Override
            protected Void doInBackground(Void... params) {
                /** CallVan mPost + comment data download */
                downloadData();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                setLayout();
            }
        }.execute(null, null, null);
    }

    private void downloadData() {
        //디테일 페이지 데이터 받아오기 (mPost + 댓글)
        Log.i(TAG, "CALL_BOARD_NO : " + board_no + ", position : " + position);
        LoopjConnection connection = LoopjConnection.getInstance(getApplicationContext());
        /* TODO: 인텐트를 활용하는 FLAG값도 CONST클래스를 활용해서 사용할 것.*/
        mPost = connection.getOnePost(new RequestParams("CALL_BOARD_NO", board_no));
    }

    private void setLayout() {
        if (mPost == null) {
        } else {
            mCommentsList = mPost.getCommentsList();
        }
        //TODO: 아래처럼 바꾸는게 나음. AND  에러처리 해주세영
        /*
        * if (mPost != null) {
        *   mCommentsList = mPost.getCommentsList();
        * }
        * */

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
            case R.id.comment_info_ok_btn:
                if (comment_info_layout.getVisibility() == View.VISIBLE) {
                    comment_info_layout.setVisibility(View.GONE);
                }
                break;

            case R.id.comment_popup_btn:
                if (comment_info_layout.getVisibility() == View.GONE) {
                    comment_info_layout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.modify_btn:
                appravalParams = new RequestParams();
                appravalParams.put("CALL_BOARD_NO",board_no);
                appravalParams.put("PASSWD",passwd_ed.getText().toString());
                Log.e(":::::::::::::",""+callvan_flag.isChecked());
                if(callvan_flag.isChecked()){
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {
                            /** 댓글 입력, 데이터 전송*/
                            LoopjConnection.getInstance(getApplicationContext()).approvalCallvan(appravalParams);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            callvan_flag.setEnabled(false);
                            passwd_ed.setEnabled(false);
                            modify_btn.setEnabled(false);
                            Toast.makeText(getApplicationContext(),"승인완료",Toast.LENGTH_SHORT).show();
                            callvan_flag.setVisibility(View.INVISIBLE);
                            passwd_ed.setVisibility(View.INVISIBLE);
                            modify_btn.setVisibility(View.INVISIBLE);
                            finish();
                        }
                    }.execute(null, null, null);
                }else{
                    Toast.makeText(getApplicationContext(),"승인을 체크해야만 탑승완료처리가 됩니다.",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.comment_btn:
                SharedPreferences sp = getSharedPreferences("GCM", MODE_PRIVATE);
                comment_params.put("WRITE_NAME", comment_name_editText.getText());
                comment_params.put("CALL_BOARD_NO", board_no);
                comment_params.put("CONTENTS", comment_editText.getText());
                comment_params.put("REG_ID", sp.getString(Const.SHARED_REG_ID, ""));
                comment_params.put("COMMENT_LEVEL", 1);
                comment_params.put("BEFORCOMMENT_NO", board_no);            //이희선씨의 보드넘버 요청
                comment_params.put("SEND_REG_ID", " ");                     //이희선씨의 공백 요청

                if(comment_name_editText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"사용자명을 입력하세요",Toast.LENGTH_SHORT).show();
                    return ;
                }

                if(comment_editText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"댓글을 입력하세요",Toast.LENGTH_SHORT).show();
                    return ;
                }

                comment_editText.setText(null);

                //이름 자동 생성을 위한 preference 저장
                SharedPreferences.Editor editor = userPref.edit();
                editor.putString("name",comment_name_editText.getText().toString());
                editor.commit();

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        /** 댓글 입력, 데이터 전송*/
                        LoopjConnection.getInstance(getApplicationContext()).addComment(comment_params);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        downLoadBoard_setLayout();
                    }
                }.execute(null, null, null);
                break;
        }
    }

}