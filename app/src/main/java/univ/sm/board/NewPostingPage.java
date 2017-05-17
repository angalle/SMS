package univ.sm.board;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

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

public class NewPostingPage extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private EditText writeName;
    private EditText department;
    private EditText studentNo;
    private EditText departure, departure_detail;
    private EditText destination, destination_detail;
    private EditText passengerNum;
    private EditText passwd;
    private String waitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_posting);
        SharedPreferences sp = getSharedPreferences("GCM", MODE_PRIVATE);
        Log.e("::::::", "::::::::::" + sp.getString("reg-id", ""));
        //set click Listener
        Button upload_btn = (Button) findViewById(R.id.upload_btn);
        LinearLayout postingLayout = (LinearLayout) findViewById(R.id.posting_layout);
        upload_btn.setOnClickListener(this);
        postingLayout.setOnClickListener(this);
        //mPost parametars..
        writeName = (EditText) findViewById(R.id.writename_edit);
        department = (EditText) findViewById(R.id.department_std_edit); //학과
        studentNo = (EditText) findViewById(R.id.studentNo_edit);
        departure = (EditText) findViewById(R.id.departure_edit);
        departure_detail= (EditText) findViewById(R.id.departure_detail_edit);
        destination = (EditText) findViewById(R.id.destination_edit);
        destination_detail = (EditText) findViewById(R.id.destination_detail_edit);
        passengerNum = (EditText) findViewById(R.id.passengerNum_edit);
        passwd = (EditText) findViewById(R.id.passwd_edit);
        Spinner waitTimeSpinner = (Spinner) findViewById(R.id.wait_time_spinner);
        ArrayAdapter<CharSequence> waiteAdapter = ArrayAdapter.createFromResource(this, R.array.wait_time_array, R.layout.support_simple_spinner_dropdown_item);
        waiteAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        waitTimeSpinner.setAdapter(waiteAdapter);
        waitTimeSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.upload_btn:
                //TODO : null 확인하기
             /*   if(writeName.getText() ==null || passwd.getText() == null || studentNo.getText() == null || department.getText() == null || departure.getText() == null || departure_detail.getText() == null || destination.getText() == null || destination_detail.getText() == null || passengerNum.getText()==null){
                    Toast.makeText(this, "전부 다 입력해주세요", Toast.LENGTH_SHORT).show();
                    break;
                }*/
                LoopjConnection connection = LoopjConnection.getInstance(getApplicationContext());
                RequestParams params = new RequestParams();
                params.put("WRITE_NAME", writeName.getText());
                params.put("PASSWD", passwd.getText());
                params.put("STUDENT_NO", studentNo.getText());//학번
                params.put("DEPARTMENT", department.getText());//학과
                params.put("DEPARTURE", departure.getText());
                params.put("DEPARTURE_DETAIL", departure_detail.getText());
                params.put("DESTINATION", destination.getText());
                params.put("DESTINATION_DETAIL", destination_detail.getText());
                params.put("PASSENGER_NUM", passengerNum.getText());
                params.put("WAIT_TIME",waitTime);

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
    /** 스피너 선택 이벤트를 위한 메소드*/
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        if(view.getId() == R.id.wait_time_spinner){
            waitTime = (String) parent.getItemAtPosition(position);
            Log.e("권수정", "waitTime : " + waitTime);

//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
