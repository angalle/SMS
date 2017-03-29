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

}
