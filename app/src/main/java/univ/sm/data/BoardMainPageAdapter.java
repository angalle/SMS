package univ.sm.data;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

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
                return BoardList_FView.newInstatnce();
            case 1:
                return BoardPostingRegist_FView.newInstatnce();
            default:
                return BoardList_FView.newInstatnce();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
