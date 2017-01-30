package univ.sm.data;

import univ.sm.R;

/**
 * Created by LeeHeesun on 2017-01-26.
 */
public enum ModelObject {

    RED("test", R.layout.view_question_1),
    BLUE("test", R.layout.view_question_2),
    GREEN("test", R.layout.view_question_3),
    WHITE("test", R.layout.view_question_4);

    private String mTitleResId;
    private int mLayoutResId;

    ModelObject(String titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public String getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}
