package univ.sm.board;

import android.nfc.Tag;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import univ.sm.data.Comment;

/**
 * Post 데이터 객체 관리용
 * 서버에서 받아온 데이터 파싱..등등
 * Created by SooJeong on 2017-03-24.
 */

public class BoardManager {

    private static final String TAG = "BoardManager";
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
     * post 바꿔치기
     *
     * @param i       바꿀 Post 의 인덱스
     * @param newPost 새로운 내용의 Post
     */
    public static void refreshPost(int i, Post newPost) {
        Post post = postArrayList.get(i);
        post.refreshPost(newPost);
    }

    /**
     * post 바꿔치기
     *
     * @param i        바꿀 Post 의 인덱스
     * @param jsonPost 새로운 내용의 JSONObject
     */
    public static void refreshPost(int i, JSONObject jsonPost) {
        refreshPost(i, json2Post(jsonPost));
    }

    /**
     * JSONObject-> Post 객체로 바꾸기
     *
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
                    jsonPost.getString("WAIT_TIME"), jsonPost.getString("INSERT_TIME"), jsonPost.getString("INSERT_DATE"), jsonPost.getString("REMAIN_TIME"));



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return post;
    }

    /***
     * comment 가 포함되어 있는 post 객체를 생성한다.
     *
     * @param jsonPost
     * @return
     */
    public static Post json2PostWithComment(JSONObject jsonPost) {
        Post post = null;

        try {
            JSONArray postsJsonArray = jsonPost.getJSONArray("CALLVAN_INFO");
            JSONObject postJson = postsJsonArray.getJSONObject(0);
            Log.i(TAG, "postJson : " +postJson);
            JSONArray commentsArrayJson = jsonPost.getJSONArray("COMMENTS");

            ArrayList<Comment> commentsList = new ArrayList<>();

            for(int i = 0 ; i < commentsArrayJson.length(); i++){
                JSONObject commentsjson= commentsArrayJson.getJSONObject(i);

                Comment comment = new Comment(commentsjson.getString("COMMENT_NO"), commentsjson.getString("CALL_BOARD_NO"),
                        commentsjson.getString("COMMENT_LEVEL"), commentsjson.getString("CONTENTS"),
                        commentsjson.getString("REG_ID"), commentsjson.getString("WRITE_NAME"),
                        commentsjson.getString("DEPARTMENT"), commentsjson.getString("SHARING_FLAG"),
                        commentsjson.getString("BEFORE_COMMENT_NO"), commentsjson.getString("INSERT_TIME"),
                        commentsjson.getString("INSERT_DATE"));
                commentsList.add(comment);

            }


            post = new Post(postJson.getString("CALL_BOARD_NO"), postJson.getString("WRITE_NAME"),
                    postJson.getString("PASSWD"), postJson.getString("DEPARTMENT"),
                    postJson.getString("STUDENT_NO"), /*postJson.getString("DEPARTURE")*/ null,
                    postJson.getString("DEPARTURE_DETAIL"), postJson.getString("DESTINATION"),
                    postJson.getString("DESTINATION_DETAIL"), postJson.getString("REG_ID"),
                    postJson.getString("USE_FLAG"), postJson.getString("PASSENGER_NUM"),
                    postJson.getString("WAIT_TIME"), postJson.getString("INSERT_TIME"),
                    postJson.getString("INSERT_DATE"),/* postJson.getString("REMAIN_TIME")*/ null ,commentsList);

            Log.i(TAG,"json2PostWithComment : post.boardno = " + post.getBoard_no() + ", commentList.size = " + commentsList.size());
            //댓글 리스트 추가해서 Post 만들기

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return post;
    }
}