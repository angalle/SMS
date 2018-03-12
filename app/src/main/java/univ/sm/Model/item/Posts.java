package univ.sm.Model.item;


import java.util.ArrayList;

import univ.sm.Model.BoardComment;

/**
 * 게시글 리스트  +  게시글 상세페이지
 * Created by heesun on 2017-03-24.
 */

public class Posts {
    private String BOARD_NO;                //게시글 번호 CALL_BOARD_NO
    private String WRITE_NAME;              //작성자 명
    private String PASSWORD;                  //비밀번호
    private String DEPARTMENT;              //학과
    private String STUDENT_NO;              //학번
    private String DEPARTURE;               //출발지
    private String DEPARTURE_DETAIL;        //출발지 설명
    private String DESTINATION;             //목적지
    private String DESTINATION_DETAIL;      //목적지설명
    private String REG_ID;                  //gcm(push) 기기값
    private String USE_FLAG;               //사용 flag Y/N
    private String PASSENGER_NUM;           //총 인원
    private String WAIT_TIME;               //대기시간  남은시간 표현하려면 현재시간 - 대기시간 = 남은시간.  이렇게 해도되고... return value 에 추가를 해줄까 ...
    private String INSERT_TIME;             //등록시간
    private String INSERT_DATE;             //등록날짜
    private String REMAIN_TIME;             //등록날짜
    private int COMMENT_CNT;                //코멘트 개수

    private ArrayList<BoardComment> commentsList; //댓글리스트


    /**
     * 리스트 페이지 객체
     */
    public Posts(String BOARD_NO,   String WRITE_NAME,      String PASSWORD,      String DEPARTMENT,      String STUDENT_NO,      String DEPARTURE,
                 String DEPARTURE_DETAIL,                   String DESTINATION, String DESTINATION_DETAIL,                      String REG_ID, 
                 String USE_FLAG,   String PASSENGER_NUM,   String WAIT_TIME,   String INSERT_TIME,     String INSERT_DATE,     String REMAIN_TIME,
                 int COMMENT_CNT) {

        
        
        this.BOARD_NO = BOARD_NO;   this.WRITE_NAME = WRITE_NAME;   this.PASSWORD = PASSWORD;   this.DEPARTMENT = DEPARTMENT;   this.STUDENT_NO = STUDENT_NO;
        this.DEPARTURE = DEPARTURE; this.DEPARTURE_DETAIL = DEPARTURE_DETAIL;               this.DESTINATION = DESTINATION; this.DESTINATION_DETAIL = DESTINATION_DETAIL;
        this.REG_ID = REG_ID;       this.USE_FLAG = USE_FLAG;       this.PASSENGER_NUM = PASSENGER_NUM;                     this.WAIT_TIME = WAIT_TIME;
        this.INSERT_TIME = INSERT_TIME;                             this.INSERT_DATE = INSERT_DATE;                         this.REMAIN_TIME = REMAIN_TIME;
        this.COMMENT_CNT = COMMENT_CNT;
    }
    /**
     * 상세페이지 객체
     */
    public Posts(String BOARD_NO,   String WRITE_NAME,      String PASSWORD,      String DEPARTMENT,  String STUDENT_NO,  String DEPARTURE,   String DEPARTURE_DETAIL,
                 String DESTINATION,String DESTINATION_DETAIL,                  String REG_ID,      String USE_FLAG,     String PASSENGER_NUM,
                 String WAIT_TIME,  String INSERT_TIME,     String INSERT_DATE, String REMAIN_TIME, int COMMENT_CNT,    ArrayList<BoardComment> commentList) {

        
        this.BOARD_NO = BOARD_NO;   this.WRITE_NAME = WRITE_NAME;   this.PASSWORD = PASSWORD;   this.DEPARTMENT = DEPARTMENT;   this.STUDENT_NO = STUDENT_NO;
        this.DEPARTURE = DEPARTURE; this.DEPARTURE_DETAIL = DEPARTURE_DETAIL;               this.DESTINATION = DESTINATION; this.DESTINATION_DETAIL = DESTINATION_DETAIL;
        this.REG_ID = REG_ID;       this.USE_FLAG = USE_FLAG;       this.PASSENGER_NUM = PASSENGER_NUM;                     this.WAIT_TIME = WAIT_TIME;
        this.INSERT_TIME = INSERT_TIME;                             this.INSERT_DATE = INSERT_DATE;                         this.COMMENT_CNT = COMMENT_CNT;
        this.REMAIN_TIME = REMAIN_TIME;                             this.commentsList = commentList;
    }


    /**
     * 게시글 Detailpage 에서 새로고침시 사용
     * 게시글 새로고침하기
     *
     * @param refreshPost
     */
    public void refreshPost(Posts refreshPost) {
        this.STUDENT_NO = refreshPost.STUDENT_NO;   this.BOARD_NO = refreshPost.BOARD_NO;   this.WRITE_NAME = refreshPost.WRITE_NAME;   this.PASSWORD = refreshPost.PASSWORD;   
        this.DEPARTMENT = refreshPost.DEPARTMENT;   this.DEPARTURE = refreshPost.DEPARTURE; this.DEPARTURE_DETAIL = refreshPost.DEPARTURE_DETAIL;
        this.DESTINATION = refreshPost.DESTINATION; this.DESTINATION_DETAIL = refreshPost.DESTINATION_DETAIL;                           this.REG_ID = refreshPost.REG_ID;
        this.USE_FLAG = refreshPost.USE_FLAG;       this.PASSENGER_NUM = refreshPost.PASSENGER_NUM;                                     this.WAIT_TIME = refreshPost.WAIT_TIME;
        this.INSERT_TIME = refreshPost.INSERT_TIME; this.INSERT_DATE = refreshPost.INSERT_DATE;                                         this.COMMENT_CNT = refreshPost.COMMENT_CNT;
        this.REMAIN_TIME = refreshPost.REMAIN_TIME;
    }

}
