package net.zoker.ucommon.app.room1.present;

import android.content.Context;

import net.zoker.ucommon.app.room1.Callback;
import net.zoker.ucommon.app.room1.contract.LoginContract;
import net.zoker.ucommon.app.room1.dao.bean.User;
import net.zoker.ucommon.app.room1.dao.local.database.DatabaseManager;
import net.zoker.ucommon.app.room1.dao.local.database.entities.LoginInfoEntity;
import net.zoker.ucommon.app.room1.dao.model.IUserModel;

import java.util.List;

/**
 * Created by zoker on 2018/1/2.
 */

public class LoginPresenter extends LoginContract.BasePresenter<LoginContract.view> implements LoginContract.Presenter {
    private IUserModel userModel;

    public LoginPresenter(IUserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public void login(String username, String password) {
        checkViewAttached();
        getMvpView().showLoading("登录中");
        LoginInfoEntity loginInfoEntity = new LoginInfoEntity();
        loginInfoEntity.setUsername(username);
        loginInfoEntity.setPassword(password);
        userModel.saveLoginInfoCache(getMvpView().getContext(), loginInfoEntity, new Callback<Boolean>() {
            @Override
            public void onSuccess(Boolean t) {
                loadLoginInfoCache();
            }

            @Override
            public void onFail(Exception exception) {

            }
        });
        userModel.login(username, password, new Callback<User>() {
            @Override
            public void onSuccess(User t) {
                getMvpView().hideLoading();
                getMvpView().loginSuccess();
                getMvpView().showMsg("登录成功");
            }

            @Override
            public void onFail(Exception exception) {
                getMvpView().hideLoading();
                getMvpView().loginFail();
                getMvpView().showError(exception.getMessage());
            }
        });
    }

    @Override
    public void loadLoginInfoCache() {
        userModel.getLoginInfoCache(getMvpView().getContext(), new Callback<List<LoginInfoEntity>>() {
            @Override
            public void onSuccess(List<LoginInfoEntity> loginInfoEntities) {
                getMvpView().setupUserNameTip(loginInfoEntities);
            }

            @Override
            public void onFail(Exception exception) {

            }
        });
    }
}
