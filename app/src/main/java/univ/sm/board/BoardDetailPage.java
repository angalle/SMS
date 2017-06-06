package univ.sm.board;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import univ.sm.R;
import univ.sm.connect.LoopjConnection;
import univ.sm.data.Comment;

/**
 * 게시판 세부 페이지
 * 글 인덱스로 게시글 불러옴, 댓글기능 및 새로고침 기능
 * Created by kwonsoojeong on 2017-03-28.
 */

public class BoardDetailPage extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "BoardDetailPage";

    private int position;
    private Post mPost;
    private String board_no;
    private TextView WRITE_NAME_view, DEPARTMENT_view, STUDENT_NO_view, DEPARTURE_view, DEPARTURE_DETAIL_view, DESTINATION_view, DESTINATION_DETAIL_view, PASSENGER_NUM_view, WAIT_TIME_view;
    private RecyclerView commentRCV;
    private ArrayList<Comment> mCommentsList;
    private EditText comment_editText, comment_name_editText;
    private ImageButton comment_popup_btn;
    private Button comment_btn, comment_info_ok_btn;
    private LinearLayout comment_info_layout;
    private RequestParams comment_params = new RequestParams();
    private SharedPreferences userPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downLoadBoard_setLayout();
    }

    void downLoadBoard_setLayout() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Intent intent = getIntent();
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
        mPost = connection.getOnePost(new RequestParams("CALL_BOARD_NO", board_no));
    }

    private void setLayout() {
        setContentView(R.layout.board_post_detail);

        /** board_post_detail layout 구성*/
        WRITE_NAME_view = (TextView) findViewById(R.id.WRITE_NAME_view);
        DEPARTMENT_view = (TextView) findViewById(R.id.DEPARTMENT_view);
        STUDENT_NO_view = (TextView) findViewById(R.id.STUDENT_NO_view);
        DEPARTURE_view = (TextView) findViewById(R.id.DEPARTURE_view);
        DEPARTURE_DETAIL_view = (TextView) findViewById(R.id.DEPARTURE_DETAIL_view);
        DESTINATION_view = (TextView) findViewById(R.id.DESTINATION_view);
        DESTINATION_DETAIL_view = (TextView) findViewById(R.id.DESTINATION_DETAIL_view);
        PASSENGER_NUM_view = (TextView) findViewById(R.id.PASSENGER_NUM_view);
        WAIT_TIME_view = (TextView) findViewById(R.id.WAIT_TIME_view);

        /** board comment 구성*/
        commentRCV = (RecyclerView) findViewById(R.id.commentRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        commentRCV.setLayoutManager(layoutManager);
        mCommentsList = new ArrayList<>();
        if (mPost == null) {
        } else {
            mCommentsList = mPost.getCommentsList();
        }

        BoardCommentListAdapter commentListAdapter = new BoardCommentListAdapter(mCommentsList, getApplicationContext());
        commentRCV.setAdapter(commentListAdapter);

        comment_info_layout = (LinearLayout) findViewById(R.id.comment_info_Layout);
        comment_name_editText = (EditText) findViewById(R.id.comment_name);
        userPref = getSharedPreferences("userPref", MODE_PRIVATE);
        comment_name_editText.setText(userPref.getString("name",""));
        comment_editText = (EditText) findViewById(R.id.comment_editText);
        comment_info_ok_btn = (Button) findViewById(R.id.comment_info_ok_btn);
        comment_info_ok_btn.setOnClickListener(this);
        comment_popup_btn = (ImageButton) findViewById(R.id.comment_popup_btn);
        comment_popup_btn.setOnClickListener(this);
        comment_btn = (Button) findViewById(R.id.comment_btn);
        comment_btn.setOnClickListener(this);

        if (mPost.getBoard_no().equals(board_no)) {
            WRITE_NAME_view.setText(mPost.getWrite_name());
            DEPARTMENT_view.setText(mPost.getDepartment());
            STUDENT_NO_view.setText(mPost.getStudent_no());
            DEPARTURE_view.setText(mPost.getDeparture());
            DEPARTURE_DETAIL_view.setText(mPost.getDeparture_detail());
            DESTINATION_view.setText(mPost.getDestination());
            DESTINATION_DETAIL_view.setText(mPost.getDestination_detail());
            PASSENGER_NUM_view.setText(mPost.getPassenger_num() + "명");
            WAIT_TIME_view.setText(mPost.getWait_time());
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

            case R.id.comment_btn:
                SharedPreferences sp = getSharedPreferences("GCM", MODE_PRIVATE);
                comment_params.put("WRITE_NAME", comment_name_editText.getText());
                comment_params.put("CALL_BOARD_NO", board_no);
                comment_params.put("CONTENTS", comment_editText.getText());
                comment_params.put("REG_ID", sp.getString("reg-id", ""));
                comment_params.put("COMMENT_LEVEL", 1);
                comment_params.put("BEFORCOMMENT_NO", board_no);//이희선씨의 보드넘버 요청
                comment_params.put("SEND_REG_ID", " ");//이희선씨의 공백 요청

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