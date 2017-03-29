package univ.sm.board;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

import univ.sm.R;

/**
 * 새 글 등록 페이지
 * Created by soojeong on 2017-03-22.
 */

public class NewPostingPage extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_posting);
        Button upload_btn = (Button) findViewById(R.id.upload_btn);
        upload_btn.setOnClickListener(this);
        LinearLayout postingLayout = (LinearLayout) findViewById(R.id.posting_layout);
        postingLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.upload_btn:
                //TODO : Post
                Log.i("권수정","click upload button!");
                finish();
                break;
            case R.id.posting_layout:
                /**todo 바탕 클릭시 키보드 숨기기 ..... 현재 동작안함 원인 모르겟음*/
                View view = this.getCurrentFocus();
                if (view != null) {
                    Log.i("권수정","키보드 내려가");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
                break;
        }

    }
}
