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
    private Button new_btn, refresh_btn;
    private ArrayList<Post> postArrayList = new ArrayList<>();
    BoardViewAdapter boardViewAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //SET UI
                setContentView(R.layout.board_list);
            }

            @Override
            protected Void doInBackground(Void... params) {
                /** CallVan board data download */
                LoopjConnection connection = LoopjConnection.getInstance();
                connection.getBoardList();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                setLayout();
            }
        }.execute(null, null, null);
    }

    private void setLayout() {
        postArrayList = BoardManager.getPostArrayList();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        boardViewAdapter = new BoardViewAdapter(postArrayList, getApplicationContext());
        mRecyclerView.setAdapter(boardViewAdapter);

        refresh_btn = (Button) findViewById(R.id.refresh_btn);
        new_btn = (Button) findViewById(R.id.new_btn);
        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        /** CallVan board data download */
                        LoopjConnection connection = LoopjConnection.getInstance();
                        connection.getBoardList();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        boardViewAdapter.setBoardArrayList(BoardManager.getPostArrayList());
                        boardViewAdapter.notifyDataSetChanged();
                    }
                }.execute(null, null, null);
            }
        });
        new_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getApplicationContext(), NewPostingPage.class);
                startActivity(go);
            }
        });

    }
}
