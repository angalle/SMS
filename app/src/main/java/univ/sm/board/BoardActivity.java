package univ.sm.board;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Objects;

import univ.sm.R;
import univ.sm.connect.LoopjConnection;
import univ.sm.data.BoardMainPageAdapter;
import univ.sm.data.Const;

import static univ.sm.R.id.passengerNum;

/**
 * 게시판 리스트 페이지
 * 데이터 불러오기, 새글올리기, 새로고침
 * Created by kwonsoojeong on 2017-03-02.
 *
 * Modify by leeheesun on 2017-04-xx
 * 코드 마음대로 수정해서 미안합니다 - ☆  정리만 해봤어요 기능은 같고 주석처리 해놨어용
 */

@SuppressWarnings("DefaultFileTemplate")
public class BoardActivity extends FragmentActivity implements View.OnClickListener,ViewTreeObserver.OnGlobalLayoutListener
                                                                        ,ViewPager.OnPageChangeListener{
    private TextView board_list,board_write;
    private ImageView refresh_btn,board_selector;
    private ViewPager vp;
    public static Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //기본 레이아웃 세팅
        setContentView(R.layout.board_main);
        fn_staticLayout();
        context = getApplicationContext();
    }

    /* 정적 view 초기화 */
    private void fn_staticLayout() {
        /* view bind */
        board_list = (TextView)findViewById(R.id.board_list);
        board_write = (TextView)findViewById(R.id.board_write);
        board_selector = (ImageView)findViewById(R.id.board_selector);
        refresh_btn = (ImageView) findViewById(R.id.refresh_btn);
        vp = (ViewPager)findViewById(R.id.borad_vPager);
        vp.setAdapter(new BoardMainPageAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);


        /* 이벤트 */
        board_list.setOnClickListener(this);
        board_list.setTag(0);
        board_write.setOnClickListener(this);
        board_list.setTag(1);
        refresh_btn.setOnClickListener(this);
        vp.addOnPageChangeListener(this);
        /* 이미지 좌표 재 설정*/
        board_selector.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onClick(View v) {
        //// TODO: 2017-04-29  프레그먼트의 tag값을 어떻게 가져올 수 있지?
        //int tag = (int) v.getTag();
        System.out.println("v.getId():::"+v.getId());
        if(v.getId() == R.id.board_list){
            moveImageBar(v);
            vp.setCurrentItem(0);
        }
        if(v.getId() == R.id.board_write){
            moveImageBar(v);
            vp.setCurrentItem(1);
        }
        if(v.getId() == R.id.refresh_btn){
            //// TODO: 2017-04-23  메인액티비티에서 리스트 프레그먼트의 데이터를 리프레쉬 하기.
            if(vp.getCurrentItem() == 0 ){
                //why board_list click ?
                //reason : refresh list
                board_list.performClick();
            }else if(vp.getCurrentItem() == 1 ){
                //BoardPostingFragment ff = (BoardPostingFragment)getFragmentManager().findFragmentById(R.layout.board_posting);
                BoardPostingFragment ff = (BoardPostingFragment)getSupportFragmentManager().findFragmentById(R.id.posting_layout);
                Post post = ff.sendParentClickData();

                LoopjConnection connection = LoopjConnection.getInstance(getApplicationContext());

                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout_view = inflater.inflate(R.layout.board_posting,null);

                EditText writeName = (EditText) layout_view.findViewById(R.id.writename_edit);
                EditText passwd = (EditText) layout_view.findViewById(R.id.passwd);
                EditText department = (EditText) layout_view.findViewById(R.id.department_std_edit); //학과
                EditText studentNo = (EditText) layout_view.findViewById(R.id.studentNo_edit);
                EditText departure = (EditText) layout_view.findViewById(R.id.departure_detail);
                EditText departure_detail= (EditText) layout_view.findViewById(R.id.departure_detail_edit);
                EditText destination = (EditText) layout_view.findViewById(R.id.destination_edit);
                EditText destination_detail = (EditText) layout_view.findViewById(R.id.destination_detail_edit);
                Spinner waitTimeSpinner = (Spinner) layout_view.findViewById(R.id.wait_time_spinner);
                Spinner peopleNumSpinner = (Spinner) layout_view.findViewById(R.id.passengerNum_spinner);

                waitTimeSpinner = (Spinner) layout_view.findViewById(R.id.wait_time_spinner);
                ArrayAdapter<CharSequence> waiteAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.wait_time_array, R.layout.support_simple_spinner_dropdown_item);
                waiteAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                waitTimeSpinner.setAdapter(waiteAdapter);

                peopleNumSpinner = (Spinner) layout_view.findViewById(R.id.passengerNum_spinner);
                ArrayAdapter<CharSequence> peopleAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.peopleNum_array, R.layout.support_simple_spinner_dropdown_item);
                waiteAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                peopleNumSpinner.setAdapter(peopleAdapter);


                //Writing  : hs
                //getText() : return Editable / Editable : not completely convert toString
                //therfore make sure to method : toString();
                String writeNameStr = writeName.getText().toString();
                String passwdStr = passwd.getText().toString();
                String departmentStr = department.getText().toString();
                String studentNoStr = studentNo.getText().toString();
                String departureStr = departure.getText().toString();
                String departure_detailStr = departure_detail.getText().toString();
                String destinationStr = destination.getText().toString();
                String destination_detailStr = destination_detail.getText().toString();
                SharedPreferences sp = getSharedPreferences(Const.SHARED_GCM, MODE_PRIVATE);
                String regId = sp.getString(Const.SHARED_REG_ID,"");
                String waitTime = waitTimeSpinner.getSelectedItem().toString();
                String peopleNum = peopleNumSpinner.getSelectedItem().toString();


                if("".equals(writeNameStr) ||"".equals(passwdStr) ||"".equals(studentNoStr) ||"".equals(departmentStr) || "".equals(peopleNum)||
                        "".equals(departureStr) ||/*"".equals(departure_detailStr) ||*/"".equals(destinationStr) /*||"".equals(destination_detailStr)*/ ){

                    Toast.makeText(getApplicationContext(), "전부 다 입력해주세요", Toast.LENGTH_SHORT).show();
                    return ;
                }

                /*RequestParams params = new RequestParams();
                params.put("WRITE_NAME"         , writeNameStr);
                params.put("PASSWD"              , regId);
                params.put("REG_ID"              , passwdStr);
                params.put("STUDENT_NO"         , studentNoStr);//학번
                params.put("DEPARTMENT"         , departmentStr);//학과
                params.put("DEPARTURE"          , departureStr);
                params.put("DEPARTURE_DETAIL"  , departure_detailStr);
                params.put("DESTINATION"        , destinationStr);
                params.put("DESTINATION_DETAIL", destination_detailStr);
                params.put("PASSENGER_NUM"      , peopleNum);
                params.put("WAIT_TIME"           , waitTime);*/

                RequestParams params = new RequestParams();
                params.put("WRITE_NAME"         , post.getWrite_name());
                params.put("PASSWD"              , post.getReg_id());
                params.put("REG_ID"              , post.getReg_id());
                params.put("STUDENT_NO"         , post.getStudent_no());//학번
                params.put("DEPARTMENT"         , post.getDepartment());//학과
                params.put("DEPARTURE"          , post.getDeparture());
                params.put("DEPARTURE_DETAIL"  , post.getDeparture_detail());
                params.put("DESTINATION"        , post.getDestination());
                params.put("DESTINATION_DETAIL", post.getDestination_detail());
                params.put("PASSENGER_NUM"      , post.getPassenger_num());
                params.put("WAIT_TIME"           , post.getWait_time());
                Log.i("권수정", "click upload button!");
                //todo 업로드 후 화면 전환 -> 목록으로
                connection.addPosting(params);
                board_list.performClick();
            }

        }
    }

    /* 주말선택의 빨간바를 이동하는 함수 */
    private void moveImageBar(View v){
        /* 이동해야할 x좌표 */
        float toX=v.getLeft();
        float toWidth=v.getWidth();

        float width = board_selector.getWidth();

        board_selector.animate().setDuration(150);
        board_selector.animate().scaleX(toWidth / width);
        board_selector.animate().translationX(toX-(width-toWidth)/2.0f).withLayer();
    }

    /*onCreate에서 먹지 않는 setWidth,getWidth 등의 함수들을 이 안에서 구현이 가능 하다.*/
    @Override
    public void onGlobalLayout() {
        board_selector.setX(board_list.getX());
        board_selector.getLayoutParams().width = board_list.getWidth();

        removeOnGlobalLayoutListener(board_selector.getViewTreeObserver(), this);
    }

    /*GlobalLayout을 사용할때 계속 호출 되는 경우가 있는데 Listener를 remove 시켜줘야 계속 호출이 반복되지 않음.*/
    private static void removeOnGlobalLayoutListener(ViewTreeObserver observer, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (observer == null) {
            return ;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            observer.removeGlobalOnLayoutListener(listener);
        } else {
            observer.removeOnGlobalLayoutListener(listener);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == 0){
            board_list.performClick();
        }else if(position == 1){
            board_write.performClick();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
