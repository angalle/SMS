package univ.sm.Model.item;

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
}
