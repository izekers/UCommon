package net.zoker.ucommon.app.demo2;

/**
 * Created by zoker on 2018/1/2.
 */

public interface Callback<Result> {
    void onSuccess(Result t);
    void onFail(Exception exception);
}