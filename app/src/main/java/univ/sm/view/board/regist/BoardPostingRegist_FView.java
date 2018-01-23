package univ.sm.view.board.regist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import univ.sm.R;
import univ.sm.data.Board;
import univ.sm.data.Const;
import univ.sm.view.board.BoardView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by heesun on 2017-04-23.
 */
public class BoardPostingRegist_FView extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    Context context;
    private EditText writeName,department,studentNo,departure,departure_detail,destination_detail,destination,passwd;
    private Spinner passengerNum,waitTimeSpinner;
    String regId="";
    FrameLayout l_layout;


    public static BoardPostingRegist_FView newInstatnce(){
        BoardPostingRegist_FView b = new BoardPostingRegist_FView();
        return b;
    }

    public BoardPostingRegist_FView() {
        super();
        this.context = BoardView.context;

    }

    public Board sendParentClickData() {
        Board post = new Board();
        post.setDepartment(department.getText().toString());
        post.setWrite_name(writeName.getText().toString());
        post.setStudent_no(studentNo.getText().toString());
        post.setDeparture(departure.getText().toString());
        post.setDeparture_detail(departure_detail.getText().toString());
        post.setDestination(destination.getText().toString());
        post.setDestination_detail(destination_detail.getText().toString());
        post.setPasswd(passwd.getText().toString());

        post.setPassenger_num(passengerNum.getSelectedItem().toString());
        post.setWait_time(waitTimeSpinner.getSelectedItem().toString());

        return post;
    }

    @Override
      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*fragment  view 가져와 초기화.*/
        l_layout = (FrameLayout) inflater.inflate(R.layout.board_posting,container,false);
        set_initView(l_layout);

        /*입력 정보 자동 기억*/
        SharedPreferences sp = context.getSharedPreferences(Const.SHARED_GCM, MODE_PRIVATE);
        sp.getString(Const.PASSWD,"");
        sp.getString(Const.STUDENT_NO,"");
        sp.getString(Const.DEPARTMENT,"");
        writeName.setText(sp.getString(Const.WRITE_NAME,""));
        passwd.setText(sp.getString(Const.WRITE_NAME,""));
        studentNo.setText(sp.getString(Const.WRITE_NAME,""));
        departure.setText(sp.getString(Const.WRITE_NAME,""));
        return l_layout;
    }

    private void set_initView(View v){
        FrameLayout postingLayout = (FrameLayout) v.findViewById(R.id.posting_layout);
        postingLayout.setOnClickListener(this);
        //post parametars..
        writeName           = (EditText) v.findViewById(R.id.writename_edit);
        department          = (EditText) v.findViewById(R.id.department_std_edit); //학과
        studentNo           = (EditText) v.findViewById(R.id.studentNo_edit);
        departure           = (EditText) v.findViewById(R.id.departure_detail);
        departure_detail    = (EditText) v.findViewById(R.id.departure_detail_edit);
        destination         = (EditText) v.findViewById(R.id.destination_edit);
        destination_detail  = (EditText) v.findViewById(R.id.destination_detail_edit);
        passwd              = (EditText) v.findViewById(R.id.passwd);

        waitTimeSpinner = (Spinner) v.findViewById(R.id.wait_time_spinner);
        ArrayAdapter<CharSequence> waiteAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.wait_time_array, R.layout.spin);
        waiteAdapter.setDropDownViewResource(R.layout.spin_dropdown);
        waitTimeSpinner.setAdapter(waiteAdapter);
        waitTimeSpinner.setOnItemSelectedListener(this);

        passengerNum = (Spinner) v.findViewById(R.id.passengerNum_spinner);
        ArrayAdapter<CharSequence> peopleAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.peopleNum_array, R.layout.spin);
        waiteAdapter.setDropDownViewResource(R.layout.spin_dropdown);
        passengerNum.setAdapter(peopleAdapter);
        passengerNum.setOnItemSelectedListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
    /** 스피너 선택 이벤트를 위한 메소드*/
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
