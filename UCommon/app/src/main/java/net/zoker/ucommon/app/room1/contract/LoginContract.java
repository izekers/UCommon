package net.zoker.ucommon.app.room1.contract;

import android.content.Context;

import net.zoker.ucommon.app.room1.dao.local.database.entities.LoginInfoEntity;

import java.util.List;

/**
 * Created by zoker on 2018/1/2.
 */

public class LoginContract extends BaseContract {
    public interface view extends MvpView {
        void setupUserNameTip(List<LoginInfoEntity> loginInfoEntities);

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
