package net.zoker.ucommon.app.realm1.dao.local.database.entities;

import android.graphics.Bitmap;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zoker on 2018/1/3.
 */
public class LoginInfoEntity extends RealmObject{
    @PrimaryKey    //PrimaryKey : 主键
    private String _id;

    private String username;
    private String password;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Ignore
    private Bitmap picture;

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
