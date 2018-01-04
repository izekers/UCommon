package net.zoker.ucommon.app.room1.dao.Iml;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import net.zoker.ucommon.app.room1.Callback;
import net.zoker.ucommon.app.room1.dao.bean.User;
import net.zoker.ucommon.app.room1.dao.local.database.DatabaseManager;
import net.zoker.ucommon.app.room1.dao.local.database.entities.LoginInfoEntity;
import net.zoker.ucommon.app.room1.dao.model.IUserModel;

import java.util.List;

/**
 * Created by zoker on 2018/1/2.
 */

public class UserIml implements IUserModel {
    private LoginSync loginSync;

    public UserIml() {

    }

    @Override
    public void login(final String username, final String password, final Callback<User> callback) {
        if (TextUtils.isEmpty(username) && callback != null)
            callback.onFail(new Exception("用户名不能为空"));
        else if (TextUtils.isEmpty(password) && callback != null)
            callback.onFail(new Exception("密码不能为空"));
        else {
            if (loginSync != null && loginSync.isCancelled())
                loginSync.isCancelled();
            loginSync = new LoginSync(username, password, callback);
            loginSync.execute("");
        }
    }

    @Override
    public void getLoginInfoCache(final Context context, final Callback<List<LoginInfoEntity>> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(DatabaseManager.with(context).getLoginInfoDao().getLoginInfo());
            }
        }).start();
    }

    @Override
    public void saveLoginInfoCache(final Context context, final LoginInfoEntity loginInfoEntity, final Callback<Boolean> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseManager.with(context).getLoginInfoDao().addLoginInfo(loginInfoEntity);
                callback.onSuccess(true);
            }
        }).start();
    }


    private static class LoginSync extends AsyncTask<String, Integer, User> {
        private Callback<User> callback;
        private String username;
        private String password;

        public LoginSync(String username, String password, Callback<User> callback) {
            this.callback = callback;
            this.username = username;
            this.password = password;
        }

        @Override
        protected User doInBackground(String... strings) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return (username.equals("zoker") && password.equals("123")) ? new User() : null;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            if (user != null && callback != null) {
                callback.onSuccess(new User());
            } else {
                callback.onFail(new Exception("密码不正确"));
            }
        }
    }
}
