package univ.sm.board;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.loopj.android.http.RequestParams;

import univ.sm.R;
import univ.sm.connect.LoopjConnection;

/**
 * 새 글 등록 페이지
 * Created by soojeong on 2017-03-22.
 */
/*POST PARAMETER
        WRITE_NAME            //작성자
        PASSWD                //글 비밀번호
        DEPARTMENT            //학과
        STUDENT_NO            //학번
        DEPARTURE            //출발지
//                        DEPARTURE_DETAIL	//출발지 설명
        DESTINATION            //목적지
//                        DESTINATION_DETAIL	//목적지 설명
        REG_ID                //gcm 기기 값
        PASSENGER_NUM        //총 탑승인원
        WAIT_TIME            //대기시간*/

public class NewPostingPage extends AppCompatActivity implements View.OnClickListener {
    private EditText writeName;
    private EditText department;
    private EditText studentNo;
    private EditText departure;
    private EditText destination;
    private EditText passengerNum;
    private EditText passwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_posting);
        //set click Listener
        Button upload_btn = (Button) findViewById(R.id.upload_btn);
        LinearLayout postingLayout = (LinearLayout) findViewById(R.id.posting_layout);
        upload_btn.setOnClickListener(this);
        postingLayout.setOnClickListener(this);
        //post parametars..
        writeName = (EditText) findViewById(R.id.writename_edit);
        department = (EditText) findViewById(R.id.department_std_edit); //학과
        studentNo = (EditText) findViewById(R.id.studentNo_edit);
        departure = (EditText) findViewById(R.id.departure_edit);
        destination = (EditText) findViewById(R.id.destination_edit);
        passengerNum = (EditText) findViewById(R.id.passengerNum_edit);
        passwd = (EditText) findViewById(R.id.passwd_edit);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.upload_btn:
                //TODO : Post
                LoopjConnection connection = new LoopjConnection();
                RequestParams params = new RequestParams();
                params.put("WRITE_NAME", writeName.getText());
                params.put("PASSWD", passwd.getText());
                params.put("STUDENT_NO", studentNo.getText());
                params.put("DEPARTURE", departure.getText());
                params.put("DESTINATION", destination.getText());
                params.put("PASSENGER_NUM", passengerNum.getText());

                connection.addPosting(params);

                Log.i("권수정", "click upload button!");
                finish();
                break;
            case R.id.posting_layout:
                /**todo 바탕 클릭시 키보드 숨기기 ..... 현재 동작안함 원인 모르겟음*/
                View view = this.getCurrentFocus();
                if (view != null) {
                    Log.i("권수정", "키보드 내려가");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
                break;
        }

    }
}
