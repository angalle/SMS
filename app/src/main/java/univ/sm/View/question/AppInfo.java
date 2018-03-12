package univ.sm.View.question;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.RadioGroup;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import univ.sm.R;


/**
 * Created by heesun on 2017-02-23.
 * 물음표페이지 2번재 구현 방법.
 * 다이얼로그 형태로 보여주는 방법.
 * 현재 사용안함.
 *
 *
 * MainView에 추가 할 부분
 * case R.id.app_info_btn:
 final AppInfo dialog = new AppInfo(this);
 dialog.setOnShowListener(new DialogInterface.OnShowListener() {
        @Override
        public void onShow(DialogInterface dialog) {
        //나타날때 쓰는 효과
        }
    });
 dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
        //사라질때 하는 효과
        }
    });
 dialog.show();
 *
 *
 *
 *
 *
 *
 */
public class AppInfo extends Dialog {

    RadioGroup mPageGroup;
    ViewPager pager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.question_1,R.drawable.question_2,R.drawable.question_3,R.drawable.question_4};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    Context context;
    public AppInfo(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_question);

        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new DialogViewAdapter(getContext(),ImagesArray));

        CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        final float density = context.getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;


        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
}

