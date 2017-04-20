package univ.sm.board;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import univ.sm.R;

/**
 * 게시판 세부 페이지
 * 글 인덱스로 게시글 불러옴, 댓글기능 및 새로고침 기능
 * Created by kwonsoojeong on 2017-03-28.
 */

public class BoardDetailPage extends AppCompatActivity implements View.OnClickListener {
    int position;
    String board_no;
    TextView WRITE_NAME_view, DEPARTMENT_view, STUDENT_NO_view, DEPARTURE_view, DEPARTURE_DETAIL_view, DESTINATION_view, DESTINATION_DETAIL_view, PASSENGER_NUM_view, WAIT_TIME_view;

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
        Button close_btn = (Button) findViewById(R.id.close_btn);
        close_btn.setOnClickListener(this);

        Button comment_btn = (Button) findViewById(R.id.comment_btn);
        comment_btn.setOnClickListener(this);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        board_no = intent.getExtras().getString("board_no");

        //todo 디테일 페이지 데이터 받아오기 (post + 댓글)
//         LoopjConnection.getInstance().getonePost(new RequestParams("CALL_BOARD_NO",board_no));
//        BoardManager.refreshPost(position,/*jsonobject*/);

//todo 디테일 페이지를 새로고침

        Post post = BoardManager.getPostArrayList().get(position);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment_btn:
                Intent commentPage = new Intent();
                commentPage.setClass(BoardDetailPage.this, BoardActivity.class);
                startActivity(commentPage);
                break;
            case R.id.close_btn:
                finish();
                break;
        }
    }
}
