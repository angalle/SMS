package univ.sm.data;

/**
 * Created by heesun on 2017-01-11.
 */

public class SchDetail_D {
    String pivotTime;
    String pivotTimeFirst;
    String pivotTimeSecond;
    String pivotTimeThird;


    public SchDetail_D(String pivotTime, String pivotTimeFirst, String pivotTimeSecond, String pivotTimeThird) {
        this.pivotTime = pivotTime;
        this.pivotTimeFirst = pivotTimeFirst;
        this.pivotTimeSecond = pivotTimeSecond;
        this.pivotTimeThird = pivotTimeThird;
    }

    public SchDetail_D() {

    }

    public String getPivotTime() {
        return pivotTime;
    }

    public void setPivotTime(String pivotTime) {
        this.pivotTime = pivotTime;
    }

    public String getPivotTimeFirst() {
        return pivotTimeFirst;
    }

    public void setPivotTimeFirst(String pivotTimeFirst) {
        this.pivotTimeFirst = pivotTimeFirst;
    }

    public String getPivotTimeSecond() {
        return pivotTimeSecond;
    }

    public void setPivotTimeSecond(String pivotTimeSecond) {
        this.pivotTimeSecond = pivotTimeSecond;
    }

    public String getPivotTimeThird() {
        return pivotTimeThird;
    }

    public void setPivotTimeThird(String pivotTimeThird) {
        this.pivotTimeThird = pivotTimeThird;
    }
}
