package univ.sm;

import android.animation.ObjectAnimator;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.logging.Log;

import java.util.logging.Logger;

/**
 * Created by heesun on 2016-12-13.
 */

public class SchDetail extends AppCompatActivity implements View.OnClickListener,ViewTreeObserver.OnGlobalLayoutListener {
    ImageView schDetailTopBar;
    TextView schDetailWeekDay,schDetailSatureDay,schDetailSunDay;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sch_detail);
        schDetailWeekDay = (TextView) findViewById(R.id.sch_detail_weekDay);
        schDetailSatureDay = (TextView) findViewById(R.id.sch_detail_satureDay);
        schDetailSunDay = (TextView) findViewById(R.id.sch_detail_sunDay);

        schDetailTopBar = (ImageView) findViewById(R.id.sch_detail_top_bar);

        //schDetailTopBar.setX(schDetailWeekDay.getX());

        System.out.println("==================================");
        System.out.println(schDetailWeekDay.getX());
        System.out.println(schDetailWeekDay.getY());

        schDetailTopBar.getViewTreeObserver().addOnGlobalLayoutListener(this);

        schDetailWeekDay.setOnClickListener(this);
        schDetailSatureDay.setOnClickListener(this);
        schDetailSunDay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        float toX=0,toWidth=0;
        if(v.getId() == R.id.sch_detail_weekDay){
            toX = v.getLeft();
            toWidth = v.getWidth();
        }else if(v.getId() == R.id.sch_detail_satureDay){
            toX = v.getLeft();
            toWidth = v.getWidth();
        }else if(v.getId() == R.id.sch_detail_sunDay){
            toX = v.getLeft();
            toWidth = v.getWidth();
        }


        float width = schDetailTopBar.getWidth();
        System.out.println(width);
        System.out.println(toX);

        schDetailTopBar.animate().scaleX(toWidth/width);


        schDetailTopBar.animate().translationX(toX-(width-toWidth)/2.0f).withLayer();





        //scaleX(toWidth);
        //사이즈 변화
        //ScaleAnimation scaleAnimation = new ScaleAnimation(schDetailTopBar.getWidth(),toWidth,schDetailTopBar.getHeight(),toHeigth);
        //scaleAnimation.setDuration(1000);
        //scaleAnimation.start();
        //ObjectAnimator ani = ObjectAnimator.ofFloat(schDetailTopBar,"scaleX",toWidth);
        //ani.setDuration(1000);
        //ani.start();
    }

    @Override
    public void onGlobalLayout() {
        schDetailTopBar.setX(schDetailWeekDay.getX());
        //schDetailTopBar.setMaxWidth(schDetailWeekDay.getWidth());
        schDetailTopBar.getLayoutParams().width = schDetailWeekDay.getWidth();
        //schDetailTopBar.setMaxWidth(schDetailWeekDay.getWidth());
    }
}
