package net.zoker.ucommon.app.room1.dao.local.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

/**
 * Created by zoker on 2018/1/3.
 */
@Entity(tableName = "loginInfo")    //声明数据库表单的名字  tableName不写默认表名为类名
public class LoginInfoEntity {
    @PrimaryKey(autoGenerate = true)    //PrimaryKey : 主键  autoGenerate :自增长
    private int _id;

    @ColumnInfo(name = "username")  //@ColumnInfo ：命名
    private String username;
    private String password;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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
