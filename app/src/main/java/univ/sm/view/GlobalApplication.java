package univ.sm.view;

import android.app.Application;

/**
 * Created by PE-LHS on 2018-03-13.
 */

public class GlobalApplication extends Application {

    private static volatile GlobalApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static GlobalApplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit GlobalApplication");
        return instance;
    }

}
