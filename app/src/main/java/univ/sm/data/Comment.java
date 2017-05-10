package univ.sm.data;

/**
 * Created by heesun on 2017-04-18.
 */
public class Comment {

    private String COMMENT_NO;
    private String CALL_BOARD_NO;
    private String COMMENT_LEVEL;
    private String CONTENTS;
    private String REG_ID;
    private String WRITE_NAME;
    private String DEPARTMENT;
    private String SHARING_FLAG;
    private String BEFORE_COMMENT_NO;
    private String INSERT_TIME;
    private String INSERT_DATE;

    public Comment(String COMMENT_NO, String CALL_BOARD_NO, String COMMENT_LEVEL, String CONTENTS,
                   String REG_ID, String WRITE_NAME, String DEPARTMENT, String SHARING_FLAG,
                   String BEFORE_COMMENT_NO, String INSERT_TIME, String INSERT_DATE) {
        this.COMMENT_NO = COMMENT_NO;
        this.CALL_BOARD_NO = CALL_BOARD_NO;
        this.COMMENT_LEVEL = COMMENT_LEVEL;
        this.CONTENTS = CONTENTS;
        this.REG_ID = REG_ID;
        this.WRITE_NAME = WRITE_NAME;
        this.DEPARTMENT = DEPARTMENT;
        this.SHARING_FLAG = SHARING_FLAG;
        this.BEFORE_COMMENT_NO = BEFORE_COMMENT_NO;
        this.INSERT_TIME = INSERT_TIME;
        this.INSERT_DATE = INSERT_DATE;
    }

    public String getCOMMENT_NO() {
        return COMMENT_NO;
    }

    public String getCALL_BOARD_NO() {
        return CALL_BOARD_NO;
    }

    public String getCOMMENT_LEVEL() {
        return COMMENT_LEVEL;
    }

    public String getCONTENTS() {
        return CONTENTS;
    }

    public String getREG_ID() {
        return REG_ID;
    }

    public String getWRITE_NAME() {
        return WRITE_NAME;
    }

    public String getDEPARTMENT() {
        return DEPARTMENT;
    }

    public String getSHARING_FLAG() {
        return SHARING_FLAG;
    }

    public String getBEFORE_COMMENT_NO() {
        return BEFORE_COMMENT_NO;
    }

    public String getINSERT_TIME() {
        return INSERT_TIME;
    }

    public String getINSERT_DATE() {
        return INSERT_DATE;
    }

    public void setCOMMENT_NO(String COMMENT_NO) {

        this.COMMENT_NO = COMMENT_NO;
    }

    public void setCALL_BOARD_NO(String CALL_BOARD_NO) {
        this.CALL_BOARD_NO = CALL_BOARD_NO;
    }

    public void setCOMMENT_LEVEL(String COMMENT_LEVEL) {
        this.COMMENT_LEVEL = COMMENT_LEVEL;
    }

    public void setCONTENTS(String CONTENTS) {
        this.CONTENTS = CONTENTS;
    }

    public void setREG_ID(String REG_ID) {
        this.REG_ID = REG_ID;
    }

    public void setWRITE_NAME(String WRITE_NAME) {
        this.WRITE_NAME = WRITE_NAME;
    }

    public void setDEPARTMENT(String DEPARTMENT) {
        this.DEPARTMENT = DEPARTMENT;
    }

    public void setSHARING_FLAG(String SHARING_FLAG) {
        this.SHARING_FLAG = SHARING_FLAG;
    }

    public void setBEFORE_COMMENT_NO(String BEFORE_COMMENT_NO) {
        this.BEFORE_COMMENT_NO = BEFORE_COMMENT_NO;
    }

    public void setINSERT_TIME(String INSERT_TIME) {
        this.INSERT_TIME = INSERT_TIME;
    }

    public void setINSERT_DATE(String INSERT_DATE) {
        this.INSERT_DATE = INSERT_DATE;
    }
}
