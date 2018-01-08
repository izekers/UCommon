package net.zoker.ucommon.app.realm1.dao.model;


import android.content.Context;

import java.util.List;

/**
 * Created by zoker on 2018/1/2.
 */

public interface IUserModel extends IModel{
    /**
     * 登录
     *
     * @param username
     * @param password
     * @param callback
     */
    void login(String username, String password, net.zoker.ucommon.app.realm1.Callback<net.zoker.ucommon.app.realm1.dao.bean.User> callback);

    void getLoginInfoCache(Context context, net.zoker.ucommon.app.realm1.Callback<List<net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity>> callback);

    void saveLoginInfoCache(Context context, net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity loginInfoEntity, net.zoker.ucommon.app.realm1.Callback<Boolean> callback);
}
