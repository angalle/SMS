package univ.sm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by YAP on 2017-03-02.
 */

public class BoardActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> stringArrayList;
    private Button new_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
        //파싱해서 객체만들어서 뷰에뿌릴리스트 만들기
        stringArrayList = new ArrayList<>();
        stringArrayList.add("test");
        stringArrayList.add("test");
        stringArrayList.add("test");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        mRecyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new BoardViewAdapter(stringArrayList));
        Log.e("권수정","stringArrayList size" + stringArrayList.size());

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
