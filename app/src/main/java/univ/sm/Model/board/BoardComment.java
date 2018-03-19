package univ.sm.Model.board;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by heesun on 2017-04-18.
 */
public class BoardComment implements Serializable{

    @SerializedName("COMMENT_NO")       private String COMMENT_NO;
    @SerializedName("CALL_BOARD_NO")    private String CALL_BOARD_NO;
    @SerializedName("COMMENT_LEVEL")    private String COMMENT_LEVEL;
    @SerializedName("CONTENTS")         private String CONTENTS;
    @SerializedName("MEMBER_REG_NO")    private String MEMBER_REG_NO;
    @SerializedName("MEMBER_NAME")      private String MEMBER_NAME;
    @SerializedName("MEMBER_DEPARTMENT")private String DEPARTMENT;
    @SerializedName("SHARING_FLAG")     private String SHARING_FLAG;
    @SerializedName("BEFORE_COMMENT_NO")private String BEFORE_COMMENT_NO;
    @SerializedName("INSERT_TIME")      private String INSERT_TIME;
    @SerializedName("INSERT_DATE")      private String INSERT_DATE;

    public BoardComment() {
    }

    public String getCOMMENT_NO() {
        return COMMENT_NO;
    }

    public void setCOMMENT_NO(String COMMENT_NO) {
        this.COMMENT_NO = COMMENT_NO;
    }

    public String getCALL_BOARD_NO() {
        return CALL_BOARD_NO;
    }

    public void setCALL_BOARD_NO(String CALL_BOARD_NO) {
        this.CALL_BOARD_NO = CALL_BOARD_NO;
    }

    public String getCOMMENT_LEVEL() {
        return COMMENT_LEVEL;
    }

    public void setCOMMENT_LEVEL(String COMMENT_LEVEL) {
        this.COMMENT_LEVEL = COMMENT_LEVEL;
    }

    public String getCONTENTS() {
        return CONTENTS;
    }

    public void setCONTENTS(String CONTENTS) {
        this.CONTENTS = CONTENTS;
    }

    public String getMEMBER_REG_NO() {
        return MEMBER_REG_NO;
    }

    public void setMEMBER_REG_NO(String MEMBER_REG_NO) {
        this.MEMBER_REG_NO = MEMBER_REG_NO;
    }

    public String getMEMBER_NAME() {
        return MEMBER_NAME;
    }

    public void setMEMBER_NAME(String MEMBER_NAME) {
        this.MEMBER_NAME = MEMBER_NAME;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
    }

    public String getSHARING_FLAG() {
        return SHARING_FLAG;
    }

    public void setSHARING_FLAG(String SHARING_FLAG) {
        this.SHARING_FLAG = SHARING_FLAG;
    }

    public String getBEFORE_COMMENT_NO() {
        return BEFORE_COMMENT_NO;
    }

    public void setBEFORE_COMMENT_NO(String BEFORE_COMMENT_NO) {
        this.BEFORE_COMMENT_NO = BEFORE_COMMENT_NO;
    }

    public String getINSERT_TIME() {
        return INSERT_TIME;
    }

    public void setINSERT_TIME(String INSERT_TIME) {
        this.INSERT_TIME = INSERT_TIME;
    }

    public String getINSERT_DATE() {
        return INSERT_DATE;
    }

    public void setINSERT_DATE(String INSERT_DATE) {
        this.INSERT_DATE = INSERT_DATE;
    }
}
