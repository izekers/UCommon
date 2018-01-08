package net.zoker.ucommon.app;

import android.app.Application;

import net.zoker.ucommon.app.realm1.dao.local.database.DatabaseManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by zoker on 2018/1/4.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseManager.init(this);
    }
}
