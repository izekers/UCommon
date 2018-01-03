package net.zoker.ucommon.app.demo3.present;

import net.zoker.ucommon.app.demo3.Callback;
import net.zoker.ucommon.app.demo3.contract.LoginContract;
import net.zoker.ucommon.app.demo3.dao.bean.User;
import net.zoker.ucommon.app.demo3.dao.model.IUserModel;

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
        userModel.login(username, password, new Callback<User>() {
            @Override
            public void onSuccess(User t) {
                getMvpView().hideLoading();
                getMvpView().showMsg("登录成功");
            }

            @Override
            public void onFail(Exception exception) {
                getMvpView().hideLoading();
                getMvpView().showError(exception.getMessage());
            }
        });
    }
}
