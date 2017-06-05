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
    private EditText comment_editText, comment_name_editText, comment_passwd_editText;
    private ImageButton comment_popup_btn;
    private Button comment_btn, comment_info_ok_btn;
    private LinearLayout comment_info_layout;
    private RequestParams comment_params = new RequestParams();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        if(mPost == null){
            Comment comment = new Comment("1", "1", "1", "Not Display nothing", "NDN", "NDN", "doc", "f", "0", "d", "a");
            mCommentsList.add(comment);
        }else{
            mCommentsList = mPost.getCommentsList();
        }




        BoardCommentListAdapter commentListAdapter = new BoardCommentListAdapter(mCommentsList, getApplicationContext());
        commentRCV.setAdapter(commentListAdapter);

        comment_info_layout = (LinearLayout) findViewById(R.id.comment_info_Layout);
        comment_name_editText = (EditText) findViewById(R.id.comment_name);
        comment_editText = (EditText) findViewById(R.id.comment_editText);
        comment_passwd_editText = (EditText) findViewById(R.id.comment_passwd);
        comment_info_ok_btn = (Button) findViewById(R.id.comment_info_ok_btn);
        comment_info_ok_btn.setOnClickListener(this);
        comment_popup_btn = (ImageButton) findViewById(R.id.comment_popup_btn);
        comment_popup_btn.setOnClickListener(this);
        comment_btn = (Button) findViewById(R.id.comment_btn);
        comment_btn.setOnClickListener(this);


//        BoardManager.refreshPost(position,/*jsonobject*/);
//todo 디테일 페이지를 새로고침


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

    /*var CALL_BOARD_NO              //해당글의 INDEX
         var COMMENT_LEVEL             // 댓글의 댓글레벨
         var CONTENTS                         // 내용
         var REG_ID                                // 댓글 남긴사람의 기기값
         var WRITE_NAME                      // 작성자이름
         var DEPARTMENT                      // 학과
         var BEFORE_COMMENT_NO    // 이전 댓글 INDEX - 이전 인덱스를 찾아서 레벨로 댓글의 댓글기능 구성
         var SEND_REG_ID    	             // 타겟이 될 사람의 기기값*/
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
                comment_params.put("DEPARTMENT", comment_passwd_editText.getText()); //fixme 학과에 passwd 입력하는상태
                comment_params.put("CALL_BOARD_NO", board_no);
                comment_params.put("CONTENTS", comment_editText.getText());
                comment_params.put("REG_ID", sp.getString("reg-id", ""));

                comment_editText.setText(null);
                Log.i(TAG, "comment_params.toString() : " + comment_params.toString());
                LoopjConnection.getInstance(getApplicationContext()).addComment(comment_params); //전송

                break;

        }
    }
}

