package univ.sm.board;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import univ.sm.R;
import univ.sm.connect.LoopjConnection;

/**
 * 게시판 리스트 페이지
 * 데이터 불러오기, 새글올리기, 새로고침
 * Created by kwonsoojeong on 2017-03-02.
 */

@SuppressWarnings("DefaultFileTemplate")
public class BoardActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Button new_btn;
    private ArrayList<Board> boardArrayList = new ArrayList<>();
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_list);
        pd = new ProgressDialog(BoardActivity.this);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.setMessage("로딩...");
                pd.show();
            }
            @Override
            protected Void doInBackground(Void... params) {
                /** CallVan board data download */
                LoopjConnection connection = LoopjConnection.getInstance();
                boardArrayList = connection.getBoardList();
                Log.e("권수정", "boardArrayList.size() : " + boardArrayList.size());
                return null;
            }
        };



        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        BoardViewAdapter boardViewAdapter = new BoardViewAdapter(boardArrayList, getApplicationContext());
        mRecyclerView.setAdapter(boardViewAdapter);

        new_btn = (Button) findViewById(R.id.new_btn);
        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getApplicationContext(), NewPostingPage.class);
                startActivity(go);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
