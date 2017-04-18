package univ.sm.board;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Post 데이터 객체 관리용
 * 서버에서 받아온 데이터 파싱..등등
 * Created by SooJeong on 2017-03-24.
 */

public class BoardManager {

    private static ArrayList<Post> postArrayList;

    public BoardManager(JSONObject data) {

        //Post 객체 생성 및 List 추가
        ArrayList<Post> postList = new ArrayList<>();

        try {
            JSONObject jsonObject = data;
            JSONArray callvanArray = jsonObject.getJSONArray("callvan");
            Log.e("권수정", "callvanArray : " + callvanArray);
            for (int i = 0; i < callvanArray.length(); i++) {
                JSONObject jsonBoard = callvanArray.getJSONObject(i);
                postList.add(json2Post(jsonBoard));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.postArrayList = postList;
    }

    public static ArrayList<Post> getPostArrayList() {
        if (postArrayList == null) {
            Log.e("board", "postArrayList is null");
        }
        return postArrayList;
    }

    /**
     *  post 바꿔치기
     * @param i 바꿀 Post 의 인덱스
     * @param newPost 새로운 내용의 Post
     */
    public static void refreshPost(int i, Post newPost) {
        Post post = postArrayList.get(i);
        post.refreshPost(newPost);
    }

    /**
     * post 바꿔치기
     * @param i 바꿀 Post 의 인덱스
     * @param jsonPost 새로운 내용의 JSONObject
     */
    public static void refreshPost(int i, JSONObject jsonPost){
        refreshPost(i,json2Post(jsonPost));
    }

    /**
     * JSONObject-> Post 객체로 바꾸기
     * @param jsonPost JSONObject
     * @return post
     */
    private static Post json2Post(JSONObject jsonPost) {
        Post post = null;
        Log.e("권수정", "jsonBaord : " + jsonPost);
        try {
            post = new Post(jsonPost.getString("CALL_BOARD_NO"), jsonPost.getString("WRITE_NAME"),
                    jsonPost.getString("PASSWD"), jsonPost.getString("DEPARTMENT"),
                    jsonPost.getString("STUDENT_NO"), jsonPost.getString("DEPARTURE"),
                    jsonPost.getString("DEPARTURE_DETAIL"), jsonPost.getString("DESTINATION"),
                    jsonPost.getString("DESTINATION_DETAIL"), jsonPost.getString("REG_ID"),
                    jsonPost.getString("USE_FLAG"), jsonPost.getString("PASSENGER_NUM"),
                    jsonPost.getString("WAIT_TIME"), jsonPost.getString("INSERT_TIME"), jsonPost.getString("INSERT_DATE"));

            //todo 댓글 리스트도 추가해서 Post 만들어야함

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return post;
    }

}