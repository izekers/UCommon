package net.zoker.ucommon.app.room1.dao.model;


import android.content.Context;

import net.zoker.ucommon.app.room1.Callback;
import net.zoker.ucommon.app.room1.dao.bean.User;
import net.zoker.ucommon.app.room1.dao.local.database.entities.LoginInfoEntity;

import java.util.List;

/**
 * Created by zoker on 2018/1/2.
 */

public interface IUserModel {
    /**
     * 登录
     *
     * @param username
     * @param password
     * @param callback
     */
    void login(String username, String password, Callback<User> callback);

    void getLoginInfoCache(Context context, Callback<List<LoginInfoEntity>> callback);

    void saveLoginInfoCache(Context context, LoginInfoEntity loginInfoEntity, Callback<Boolean> callback);
}
