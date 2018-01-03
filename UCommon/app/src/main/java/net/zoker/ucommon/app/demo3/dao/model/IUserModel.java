package net.zoker.ucommon.app.demo3.dao.model;


import net.zoker.ucommon.app.demo3.Callback;
import net.zoker.ucommon.app.demo3.dao.bean.User;

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
    void login(String username, String password, Callback<User> callback);
}
