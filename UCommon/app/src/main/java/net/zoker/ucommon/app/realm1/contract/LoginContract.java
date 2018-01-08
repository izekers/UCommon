package net.zoker.ucommon.app.realm1.contract;

import android.content.Context;


import java.util.List;

/**
 * Created by zoker on 2018/1/2.
 */

public class LoginContract extends net.zoker.ucommon.app.realm1.contract.BaseContract {
    public interface view extends MvpView {
        void setupUserNameTip(List<net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity> loginInfoEntities);

        Context getContext();

        void loginSuccess();
        void loginFail();
    }

    public interface Presenter{
        /**
         * 登录
         */
        void login(String username, String password);

        void loadLoginInfoCache();
    }
}
