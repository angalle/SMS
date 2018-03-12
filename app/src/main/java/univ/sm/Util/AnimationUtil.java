package univ.sm.Util;

import android.view.View;


/**
 * Created by PE_LHS on 2018-01-18.
 */

public class AnimationUtil {
    /* 클릭한 곳의 길이를 구해 동적으로 width를 변환하는 함수 */
    public static void moveImageBar(View pivot,View target){
        /* 이동해야할 x좌표 */
        float toX       =   pivot.getLeft();
        float toWidth   =   pivot.getWidth();

        float width = target.getWidth();
        target.animate().scaleX(toWidth/width);
        target.animate().translationX(toX-(width-toWidth)/2.0f).withLayer();
    }

}
