package univ.sm.View.board.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import univ.sm.R;
import univ.sm.View.CommonView;

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
