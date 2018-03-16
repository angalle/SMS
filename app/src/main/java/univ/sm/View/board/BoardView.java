package univ.sm.View.board;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import univ.sm.Controller.CommonCallbak;
import univ.sm.Controller.api.board.BoardService;
import univ.sm.Model.Const;
import univ.sm.Model.User;
import univ.sm.Model.board.Board;
import univ.sm.R;
import univ.sm.Util.CommonUtil;
import univ.sm.View.CommonView;
import univ.sm.View.board.list.BoardList_FView;
import univ.sm.View.board.regist.BoardPostingRegist_FView;


/**
 * 게시판 리스트 페이지
 * 데이터 불러오기, 새글올리기, 새로고침
 * Created by kwonsoojeong on 2017-03-02.
 *
 * Modify by leeheesun on 2017-04-xx
 * 코드 마음대로 수정해서 미안합니다 - ☆  정리만 해봤어요 기능은 같고 주석처리 해놨어용
 */

@SuppressWarnings("DefaultFileTemplate")
public class BoardView extends CommonView implements View.OnClickListener,ViewTreeObserver.OnGlobalLayoutListener
                                                                        ,ViewPager.OnPageChangeListener{
    private TextView board_list,board_write,subTitle;
    private ImageView refresh_btn,board_selector;
    private ViewPager vp;
    public static Context context;
    public static Activity activity;
    InputMethodManager imm;
    private Intent intent;
    private User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //기본 레이아웃 세팅
        setContentView(R.layout.board_main);
        fn_staticLayout();
        context = this;
        activity = this;
        intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
    }

    /* 새로고침을 하기위한 메소드 추가. */
    @Override
    protected void onResume() {
        super.onResume();
        vp.setCurrentItem(0);
        //refresh_btn.performClick();
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
    HashMap<String,Object> params = new HashMap<String,Object>();
    @Override
    public void onClick(View v) {

        //// TODO: 2017-04-29  프레그먼트의 tag값을 어떻게 가져올 수 있지?
        /* viewpager에서 등록을 하던가 add 를해서 tag를 등록하면 가져올 수 있다.*/
        if(v.getId() == R.id.board_list){
            vp.setCurrentItem(0);
        }else if(v.getId() == R.id.board_write){
            vp.setCurrentItem(1);
        }else if(v.getId() == R.id.refresh_btn){
            //// TODO: 2017-04-23  메인액티비티에서 리스트 프레그먼트의 데이터를 리프레쉬 하기.
            /*해결*/
            if(vp.getCurrentItem() == 0 ){
                //propose : refresh list
                vp.setCurrentItem(1);
                vp.setCurrentItem(0);
            }else if(vp.getCurrentItem() == 1 ){
                BoardPostingRegist_FView ff = (BoardPostingRegist_FView)getSupportFragmentManager().findFragmentById(R.id.borad_vPager);
                Board post = ff.sendParentClickData();

                params = getPostRequestParams(post);
                if(!params.isEmpty()){
                    Log.e("SchCall ::::::", "call data::::"+params.isEmpty());
                    BoardService.getInstance(context).createApi().addBoard(params, new CommonCallbak() {
                        @Override
                        public void onError(Throwable t) {
                            Toast.makeText(getApplicationContext(), Const.MSG_ERROR, Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }

                        @Override
                        public void onSuccess(int code, Object receiveData) {
                            Log.e("onSuccess ::::::", "call data::::::" + code);
                            if(code == 200){
                                Gson gson = new Gson();
                                JsonObject jObject = gson.fromJson(receiveData.toString(),JsonObject.class);
                                String result = jObject.get("Result").getAsString();
                                Log.e("result ::::::", "result::::::" + result);
                                if("true".equals(result)){
                                    Toast.makeText(getApplicationContext(), "등록되었습니다.", Toast.LENGTH_SHORT).show();
                                    CommonUtil.nextPage(new Intent(BoardView.this,BoardView.class),activity);
                                }else{
                                    Toast.makeText(getApplicationContext(), "오류가 발생하였습니다. 관리자에게 문의하세요.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(int code) {
                            Toast.makeText(getApplicationContext(), Const.MSG_FAIL, Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "모든 항목을 입력해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    /*BoardPostingFragmenet (등록화면)에 있는 값들을 모두 가져온다.*/
    private HashMap<String,Object> getPostRequestParams(Board post){
        HashMap<String,Object> params           = new HashMap<String,Object>();

        String departureStr                     = post.getDeparture();
        String departure_detailStr              = post.getDeparture_detail();
        String destinationStr                   = post.getDestination();
        String destination_detailStr            = post.getDestination_detail();

        String waitTime                         = post.getWait_time().replace("분","");
        String passengerNum                     = post.getPassenger_num().replace("명","");
        SharedPreferences sp1                   = getSharedPreferences(Const.SHARED_GCM, MODE_PRIVATE);
        SharedPreferences sp2                   = getSharedPreferences(Const.SHARED_USER, MODE_PRIVATE);

        params.put(Const.REG_ID                   , sp1.getString(Const.SHARED_REG_ID,""));
        params.put(Const.MEMBER_EMAIL             , sp2.getString(Const.SHARED_MEMBER_EMAIL,""));//학과

        params.put(Const.DEPARTURE                , departureStr);//출발지
        params.put(Const.DEPARTURE_DETAIL         , departure_detailStr);
        params.put(Const.DESTINATION              , destinationStr);
        params.put(Const.DESTINATION_DETAIL       , destination_detailStr);
        params.put(Const.PASSENGER_NUM            , passengerNum);
        params.put(Const.WAIT_TIME                , waitTime);
        //todo 업로드 후 화면 전환 -> 목록으로

        if("".equals(passengerNum)||"".equals(departureStr) ||/*"".equals(departure_detailStr) ||*/"".equals(destinationStr) /*||"".equals(destination_detailStr)*/ ){
            Toast.makeText(getApplicationContext(), "전부 다 입력해주세요", Toast.LENGTH_SHORT).show();
            return new HashMap<String,Object>();
        }else{
            return params;
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
            BoardList_FView.instance.getServerRequestData();
            Picasso.with(context).load(R.drawable.quick_btn).resize(100,110).into(refresh_btn);
        }else if(position == 1){
            moveImageBar(board_write);
            subTitle.setText(Const.WRITE_TITLE);
            Picasso.with(context).load(R.drawable.regist_bottom_btn).resize(100,130).into(refresh_btn);
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
