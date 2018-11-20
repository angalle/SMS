package univ.sm.View.board.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.common.Common;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import univ.sm.Controller.CommonCallbak;
import univ.sm.Controller.api.board.BoardService;
import univ.sm.Model.Const;
import univ.sm.R;
import univ.sm.Util.CommonUtil;
import univ.sm.View.CommonView;
import univ.sm.View.board.BoardView;

/**
 * Created by PE_LHS on 2018-01-24.
 */

public class DirectLoginView extends CommonView{
    @BindView(R.id.user_email) TextView user_email;
    @BindView(R.id.user_pw) TextView user_pw;
    @BindView(R.id.user_name) TextView user_name;
    @BindView(R.id.univ_depart) TextView univ_depart;
    @BindView(R.id.studentNo_edit) TextView studentNo_edit;
    String INDIRECT_FLAG = "N";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direct_login);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String name = intent.getStringExtra("birthday");

        user_email.setText(email);
        user_name.setText(name);
        /** 이메일 값이 있어서 화면이 넘어오면 간접 로그인.
         * 없으면 직접 로그인, 직접가입과 간접 가입(= 카톡이나 페북으로 로그인하는 것) */
        if(!"".equals(email)){
            INDIRECT_FLAG = "Y";
        }
    }

    @OnClick(R.id.join_btn)
    public void join(){
        try{
            String email  = user_email.getText().toString();
            String pw = user_pw.getText().toString();
            String name = user_name.getText().toString();
            String depart = univ_depart.getText().toString();
            String studentNo = studentNo_edit.getText().toString();

            if(!isEmailValid(email)){
                Toast.makeText(getApplicationContext(),"이메일 형식에 맞게 입력해주세요.",Toast.LENGTH_SHORT).show();
                user_email.requestFocus();
                return;
            }

            if(!validatePassword(pw)){
                Toast.makeText(getApplicationContext(),"비밀번호 4-16자, 소문자,숫자,특수문자를 섞어서 사용해주세요.",Toast.LENGTH_SHORT).show();
                user_pw.requestFocus();
                return;
            }

            if("".equals(name)){Toast.makeText(getApplicationContext(),"이름을 입력해주세요.",Toast.LENGTH_SHORT).show();user_name.requestFocus();}
            if("".equals(depart)){Toast.makeText(getApplicationContext(),"학과를 입력해주세요.",Toast.LENGTH_SHORT).show();univ_depart.requestFocus();}
            if("".equals(studentNo)){Toast.makeText(getApplicationContext(),"학번을 입력해주세요.",Toast.LENGTH_SHORT).show();studentNo_edit.requestFocus();}


            HashMap<String,Object> map = new HashMap<String,Object>();

            map.put("MEMBER_EMAIL",email);
            map.put("MEMBER_PW",pw);
            map.put("MEMBER_NAME",name);
            map.put("MEMBER_DEPATMENET",depart);
            map.put("MEMBET_ST_NO",studentNo);

            map.put("MEMEBER_REG_NO", new CommonUtil().getRegistrationId(this));
            map.put("INDIRECT_FLAG",INDIRECT_FLAG);

            BoardService.getInstance(this).createApi().insertUser(map,callbak);
        }catch(Exception e){
            e.printStackTrace();
            Log.e("error",e.toString());
        }
    }

    @OnClick(R.id.cancel_btn)
    public void clickCancel(){
        CommonUtil.nextPage(new Intent(DirectLoginView.this,IndirectLoginView.class),this);
    }

    private void nextPage(Intent intentInfo){
        /** 추가 정보를 받아야 함. 새로운 뷰를 제공해야함.*/
        Intent intent = intentInfo;
        startActivity(intent);
        finish();
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
                    Toast.makeText(getApplicationContext(),"같은 아이디가 이미 존재합니다.",Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences sp = getSharedPreferences(Const.SHARED_USER, MODE_PRIVATE);
                    SharedPreferences.Editor spe = sp.edit();
                    spe.putString(Const.SHARED_MEMBER_EMAIL,user_email.getText().toString());
                    spe.commit();

                    Toast.makeText(getApplicationContext(),"성공적으로 가입되었습니다.",Toast.LENGTH_SHORT).show();
                    nextPage(new Intent(DirectLoginView.this, BoardView.class));
                }
            }
        }

        @Override
        public void onFailure(int code) {

        }
    };

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    }

    //비밀번호 정규식
    public static final Pattern VALID_PASSWOLD_REGEX_ALPHA_NUM
            = Pattern.compile("^[a-z0-9!@.#$%^&*?_~]{4,16}$"); // 4자리 ~ 16자리까지 가능

    // 비밀번호 검사
    public static boolean validatePassword(String pwStr) {
        Matcher matcher = VALID_PASSWOLD_REGEX_ALPHA_NUM.matcher(pwStr);
        return matcher.matches();
    }
}
