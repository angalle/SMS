package univ.sm.Controller;

/**
 * Created by heesun on 2017-12-06.
 */

public interface CommonCallbak<T> {
    void onError(Throwable t);
    void onSuccess(int code,T receiveData);
    void onFailure(int code);
}
