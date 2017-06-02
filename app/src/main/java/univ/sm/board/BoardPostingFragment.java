package univ.sm.board;

import android.app.Activity;
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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import univ.sm.R;
import univ.sm.connect.LoopjConnection;
import univ.sm.data.Const;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by heesun on 2017-04-23.
 */
public class BoardPostingFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    Context context;
    private EditText writeName,department,studentNo,departure,departure_detail,destination_detail,destination,passwd;
    private Spinner passengerNum,waitTimeSpinner;
    String regId="";
    FrameLayout l_layout;


    public static BoardPostingFragment newInstatnce(){
        BoardPostingFragment b = new BoardPostingFragment();
        return b;
    }

    public BoardPostingFragment() {
        super();
        this.context = BoardActivity.context;

    }

    public Post sendParentClickData() {
        Post post = new Post();
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


        System.out.println("writeNameStr::::::::"+department.getText().toString());
        System.out.println("passwdStr::::::::"+writeName.getText().toString());
        System.out.println("studentNoStr::::::::"+studentNo.getText().toString());
        System.out.println("departmentStr::::::::"+departure.getText().toString());
        System.out.println("peopleNum::::::::"+departure_detail.getText().toString());
        System.out.println("departureStr::::::::"+destination.getText().toString());
        return post;
    }

    @Override
      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        l_layout = (FrameLayout) inflater.inflate(R.layout.board_posting,container,false);
        set_initView(l_layout);
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
        departure_detail   = (EditText) v.findViewById(R.id.departure_detail_edit);
        destination         = (EditText) v.findViewById(R.id.destination_edit);
        destination_detail = (EditText) v.findViewById(R.id.destination_detail_edit);
        passwd               = (EditText) v.findViewById(R.id.passwd);

        waitTimeSpinner = (Spinner) v.findViewById(R.id.wait_time_spinner);
        ArrayAdapter<CharSequence> waiteAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.wait_time_array, R.layout.support_simple_spinner_dropdown_item);
        waiteAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        waitTimeSpinner.setAdapter(waiteAdapter);
        waitTimeSpinner.setOnItemSelectedListener(this);

        passengerNum = (Spinner) v.findViewById(R.id.passengerNum_spinner);
        ArrayAdapter<CharSequence> peopleAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.peopleNum_array, R.layout.support_simple_spinner_dropdown_item);
        waiteAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
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
