package univ.sm.view.board;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;

import univ.sm.view.board.list.BoardList_FView;
import univ.sm.view.board.regist.BoardPostingRegist_FView;

/**
 * Created by heesun on 2017-04-23.
 */
public class BoardMainPageAdapter extends FragmentStatePagerAdapter {
    FragmentTransaction ft = null;
    public BoardMainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                return BoardList_FView.newInstance(0);
            case 1:
                return BoardPostingRegist_FView.newInstance(1);
            default:
                return BoardList_FView.newInstance(0);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    HashMap<Integer,Fragment> mPageReferenceMap = new HashMap<Integer,Fragment>();

    public Fragment getFragment(int key) {
        return mPageReferenceMap.get(key);
    }
}
