package net.zoker.ucommon.app.demo1.dao.Iml;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import net.zoker.ucommon.app.demo1.Callback;
import net.zoker.ucommon.app.demo1.dao.bean.User;
import net.zoker.ucommon.app.demo1.dao.model.IUserModel;

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
            if (loginSync!=null && loginSync.isCancelled())
                loginSync.isCancelled();
            loginSync = new LoginSync(username,password,callback);
            loginSync.execute("");
        }
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
