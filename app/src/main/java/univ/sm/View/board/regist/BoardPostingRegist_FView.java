package univ.sm.View.board.regist;

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
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import univ.sm.R;
import univ.sm.Model.board.Board;
import univ.sm.Model.Const;
import univ.sm.View.board.BoardView;
import univ.sm.View.board.list.BoardList_FView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by heesun on 2017-04-23.
 */
public class BoardPostingRegist_FView extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    @BindView(R.id.departure_detail)EditText departure;
    @BindView(R.id.departure_detail_edit)EditText departure_detail;
    @BindView(R.id.destination_edit)EditText destination_detail;
    @BindView(R.id.destination_detail_edit)EditText destination;

    @BindView(R.id.passengerNum_spinner)Spinner passengerNum;
    @BindView(R.id.wait_time_spinner)Spinner waitTimeSpinner;

    FrameLayout l_layout;

    public static Context context;
    String regId="";

    public static BoardPostingRegist_FView newInstance(int index) {
        BoardPostingRegist_FView f = new BoardPostingRegist_FView();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public BoardPostingRegist_FView() {
        super();
        this.context = BoardView.context;
    }

    public Board sendParentClickData() {
        Board post = new Board();
        post.setDeparture(departure.getText().toString());
        post.setDeparture_detail(departure_detail.getText().toString());
        post.setDestination(destination.getText().toString());
        post.setDestination_detail(destination_detail.getText().toString());

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
        ButterKnife.bind(this,l_layout);
        set_initView(l_layout);
        /*입력 정보 자동 기억*/
        SharedPreferences sp = context.getSharedPreferences(Const.SHARED_GCM, MODE_PRIVATE);
        sp.getString(Const.PASSWD,"");
        sp.getString(Const.STUDENT_NO,"");
        sp.getString(Const.DEPARTMENT,"");
        return l_layout;
    }

    private void set_initView(View v){
        FrameLayout postingLayout = (FrameLayout) v.findViewById(R.id.posting_layout);
        postingLayout.setOnClickListener(this);

        ArrayAdapter<CharSequence> waiteAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.wait_time_array, R.layout.spin);
        waiteAdapter.setDropDownViewResource(R.layout.spin_dropdown);
        waitTimeSpinner.setAdapter(waiteAdapter);
        waitTimeSpinner.setOnItemSelectedListener(this);

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
