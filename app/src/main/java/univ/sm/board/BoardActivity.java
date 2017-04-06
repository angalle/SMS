package univ.sm.board;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import univ.sm.R;

/**
 * 게시판 리스트 페이지
 * 데이터 불러오기, 새글올리기, 새로고침
 * Created by kwonsoojeong on 2017-03-02.
 */

@SuppressWarnings("DefaultFileTemplate")
public class BoardActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> stringArrayList;
    private Button new_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_list);

        stringArrayList = new ArrayList<>();

        //todo 파싱해서 객체만들어서 뷰에뿌릴 리스트 add
        stringArrayList.add("test1");
        stringArrayList.add("test2");
        stringArrayList.add("test3");
        stringArrayList.add("test3");
        stringArrayList.add("test3");
        stringArrayList.add("test3");


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        BoardViewAdapter boardViewAdapter = new BoardViewAdapter(stringArrayList,getApplicationContext());
        mRecyclerView.setAdapter(boardViewAdapter);

        new_btn = (Button) findViewById(R.id.new_btn);
        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getApplicationContext(),NewPostingPage.class);
                startActivity(go);
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
