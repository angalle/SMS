package univ.sm.view.question;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.widget.RadioGroup;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

import univ.sm.R;
import univ.sm.view.CommonView;

/**
 * Created by heesun on 2017-02-23.
 * 물음표페이지 2번재 구현 방법.
 * 디자이너 결정으로
 * 전체화면을 띄우는 이 뷰를 사용.
 */

public class InfoView extends CommonView {
    RadioGroup mPageGroup;
    ViewPager pager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.question_1,R.drawable.question_2,R.drawable.question_3,R.drawable.question_4};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_question);

        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new DialogViewAdapter(getApplicationContext(),ImagesArray));

        CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);

        final float density = getApplicationContext().getResources().getDisplayMetrics().density;

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
