package univ.sm.Model.board;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * 게시글 data 객체
 * Created on 2017-03-24.
 */

public class Board {
    @SerializedName("board_no")
    private String board_no;                //게시글 번호 CALL_BOARD_NO
    @SerializedName("write_name")
    private String write_name;              //작성자 명
    @SerializedName("passwd")
    private String passwd;                  //비밀번호
    @SerializedName("department")
    private String department;              //학과
    @SerializedName("student_no")
    private String student_no;              //학번
    @SerializedName("departure")
    private String departure;               //출발지
    @SerializedName("departure_detail")
    private String departure_detail;        //출발지 설명
    @SerializedName("destination")
    private String destination;             //목적지
    @SerializedName("destination_detail")
    private String destination_detail;      //목적지설명
    @SerializedName("reg_id")
    private String reg_id;                  //gcm(push) 기기값
    @SerializedName("use_flag")
    private Boolean use_flag;               //사용 flag Y/N
    @SerializedName("passenger_num")
    private String passenger_num;           //총 인원
    @SerializedName("wait_time")
    private String wait_time;               //대기시간  남은시간 표현하려면 현재시간 - 대기시간 = 남은시간.  이렇게 해도되고... return value 에 추가를 해줄까 ...
    @SerializedName("insert_time")
    private String insert_time;             //등록시간
    @SerializedName("insert_date")
    private String insert_date;             //등록날짜
    @SerializedName("remain_time")
    private String remain_time;             //등록날짜
    @SerializedName("comment_cnt")
    private int comment_cnt;                //코멘트 개수

    @SerializedName("commentsList")
    private ArrayList<BoardComment> commentsList; //댓글리스트

    public Board(String board_no, String write_name, String passwd, String department,
                 String student_no, String departure, String departure_detail, String destination,
                 String destination_detail, String reg_id, String use_flag, String passenger_num,
                 String wait_time, String insert_time, String insert_date, String remain_time, int comment_cnt) {

        this.board_no = board_no;
        this.write_name = write_name;
        this.passwd = passwd;
        this.department = department;
        this.student_no = student_no;
        this.departure = departure;
        this.departure_detail = departure_detail;
        this.destination = destination;
        this.destination_detail = destination_detail;
        this.reg_id = reg_id;
        if (use_flag.equals("Y")) {
            this.use_flag = true;
        }
        this.use_flag = false;
        this.passenger_num = passenger_num;
        this.wait_time = wait_time;
        this.insert_time = insert_time;
        this.insert_date = insert_date;
        this.remain_time = remain_time;
        this.comment_cnt = comment_cnt;
    }

    public Board(String board_no, String write_name, String passwd, String department,
                 String student_no, String departure, String departure_detail, String destination,
                 String destination_detail, String reg_id, String use_flag, String passenger_num,
                 String wait_time, String insert_time, String insert_date, String remain_time, int comment_cnt, ArrayList<BoardComment> commentList) {

        this.board_no = board_no;
        this.write_name = write_name;
        this.passwd = passwd;
        this.department = department;
        this.student_no = student_no;
        this.departure = departure;
        this.departure_detail = departure_detail;
        this.destination = destination;
        this.destination_detail = destination_detail;
        this.reg_id = reg_id;
        if (use_flag.equals("Y")) {
            this.use_flag = true;
        }
        this.use_flag = false;
        this.passenger_num = passenger_num;
        this.wait_time = wait_time;
        this.insert_time = insert_time;
        this.insert_date = insert_date;
        this.comment_cnt = comment_cnt;
        this.remain_time = remain_time;
        this.commentsList = commentList;
    }

    public Board(){

    }


    /**
     * 게시글 Detailpage 에서 새로고침시 사용
     * 게시글 새로고침하기
     *
     * @param newPost
     */
    public void refreshPost(Board newPost) {
        this.board_no = newPost.board_no;
        this.write_name = newPost.write_name;
        this.passwd = newPost.passwd;
        this.department = newPost.department;
        this.student_no = newPost.student_no;
        this.departure = newPost.departure;
        this.departure_detail = newPost.departure_detail;
        this.destination = newPost.destination;
        this.destination_detail = newPost.destination_detail;
//        this.reg_id = newPost.reg_id;
        if (newPost.use_flag.equals("Y")) {
            this.use_flag = true;
        }
        this.use_flag = false;
        this.passenger_num = newPost.passenger_num;
        this.wait_time = newPost.wait_time;
        this.insert_time = newPost.insert_time;
        this.insert_date = newPost.insert_date;
        this.comment_cnt = comment_cnt;
        this.remain_time = remain_time;
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }

    public String getBoard_no() {
        return board_no;
    }

    public void setBoard_no(String board_no) {
        this.board_no = board_no;
    }

    public String getWrite_name() {
        return write_name;
    }

    public void setWrite_name(String write_name) {
        this.write_name = write_name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDeparture_detail() {
        return departure_detail;
    }

    public void setDeparture_detail(String departure_detail) {
        this.departure_detail = departure_detail;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination_detail() {
        return destination_detail;
    }

    public void setDestination_detail(String destination_detail) {
        this.destination_detail = destination_detail;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public Boolean getUse_flag() {
        return use_flag;
    }

    public void setUse_flag(Boolean use_flag) {
        this.use_flag = use_flag;
    }

    public String getPassenger_num() {
        return passenger_num;
    }

    public void setPassenger_num(String passenger_num) {
        this.passenger_num = passenger_num;
    }

    public String getWait_time() {
        return wait_time;
    }

    public void setWait_time(String wait_time) {
        this.wait_time = wait_time;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getRemain_time() {
        return remain_time;
    }

    public void setRemain_time(String remain_time) {
        this.remain_time = remain_time;
    }

    public ArrayList<BoardComment> getCommentsList() {
        return commentsList;
    }

    private void setCommentsList(ArrayList<BoardComment> commentsList) {
        this.commentsList = commentsList;
    }
}
