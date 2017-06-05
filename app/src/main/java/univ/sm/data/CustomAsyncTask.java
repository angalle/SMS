package univ.sm.data;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.lang.reflect.Method;
import java.util.Objects;

import univ.sm.board.BoardActivity;
import univ.sm.connect.LoopjConnection;

/**
 * Created by uaer on 2017-06-02.
 */

public class CustomAsyncTask extends AsyncTask<String,Void,Void>{
    Class noparams[] = {};

    ProgressDialog pd = null;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(BoardActivity.context);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("로딩...");
        pd.show();
    }

    @Override
    protected Void doInBackground(String... strings) {
        try{
            LoopjConnection lc = LoopjConnection.getInstance(BoardActivity.context);
            Method method =lc.getClass().getDeclaredMethod(strings[0],noparams);
            method.invoke(LoopjConnection.getInstance(BoardActivity.context),null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        pd.dismiss();
        super.onPostExecute(aVoid);
    }
}
