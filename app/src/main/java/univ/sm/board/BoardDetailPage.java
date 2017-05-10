package univ.sm.board;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

import univ.sm.R;
import univ.sm.connect.LoopjConnection;

/**
 * 게시판 세부 페이지
 * 글 인덱스로 게시글 불러옴, 댓글기능 및 새로고침 기능
 * Created by kwonsoojeong on 2017-03-28.
 */

public class BoardDetailPage extends AppCompatActivity implements View.OnClickListener {
    int position;
    String board_no;
    TextView WRITE_NAME_view, DEPARTMENT_view, STUDENT_NO_view, DEPARTURE_view, DEPARTURE_DETAIL_view, DESTINATION_view, DESTINATION_DETAIL_view, PASSENGER_NUM_view, WAIT_TIME_view;
    EditText comment_editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        RecyclerView commentRCV = (RecyclerView) findViewById(R.id.commentRecyclerView);
        //todo 댓글 리사이클 뷰에 어뎁터 설정해서 달기..
//        Button close_btn = (Button) findViewById(R.id.close_btn);
//        close_btn.setOnClickListener(this);
       comment_editText = (EditText) findViewById(R.id.comment_editText);

        Button comment_btn = (Button) findViewById(R.id.comment_btn);
        comment_btn.setOnClickListener(this);


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        board_no = intent.getExtras().getString("board_no");



//        BoardManager.refreshPost(position,/*jsonobject*/);

//todo 디테일 페이지를 새로고침

//        Post post = BoardManager.getPostArrayList().get(position);

        //todo 디테일 페이지 데이터 받아오기 (post + 댓글)
        /** board 넘버로 댓글까지 받아오는 api사용.. 앞선 데이터대신에 아래의 것을 사용해야함,
         * */
        Post post = LoopjConnection.getInstance().getonePost(new RequestParams("CALL_BOARD_NO",board_no));

        if (post.getBoard_no().equals(board_no)) {
            WRITE_NAME_view.setText(post.getWrite_name());
            DEPARTMENT_view.setText(post.getDepartment());
            STUDENT_NO_view.setText(post.getStudent_no());
            DEPARTURE_view.setText(post.getDeparture());
            DEPARTURE_DETAIL_view.setText(post.getDeparture_detail());
            DESTINATION_view.setText(post.getDestination());
            DESTINATION_DETAIL_view.setText(post.getDestination_detail());
            PASSENGER_NUM_view.setText(post.getPassenger_num() + "명");
            WAIT_TIME_view.setText(post.getWait_time());
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
            case R.id.comment_btn:

                //코엔드 보내기
                RequestParams params = new RequestParams();
                SharedPreferences sp = getSharedPreferences("GCM", MODE_PRIVATE);

                params.put("CALL_BOARD_NO",board_no);
                params.put("CONTENTS",comment_editText.getText().toString());
                params.put("REG_ID",sp.getString("reg-id",""));

                LoopjConnection.getInstance().addComment(params);
//todo test 필요함...
                finish();
//                Intent commentPage = new Intent();
//                commentPage.setClass(BoardDetailPage.this, BoardActivity.class);
//                startActivity(commentPage);
                break;
//            case R.id.close_btn:
//                finish();
//                break;
        }
    }

}
