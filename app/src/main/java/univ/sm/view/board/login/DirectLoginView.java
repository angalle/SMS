package univ.sm.view.board.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import univ.sm.R;
import univ.sm.view.CommonView;

/**
 * Created by PE_LHS on 2018-01-24.
 */

public class DirectLoginView extends CommonView implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direct_login);

    }

    @Override
    public void onClick(View view) {

    }
}
