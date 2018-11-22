package univ.sm.model;

import java.util.Calendar;

/**
 * Created by uaer on 2017-06-21.
 */

public class Utility {

    public static int getCurrentDate2int() {
        Calendar oCalendar = Calendar.getInstance( );  // 현재 날짜/시간 등의 각종 정보 얻기
        // 1     2     3     4     5     6     7
        final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
        return oCalendar.get(Calendar.DAY_OF_WEEK);
    }
}
