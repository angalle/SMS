package univ.sm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * Created by heesun on 2017-02-23.
 * 물음표페이지 2번재 구현 방법.
 * 액티비티 띄워서 풀화면으로 구현.
 * 일단 사용 안함.
 */

public class InfoActivity extends CommonActivity {
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
