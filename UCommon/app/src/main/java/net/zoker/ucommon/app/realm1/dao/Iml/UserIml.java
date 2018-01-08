package net.zoker.ucommon.app.realm1.dao.Iml;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import net.zoker.ucommon.app.realm1.dao.Iml.local.LocalModelIml;
import net.zoker.ucommon.app.realm1.dao.Iml.network.NetModelIml;
import net.zoker.ucommon.app.realm1.dao.local.database.DatabaseManager;
import net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity;
import net.zoker.ucommon.app.realm1.dao.model.IUserModel;

import java.util.List;

/**
 * Created by zoker on 2018/1/2.
 */

public class UserIml extends MoelIml implements IUserModel {
    private LoginSync loginSync;

    public UserIml(Context context) {
        super(new LocalModelIml(), new NetModelIml());
    }

    @Override
    public void login(final String username, final String password, final net.zoker.ucommon.app.realm1.Callback<net.zoker.ucommon.app.realm1.dao.bean.User> callback) {
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
    public void getLoginInfoCache(final Context context, final net.zoker.ucommon.app.realm1.Callback<List<net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity>> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<LoginInfoEntity> loginInfoEntities = DatabaseManager.getInstance().getLoginInfoDao().getLoginInfo();
                for (int index = 0; index < loginInfoEntities.size(); index++) {
                    Log.d("UserIml", "loginInfo = " + loginInfoEntities.get(index).getUsername());
                    Log.d("UserIml", "password = " + loginInfoEntities.get(index).getPassword());

                }
                callback.onSuccess(loginInfoEntities);
            }
        }).start();
    }

    @Override
    public void saveLoginInfoCache(final Context context, final net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity loginInfoEntity, final net.zoker.ucommon.app.realm1.Callback<Boolean> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                net.zoker.ucommon.app.realm1.dao.local.database.DatabaseManager.getInstance().getLoginInfoDao().addLoginInfo(loginInfoEntity);
                callback.onSuccess(true);
            }
        }).start();
    }


    private static class LoginSync extends AsyncTask<String, Integer, net.zoker.ucommon.app.realm1.dao.bean.User> {
        private net.zoker.ucommon.app.realm1.Callback<net.zoker.ucommon.app.realm1.dao.bean.User> callback;
        private String username;
        private String password;

        public LoginSync(String username, String password, net.zoker.ucommon.app.realm1.Callback<net.zoker.ucommon.app.realm1.dao.bean.User> callback) {
            this.callback = callback;
            this.username = username;
            this.password = password;
        }

        @Override
        protected net.zoker.ucommon.app.realm1.dao.bean.User doInBackground(String... strings) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return (username.equals("zoker") && password.equals("123")) ? new net.zoker.ucommon.app.realm1.dao.bean.User() : null;
        }

        @Override
        protected void onPostExecute(net.zoker.ucommon.app.realm1.dao.bean.User user) {
            super.onPostExecute(user);
            if (user != null && callback != null) {
                callback.onSuccess(new net.zoker.ucommon.app.realm1.dao.bean.User());
            } else {
                callback.onFail(new Exception("密码不正确"));
            }
        }
    }
}
