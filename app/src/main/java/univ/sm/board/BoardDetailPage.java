package univ.sm.board;

import android.content.Intent;
import android.os.Bundle;
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

public class BoardDetailPage extends AppCompatActivity implements View.OnClickListener{
    int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_post_detail);

        Intent i = getIntent();
        position = i.getIntExtra("position", 0);

        TextView textView_post_body = (TextView) findViewById(R.id.post_body);
        textView_post_body.setText("Hello, This posting is " + (position+1) );

        Button close_btn = (Button) findViewById(R.id.close_btn);
        close_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.comment_btn:
                //키보드 up, add text;
                break;
            case R.id.close_btn:
                finish();
                break;
        }
    }
}
