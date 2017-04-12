package univ.sm.board;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Board 데이터 객체 관리용
 * 서버통신 및 데이터 파싱..등등
 * Created by SooJeong on 2017-03-24.
 */

public class BoardManager {

    private static ArrayList<Board> boardArrayList;

    public BoardManager(JSONObject data) {

        //Board 객체 생성 및 List 추가
        ArrayList<Board> boardList = new ArrayList<>();

        try {
            JSONObject jsonObject = data;
            JSONArray callvanArray = jsonObject.getJSONArray("callvan");
            Log.e("권수정", "callvanArray : " + callvanArray);
            for (int i = 0; i < callvanArray.length(); i++) {
                JSONObject jsonBaord = callvanArray.getJSONObject(i);
                Log.e("권수정", "jsonBaord : " + jsonBaord);
                Log.e("권수정", "jsonBaord.getString(\"CALL_BOARD_NO\") : " + jsonBaord.getString("CALL_BOARD_NO"));

                Board board = new Board(jsonBaord.getString("CALL_BOARD_NO"), jsonBaord.getString("WRITE_NAME"),
                        jsonBaord.getString("PASSWD"), jsonBaord.getString("DEPARTMENT"),
                        jsonBaord.getString("STUDENT_NO"), jsonBaord.getString("DEPARTURE"),
                        jsonBaord.getString("DEPARTURE_DETAIL"), jsonBaord.getString("DESTINATION"),
                        jsonBaord.getString("DESTINATION_DETAIL"), jsonBaord.getString("REG_ID"),
                        jsonBaord.getString("USE_FLAG"), jsonBaord.getString("PASSENGER_NUM"),
                        jsonBaord.getString("WAIT_TIME"), jsonBaord.getString("INSERT_TIME"), jsonBaord.getString("INSERT_DATE"));

                boardList.add(board);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.boardArrayList = boardList;
    }

    public static ArrayList<Board> getBoardArrayList() {
        if (boardArrayList == null) {
            Log.e("board", "boardArrayList is null");
        }
        return boardArrayList;
    }

    public void add() {
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
