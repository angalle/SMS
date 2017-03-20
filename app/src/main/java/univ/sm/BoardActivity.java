package univ.sm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

/**
 * Created by YAP on 2017-03-02.
 */

public class BoardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new BoardViewAdapter());



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
