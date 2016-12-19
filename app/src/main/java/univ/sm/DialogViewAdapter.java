package univ.sm;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

/**
 * Created by heesun on 2016-12-13.
 */

public class DialogViewAdapter extends PagerAdapter {

    private final Random random = new Random();
    private int mSize;
    public DialogViewAdapter() {
        this.mSize = 3;
    }
    public DialogViewAdapter(int mSize) {
        this.mSize = mSize;
    }

    @Override
    public int getCount() {
        return mSize;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    public void addItem() {
        mSize++;
        notifyDataSetChanged();
    }

    public void removeItem() {
        mSize--;
        mSize = mSize < 0 ? 0 : mSize;

        notifyDataSetChanged();
    }
}
