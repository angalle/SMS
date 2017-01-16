package univ.sm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by heesun on 2016-12-13.
 */

public class SchEntry extends AppCompatActivity implements View.OnClickListener,ViewTreeObserver.OnGlobalLayoutListener{
    TextView schDetailWeekDay,  schDetailSatureDay,  schDetailSunDay;
    ImageView schDetailTopBar,  detail_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_entry);

    }

    @Override
    public void onClick(View v) {
        float toX = 0, toWidth = 0;      /* 이동해야할 x좌표 */
        if (v.getId() == R.id.sch_entry_weekDay) {
            toX = v.getLeft();
            toWidth = v.getWidth();
            moveImageBar(toWidth, toX);
        } else if (v.getId() == R.id.sch_entry_satureDay) {
            toX = v.getLeft();
            toWidth = v.getWidth();
            moveImageBar(toWidth, toX);
        } else if (v.getId() == R.id.sch_entry_sunDay) {
            toX = v.getLeft();
            toWidth = v.getWidth();
            moveImageBar(toWidth, toX);
        } else if (v.getId() == R.id.detail_btn) {

        }
    }
    private void moveImageBar(float toWidth,float toX){
        float width = schDetailTopBar.getWidth();
        schDetailTopBar.animate().scaleX(toWidth/width);
        schDetailTopBar.animate().translationX(toX-(width-toWidth)/2.0f).withLayer();
    }


    private void initView(){
        /*주말/토요일/일요일 텍스트 이벤트*/
        schDetailWeekDay = (TextView) findViewById(R.id.sch_entry_weekDay);
        schDetailSatureDay = (TextView) findViewById(R.id.sch_entry_satureDay);
        schDetailSunDay = (TextView) findViewById(R.id.sch_entry_sunDay);
        detail_btn = (ImageView) findViewById(R.id.detail_btn);

        /* 각 버튼 마다 이벤트 리스너*/
        schDetailWeekDay.setOnClickListener(this);
        schDetailSatureDay.setOnClickListener(this);
        schDetailSunDay.setOnClickListener(this);

        /* 기본좌표 재 설정 / xml 상에서 정확한 좌표를 표시 할 수 없음*/
        schDetailTopBar = (ImageView) findViewById(R.id.sch_entry_top_bar);
        schDetailTopBar.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        schDetailTopBar.setX(schDetailWeekDay.getX());
        schDetailTopBar.getLayoutParams().width = schDetailWeekDay.getWidth();
    }
}
