package univ.sm.View.board.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import univ.sm.Controller.CommonCallbak;
import univ.sm.Controller.api.board.BoardService;
import univ.sm.Model.Const;
import univ.sm.Model.User;
import univ.sm.R;
import univ.sm.View.CommonView;
import univ.sm.View.board.BoardView;

/**
 * Created by PE_LHS on 2018-01-24.
 */

public class IndirectLoginView extends CommonView {
    private SessionCallback callback;

    private CallbackManager callbackManager;

    @BindView(R.id.user_email)TextView user_email;
    @BindView(R.id.user_pw) TextView user_pw;
/*    @BindView(R.id.user_login) Button user_login;
    @BindView(R.id.com_kakao_login) Button com_kakao_login;*/
    @BindView(R.id.facebook_login)LoginButton facebook_login;
    SharedPreferences sp;

    /** 전역변수들, 카카오나 페이스북을 로그인 한 뒤에 정보를 받기위한 용도.*/
    String email = "";
    String name = "";

    /** 페이스북 나중에 개발 */
    /** 페이스북 나중에 개발 */
    /** 페이스북 나중에 개발 */
    /** 페이스북 나중에 개발 */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(this.getApplicationContext());

        sp = getSharedPreferences(Const.SHARED_USER, MODE_PRIVATE);
        Log.e("email::",sp.getString(Const.SHARED_MEMBER_EMAIL,""));
        if(!"".equals(sp.getString(Const.SHARED_MEMBER_EMAIL,""))){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("MEMBER_EMAIL",sp.getString(Const.SHARED_MEMBER_EMAIL,""));
            BoardService.getInstance(getApplicationContext()).createApi().checkEmail(map,callbak);
        }else{
            setContentView(R.layout.indirect_login);
            ButterKnife.bind(this);

            callback = new SessionCallback();
            Session.getCurrentSession().addCallback(callback);
            //facebook();
        }
    }

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            Log.e("onSessionOpened","onSessionOpened");
            redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }

    /** 카카오톡 콜백 function */
    protected void redirectSignupActivity() {
        List<String> propertyKeys = new ArrayList<String>();
        propertyKeys.add("kaccount_email");
        propertyKeys.add("nickname");
        propertyKeys.add("profile_image");
        propertyKeys.add("thumbnail_image");
        Log.e("678678678","UserProfile : test");
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Toast.makeText(getApplicationContext(),"카카오톡 인증에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                Log.e("error","msg::::"+errorResult);

            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Toast.makeText(getApplicationContext(),"세션이 종료되었습니다. 다시 로그인 해 주세요.",Toast.LENGTH_SHORT).show();
                Log.e("error","msg::::"+errorResult);
            }

            @Override
            public void onSuccess(UserProfile userProfile) {

                email = userProfile.getEmail();
                name = userProfile.getNickname();
                HashMap<String,Object> map = new HashMap<String,Object>();
                map.put("email",email);


                /** 이메일 체크*/
                BoardService.getInstance(getApplicationContext()).createApi().checkEmail(map,callbak);

            }

            @Override
            public void onNotSignedUp() {
                Toast.makeText(getApplicationContext(),"로그인되어있지 않습니다. 다시 로그인 해 주세요.",Toast.LENGTH_SHORT).show();
            }
        }, propertyKeys, false);
    }


    /** 카카오톡 이메일 체크하고난 뒤 로직 */
    CommonCallbak callbak = new CommonCallbak(){
        @Override
        public void onError(Throwable t) {

        }
        @Override
        public void onSuccess(int code, Object receiveData) {
            Log.e("code",code+"");
            Log.e("data",receiveData.toString());
            Gson gson = new Gson();
            if(code == 200){
                JsonObject jObject = gson.fromJson(receiveData.toString(),JsonObject.class);
                String result = jObject.get("Result").getAsString();
                if("false".equals(result)){
                    /** 없는 로직 수행*/
                    /** 직접 가입하는 페이지로 이동.*/


                    nextPage(
                            new Intent(IndirectLoginView.this,DirectLoginView.class)
                                    .putExtra("email",email)
                                    .putExtra("name",name)
                    );
                }else{
                    /** 있는 로직 수행*/
                    /** 게시판 목록으로 이동*/

                    JsonArray array = jObject.get("data").getAsJsonArray();
                    JsonObject jsonObject = array.get(0).getAsJsonObject();
                    User user = gson.fromJson(jsonObject, User.class);

                    SharedPreferences sp = getSharedPreferences(Const.SHARED_USER, MODE_PRIVATE);
                    SharedPreferences.Editor spe = sp.edit();
                    spe.putString(Const.SHARED_MEMBER_EMAIL,user.MEMBER_EMAIL);
                    spe.commit();

                    /** 게시판 목록으로 이동*/
                    Toast.makeText(getApplicationContext(),"로그인 되었습니다.",Toast.LENGTH_SHORT).show();
                    nextPage(
                            new Intent(IndirectLoginView.this,BoardView.class)
                                    .putExtra("user",user)
                    );
                }
            }
        }
        @Override
        public void onFailure(int code) {

        }
    };

    ProfileTracker profileTracker;

    @OnClick(R.id.facebook_login)
    public void facebook() {
        Log.e("facebook","facebook");

        facebook_login.setReadPermissions("email");

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.e("토큰",loginResult.getAccessToken().getToken());
                        Log.e("토큰",loginResult.getRecentlyGrantedPermissions().toString());
                        Log.e("유저아이디",loginResult.getAccessToken().getUserId());
                        Log.e("전체내용",loginResult.getAccessToken().toString());
                        Log.e("퍼미션 리스트",loginResult.getAccessToken().getPermissions()+"");

                        GraphRequest request =GraphRequest.newMeRequest(loginResult.getAccessToken() ,
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        try {
                                            Log.e("user profile",object.toString());
                                            /*Log.e("user profile",object.getString("name"));
                                            Log.e("user profile",object.getString("gender"));*/
                                            Log.e("response ",response.toString());
                                            Log.e("getJSONObject ",response.getJSONObject().toString());

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        request.executeAsync();

                        /*email = userProfile.getEmail();
                        name = userProfile.getNickname();
                        HashMap<String,Object> map = new HashMap<String,Object>();
                        map.put("email",email);


                        *//** 이메일 체크*//*
                        BoardService.getInstance(getApplicationContext()).createApi().checkEmail(map,callbak);*/
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Log.e("onCancel","onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.e("onCancel","onCancel"+exception);
                    }
                });
    }


    @OnClick(R.id.user_login)
    public void loginUser(){
        String email = user_email.getText().toString();
        String pw = user_pw.getText().toString();

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("MEMBER_EMAIL",email);
        map.put("MEMBER_PW",pw);

        BoardService.getInstance(getApplicationContext()).createApi().loginUser(map,new CommonCallbak(){
            @Override
            public void onError(Throwable t) {
                Toast.makeText(getApplicationContext(),Const.MSG_ERROR,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int code, Object receiveData) {
                Log.e("code",code+"");
                Log.e("data",receiveData.toString());
                Gson gson = new Gson();
                if(code == 200){
                    JsonObject jObject = gson.fromJson(receiveData.toString(),JsonObject.class);
                    String result = jObject.get("Result").getAsString();
                    if("false".equals(result)){
                        /** 없는 로직 수행*/
                        Toast.makeText(getApplicationContext(),"이메일과 비밀번호를 확인해 주세요.",Toast.LENGTH_SHORT).show();
                    }else{
                        /** 있는 로직 수행*/
                        JsonArray array = jObject.get("data").getAsJsonArray();
                        JsonObject jsonObject = array.get(0).getAsJsonObject();
                        User user = gson.fromJson(jsonObject, User.class);

                        SharedPreferences.Editor spe = sp.edit();
                        spe.putString(Const.SHARED_MEMBER_EMAIL,user.MEMBER_EMAIL);
                        spe.commit();

                        /** 게시판 목록으로 이동*/
                        Toast.makeText(getApplicationContext(),"로그인 되었습니다.",Toast.LENGTH_SHORT).show();
                        nextPage(
                                new Intent(IndirectLoginView.this,BoardView.class)
                                        .putExtra("user",user)
                        );
                    }
                }
            }

            @Override
            public void onFailure(int code) {
                Toast.makeText(getApplicationContext(),Const.MSG_FAIL+code,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.com_kakao_login)
    public void clickKakao(){
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    private void nextPage(Intent intentInfo){
        /** 추가 정보를 받아야 함. 새로운 뷰를 제공해야함.*/
        Intent intent = intentInfo;
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }
}
