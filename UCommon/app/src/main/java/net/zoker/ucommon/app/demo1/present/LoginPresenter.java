package net.zoker.ucommon.app.demo1.present;

import net.zoker.ucommon.app.demo1.Callback;
import net.zoker.ucommon.app.demo1.contract.LoginContract;
import net.zoker.ucommon.app.demo1.dao.bean.User;
import net.zoker.ucommon.app.demo1.dao.model.IUserModel;

/**
 * Created by zoker on 2018/1/2.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.view loginView;
    private IUserModel userModel;

    public LoginPresenter(LoginContract.view loginView, IUserModel userModel) {
        this.loginView = loginView;
        this.userModel = userModel;
    }

    @Override
    public void login(String username, String password) {

        loginView.showLoading("登录中");
        userModel.login(username, password, new Callback<User>() {
            @Override
            public void onSuccess(User t) {
                loginView.hideLoading();
                loginView.showResult("登录成功");
            }

            @Override
            public void onFail(Exception exception) {
                loginView.hideLoading();
                loginView.showResult(exception.getMessage());
            }
        });
    }
}
