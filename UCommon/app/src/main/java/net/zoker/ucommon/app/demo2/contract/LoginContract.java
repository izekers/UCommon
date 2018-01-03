package net.zoker.ucommon.app.demo2.contract;

/**
 * Created by zoker on 2018/1/2.
 */

public class LoginContract extends BaseContract {
    public interface view extends BaseContract.MvpView {
    }

    public interface Presenter{
        /**
         * 登录
         */
        void login(String username, String password);
    }
}
