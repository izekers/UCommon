package net.zoker.ucommon.app.demo1.dao.model;

import zoker.net.mvp.base.Callback;

/**
 * Created by zoker on 2018/1/2.
 */

public interface IUserModel {
    /**
     * 登录
     * @param username
     * @param password
     * @param callback
     */
    void login(String username, String password, Callback callback);
}
