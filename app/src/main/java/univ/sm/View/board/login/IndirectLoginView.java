package univ.sm.View.board.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import univ.sm.Controller.CommonCallbak;
import univ.sm.Controller.api.board.BoardService;
import univ.sm.R;
import univ.sm.View.CommonView;
import univ.sm.View.board.list.BoardList_FView;

/**
 * Created by PE_LHS on 2018-01-24.
 */

public class IndirectLoginView extends CommonView {
    private SessionCallback callback;

    private CallbackManager callbackManager;

    @BindView(R.id.user_email)TextView user_email;
    @BindView(R.id.user_pw) TextView user_pw;
    @BindView(R.id.user_login) Button user_login;

    /** 전역변수들, 카카오나 페이스북을 로그인 한 뒤에 정보를 받기위한 용도.*/
    String email = "";
    String name = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.indirect_login);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
        Log.e("start","start");

        facebook();
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
                /** email check logic*/

                HashMap<String,Object> map = new HashMap<String,Object>();
                map.put("email",email);

                BoardService.getInstance(getApplicationContext()).createApi().checkEmail(map,callbak);

            }

            @Override
            public void onNotSignedUp() {
                Toast.makeText(getApplicationContext(),"로그인되어있지 않습니다. 다시 로그인 해 주세요.",Toast.LENGTH_SHORT).show();
            }
        }, propertyKeys, false);
    }
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
                    Toast.makeText(getApplicationContext(),"같은 아이디가 이미 존재합니다.",Toast.LENGTH_SHORT).show();
                }else{
                    /** 있는 로직 수행*/
                    /** 추가 정보를 받아야 함. 새로운 뷰를 제공해야함.*/
                    nextPage(
                            new Intent(IndirectLoginView.this,DirectLoginView.class)
                                    .putExtra("email",email)
                                    .putExtra("name",name)
                    );
                    //Toast.makeText(getApplicationContext(),"성공적으로 가입되었습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(int code) {

        }
    };






    private void facebook() {
        LoginButton loginButton = (LoginButton) findViewById(R.id.facebook_login);
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("result",object.toString());
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr",error.toString());
            }
        });
    }


    @OnClick(R.id.user_login)
    private void loginUser(){
        String email = user_email.getText().toString();
        String pw = user_pw.getText().toString();

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("email",email);
        map.put("pw",pw);

        BoardService.getInstance(getApplicationContext()).createApi().checkEmail(map,new CommonCallbak(){
            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onSuccess(int code, Object receiveData) {

            }

            @Override
            public void onFailure(int code) {

            }
        });

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
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }
}
