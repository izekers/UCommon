package net.zoker.ucommon.app.demo1.contract;

/**
 * Created by zoker on 2018/1/2.
 */

public class LoginContract {
    public interface view {
        /**
         * 从UI中获取用户输入的用户名
         *
         * @return
         */
        String getUsername();

        /**
         * 从UI中获取用户输入的密码
         *
         * @return
         */
        String getPassword();

        /**
         * 显示加loading
         *
         * @param msg
         */
        void showLoading(String msg);

        /**
         * 取消loading显示
         */
        void hideLoading();

        /**
         * 显示结果
         *
         * @param result
         */
        void showResult(String result);
    }

    public interface Presenter {
        /**
         * 登录
         */
        void login(String username,String password);
    }
}
