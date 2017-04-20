package univ.sm.board;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
public class CommentActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //comment page preview
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

            }
        }.execute(null, null, null);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
