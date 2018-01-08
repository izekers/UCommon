package net.zoker.ucommon.app.realm1.dao.local.database.entities;

import android.graphics.Bitmap;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by zoker on 2018/1/4.
 */

public class User extends RealmObject{
    /**
     * 支持以下类型：
     *      boolean, byte, short, int, long, float, double, String, Date and byte[]
     *      在Realm中byte, short, int, long最终都被映射成long类型
     */
    @PrimaryKey
    private String id;

    private RealmList<LoginInfoEntity> loginInfos; //一对多的关系，两个类都要继承RealmObject

    @Ignore
    private Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<LoginInfoEntity> getLoginInfos() {
        return loginInfos;
    }

    public void setLoginInfos(RealmList<LoginInfoEntity> loginInfos) {
        this.loginInfos = loginInfos;
    }
}
