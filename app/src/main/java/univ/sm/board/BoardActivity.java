package univ.sm.board;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import android.view.inputmethod.InputMethodManager;
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
    private TextView board_list,board_write,subTitle;
    private ImageView refresh_btn,board_selector;
    private ViewPager vp;
    public static Context context;
    InputMethodManager imm;


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
        subTitle = (TextView)findViewById(R.id.subTitle);
        board_write = (TextView)findViewById(R.id.board_write);
        board_selector = (ImageView)findViewById(R.id.board_selector);
        refresh_btn = (ImageView) findViewById(R.id.refresh_btn);
        vp = (ViewPager)findViewById(R.id.borad_vPager);
        vp.setAdapter(new BoardMainPageAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

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
        /* viewpager에서 등록을 하던가 add 를해서 tag를 등록하면 가져올 수 있다.*/
        if(v.getId() == R.id.board_list){
            vp.setCurrentItem(0);
        }else if(v.getId() == R.id.board_write){
            vp.setCurrentItem(1);
            moveImageBar(v);
            subTitle.setText(Const.WRITE_TITLE);
        }else if(v.getId() == R.id.refresh_btn){
            //// TODO: 2017-04-23  메인액티비티에서 리스트 프레그먼트의 데이터를 리프레쉬 하기.
            /*해결*/
            if(vp.getCurrentItem() == 0 ){
                //propose : refresh list
                board_list.performClick();
            }else if(vp.getCurrentItem() == 1 ){
                /* 애초의 취지  :  등록 폼의 데이터들이 mainActivity의 event에서 접근이 안되서 문제가 발생.*/
                /* 경과  : interface선언, static 변수 선언 등으로 해결해 보려했으나 정방향의 문제 해결을 진행함*/
                /* getSupportFragmentManger의 사용법*/
                /* 보통 MainActivity의 fragment id를 넣어라 아래 코드와 같이 진행하는게 원칙.*/
                /* 상황에 따라 viewPager id값을 넣어서 하게 된다.*/
                BoardPostingFragment ff = (BoardPostingFragment)getSupportFragmentManager().findFragmentById(R.id.borad_vPager);
                Post post = ff.sendParentClickData();
                /*생각해보면 결국 viewPager도 fragment 이니까 해당 화면의 아이디를 불러오는 걸로 느껴진다.*/

                // 여기와 상관없지만 - EditText를 사용할때 String convert 주의점
                //Writing  : hs
                //getText() : return Editable / Editable : not completely convert toString
                //therfore make sure to method : toString();
                String writeNameStr             = post.getWrite_name();
                String passwdStr                = post.getPasswd();
                String departmentStr            = post.getDepartment();
                String studentNoStr             = post.getStudent_no();
                String departureStr             = post.getDeparture();
                String departure_detailStr      = post.getDeparture_detail();
                String destinationStr           = post.getDestination();
                String destination_detailStr    = post.getDestination_detail();

                String waitTime                 = post.getWait_time();
                String passengerNum             = post.getPassenger_num();

                RequestParams params = new RequestParams();
                params.put(Const.WRITE_NAME               , writeNameStr);
                params.put(Const.PASSWD                      , passwdStr);
                SharedPreferences sp = getSharedPreferences(Const.SHARED_GCM, MODE_PRIVATE);
                params.put(Const.REG_ID                       , sp.getString(Const.SHARED_REG_ID,""));
                params.put(Const.STUDENT_NO                  , studentNoStr);//학번
                params.put(Const.DEPARTMENT                  , departmentStr);//학과
                params.put(Const.DEPARTURE                   , departureStr);//출발지
                params.put(Const.DEPARTURE_DETAIL           , departure_detailStr);
                params.put(Const.DESTINATION                , destinationStr);
                params.put(Const.DESTINATION_DETAIL        , destination_detailStr);
                params.put(Const.PASSENGER_NUM              , passengerNum);
                params.put(Const.WAIT_TIME                  , waitTime);
                //todo 업로드 후 화면 전환 -> 목록으로

                if("".equals(writeNameStr) ||"".equals(passwdStr) ||"".equals(studentNoStr) ||"".equals(departmentStr) || "".equals(passengerNum)||
                        "".equals(departureStr) ||/*"".equals(departure_detailStr) ||*/"".equals(destinationStr) /*||"".equals(destination_detailStr)*/ ){
                    Toast.makeText(getApplicationContext(), "전부 다 입력해주세요", Toast.LENGTH_SHORT).show();
                    return ;
                }


                LoopjConnection connection = LoopjConnection.getInstance(getApplicationContext());
                connection.addPosting(params,board_list);
                Toast.makeText(getApplicationContext(), "입력된 정보가 저장됩니다.", Toast.LENGTH_SHORT).show();
                board_list.performClick();

                imm.hideSoftInputFromWindow(refresh_btn.getWindowToken(), 0);

                SharedPreferences.Editor spe = sp.edit();
                spe.putString(Const.WRITE_NAME,writeNameStr);
                spe.putString(Const.PASSWD,passwdStr);
                spe.putString(Const.STUDENT_NO,studentNoStr);
                //spe.putString(Const.REG_ID,"");
                spe.putString(Const.DEPARTMENT,departmentStr);
                //spe.putString(Const.DEPARTURE,"");
                //spe.putString(Const.DEPARTURE_DETAIL,"");
                //spe.putString(Const.DESTINATION,"");
                //spe.putString(Const.DESTINATION_DETAIL,"");
                //spe.putString(Const.PASSENGER_NUM,"");
                //spe.putString(Const.WAIT_TIME,"");
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

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == 0){
            moveImageBar(board_list);

            subTitle.setText(Const.LIST_TITLE);
            BoardListFragment.instance.getServerRequestData();
        }else if(position == 1){
            board_write.performClick();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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

}
