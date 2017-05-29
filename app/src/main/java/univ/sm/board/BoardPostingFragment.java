package univ.sm.board;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by heesun on 2017-04-23.
 */
public class BoardPostingFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Context context;
    private static EditText writeName,department,studentNo,departure,departure_detail,destination_detail,destination,passengerNum,passwd;
    private static String waitTime;
    LinearLayout l_layout;
    public BoardPostingFragment() {
        super();
        this.context = getContext();
    }

    public interface OnApplySelectedListener{
        public void onCategoryApplySelected(RequestParams params);
    }

    @Override
      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        l_layout = (LinearLayout) inflater.inflate(R.layout.board_posting,container,false);
        //SharedPreferences sp = getSharedPreferences("GCM", MODE_PRIVATE);
        set_initView(l_layout);

        return l_layout;
    }

    private void set_initView(View v){
        //set click Listener
        //Button upload_btn = (Button) v.findViewById(R.id.upload_btn);
        LinearLayout postingLayout = (LinearLayout) v.findViewById(R.id.posting_layout);
        //upload_btn.setOnClickListener(this);
        postingLayout.setOnClickListener(this);
        //post parametars..
        writeName = (EditText) v.findViewById(R.id.writename_edit);
        department = (EditText) v.findViewById(R.id.department_std_edit); //학과
        studentNo = (EditText) v.findViewById(R.id.studentNo_edit);
        departure = (EditText) v.findViewById(R.id.departure_detail);
        departure_detail= (EditText) v.findViewById(R.id.departure_detail_edit);
        destination = (EditText) v.findViewById(R.id.destination_edit);
        destination_detail = (EditText) v.findViewById(R.id.destination_detail_edit);
        passengerNum = (EditText) v.findViewById(R.id.passengerNum_edit);
        //passwd = (EditText) v.findViewById(R.id.passwd_edit);
        Spinner waitTimeSpinner = (Spinner) v.findViewById(R.id.wait_time_spinner);
        ArrayAdapter<CharSequence> waiteAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.wait_time_array, R.layout.support_simple_spinner_dropdown_item);
        waiteAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        waitTimeSpinner.setAdapter(waiteAdapter);
        waitTimeSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.upload_btn:
                //TODO : null 확인하기
             *//*   if(writeName.getText() ==null || passwd.getText() == null || studentNo.getText() == null || department.getText() == null || departure.getText() == null || departure_detail.getText() == null || destination.getText() == null || destination_detail.getText() == null || passengerNum.getText()==null){
                    Toast.makeText(this, "전부 다 입력해주세요", Toast.LENGTH_SHORT).show();
                    break;
                }*//*
                LoopjConnection connection = LoopjConnection.getInstance();
                RequestParams params = new RequestParams();
                params.put("WRITE_NAME"         , writeName.getText());
                params.put("PASSWD"              , passwd.getText());
                params.put("STUDENT_NO"         , studentNo.getText());//학번
                params.put("DEPARTMENT"         , department.getText());//학과
                params.put("DEPARTURE"          , departure.getText());
                params.put("DEPARTURE_DETAIL"  , departure_detail.getText());
                params.put("DESTINATION"        , destination.getText());
                params.put("DESTINATION_DETAIL", destination_detail.getText());
                params.put("PASSENGER_NUM"      , passengerNum.getText());
                params.put("WAIT_TIME"           , waitTime);
                connection.addPosting(params);

                Log.i("권수정", "click upload button!");
                *//**todo 업로드 후 화면 전환 -> 목록으로*//*
                break;*/
            case R.id.posting_layout:
                /**todo 바탕 클릭시 키보드 숨기기 ..... 현재 동작안함 원인 모르겟음*/
                /*View view = this.getCurrentFocus();
                if (view != null) {
                    Log.i("권수정", "키보드 내려가");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
                }*/
                break;
        }

    }
    /** 스피너 선택 이벤트를 위한 메소드*/
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        waitTime = (String) parent.getItemAtPosition(position);
        Log.e("권수정", "waitTime : " + waitTime);
    }

    public static String getWaitTime(){
        return waitTime;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

   /* public static RequestParams getBoardListRequestParams(Context context){
        if(writeName.getText() ==null || passwd.getText() == null || studentNo.getText() == null || department.getText() == null || departure.getText() == null || departure_detail.getText() == null || destination.getText() == null || destination_detail.getText() == null || passengerNum.getText()==null){
            Toast.makeText(context, "전부 다 입력해주세요", Toast.LENGTH_SHORT).show();
            return null;
        }

        RequestParams params = new RequestParams();
        params.put("WRITE_NAME"         , writeName.getText());
        params.put("PASSWD"              , "test"*//*passwd.getText()*//*);
        params.put("STUDENT_NO"         , studentNo.getText());//학번
        params.put("DEPARTMENT"         , department.getText());//학과
        params.put("DEPARTURE"          , departure.getText());
        params.put("DEPARTURE_DETAIL"  , departure_detail.getText());
        params.put("DESTINATION"        , destination.getText());
        params.put("DESTINATION_DETAIL", destination_detail.getText());
        params.put("PASSENGER_NUM"      , passengerNum.getText());
        params.put("WAIT_TIME"           , waitTime);
        Log.i("권수정", "click upload button!");
        *//**todo 업로드 후 화면 전환 -> 목록으로*//*
        return params;
    }*/
}
