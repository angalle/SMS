package univ.sm;

import java.util.ArrayList;

import univ.sm.data.SplashData;
import univ.sm.data.item.Shuttle;

/**
 * Created by PE_LHS on 2018-01-18.
 */

public class StaticData {
    /* 시간표 데이터 */
    public static ArrayList<Shuttle>[] positionShuttleArr = new ArrayList[SplashData.busUrl.length];

    String test ="";
    /* 시간표 데이터 */
    public static ArrayList<Shuttle> arrShuttle = new ArrayList();


    public static ArrayList<Shuttle> getArrShuttle(String siteCode, String weekdayCode) {
        /* 임시 배열 */
        ArrayList<Shuttle> temp = new ArrayList<Shuttle>();
        /* splash 에서 가져온 데이터 */
        ArrayList<Shuttle> pivotArr = arrShuttle;
        int index = 1;
        for(int i =0 ; i < pivotArr.size() ; i ++){
            /* 같은 장소중에서*/
            if(siteCode.equals(pivotArr.get(i).getSTOP_SITE_CODE())){
                /* 같은 요일인것들.*/
                if(weekdayCode.equals(pivotArr.get(i).getWEEKDAY_CODE())){
                    temp.add(pivotArr.get(i));
                }
            }
        }

        return temp;
    }

}
