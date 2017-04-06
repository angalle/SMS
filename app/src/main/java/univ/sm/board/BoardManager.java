package univ.sm.board;

import java.util.ArrayList;
import java.util.List;

/**
 * Board 데이터 객체 관리용
 * 서버통신 및 데이터 파싱..등등
 * Created by SooJeong on 2017-03-24.
 */

public class BoardManager {
    List<Board> boardList = new ArrayList<>();

    public BoardManager(String Data) {

        //TODO data load  -> Board 객체 생성 및 List 추가
        ArrayList<Board> boardList = new ArrayList<>();
        this.boardList = boardList;
    }

    public void add(){
/*//POST PARAMETER
WRITE_NAME			//작성자
PASSWD				//글 비밀번호
DEPARTMENT			//학과
STUDENT_NO			//학번
DEPARTURE			//출발지
DEPARTURE_DETAIL	//출발지 설명
DESTINATION			//목적지
DESTINATION_DETAIL	//목적지 설명
REG_ID				//gcm 기기 값
PASSENGER_NUM		//총 탑승인원
WAIT_TIME			//대기시간*/
    }
}
