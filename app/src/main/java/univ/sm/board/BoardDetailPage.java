package univ.sm.board;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.BaseObj;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        board_no = intent.getExtras().getString("board_no");


        //todo 디테일 페이지를 새로고침하게되면 어떻게 해야될까?... 보드 하나만 받아와야하나? or 보드리스트를 다시받아온다?

        Board board = BoardManager.getBoardArrayList().get(position);
        if (board.getBoard_no().equals(board_no)) {
            WRITE_NAME_view.setText(board.getWrite_name());
            DEPARTMENT_view.setText(board.getDepartment());
            STUDENT_NO_view.setText(board.getStudent_no());
            DEPARTURE_view.setText(board.getDeparture());
            DEPARTURE_DETAIL_view.setText(board.getDeparture_detail());
            DESTINATION_view.setText(board.getDestination());
            DESTINATION_DETAIL_view.setText(board.getDestination_detail());
            PASSENGER_NUM_view.setText(board.getPassenger_num() + "명");
            WAIT_TIME_view.setText(board.getWait_time());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment_btn:
                //키보드 up, add text;
                break;
            case R.id.close_btn:
                finish();
                break;
        }
    }
}
