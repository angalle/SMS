package univ.sm;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import butterknife.ButterKnife;

public class AppInfo extends Dialog{

    public AppInfo(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.app_info);
        ButterKnife.bind(this);

    }
}
