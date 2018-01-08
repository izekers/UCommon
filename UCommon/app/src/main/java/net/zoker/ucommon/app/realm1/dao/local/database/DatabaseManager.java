package net.zoker.ucommon.app.realm1.dao.local.database;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.content.Context;

import net.zoker.ucommon.app.realm1.dao.local.database.DAO.LoginInfoDao;
import net.zoker.ucommon.app.realm1.dao.local.database.DAO.LoginInfoDaoIml;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by zoker on 2018/1/3.
 */

public class DatabaseManager {
    private static Application APPLICATION;
    private static DatabaseManager INSTANCE;
    private static final int VERSION = 2;
    private static final String DBNAME = "UCOMMON.realm";
    private static byte[] key = null;
    private LoginInfoDao loginInfoDao;

    private DatabaseManager() {
        loginInfoDao = new LoginInfoDaoIml();
    }

    //加密
    private static RealmConfiguration.Builder key(RealmConfiguration.Builder config) {
        return config.encryptionKey(key);
    }

    //版本迁移
    private static RealmConfiguration.Builder versionControl(RealmConfiguration.Builder config) {
        return config
                .schemaVersion(VERSION)
                .migration(MIGRATION);
    }

    private static final RealmMigration MIGRATION = new RealmMigration() {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();
            if (oldVersion == 0) {
                //dosomething
                oldVersion++;
            }
            if (oldVersion == 1) {
                //dosomething
                oldVersion++;
            }
        }
    };

    public static void init(Application application) {
        APPLICATION = application;
        Realm.init(APPLICATION);
        RealmConfiguration config = versionControl(new RealmConfiguration.Builder().name(DBNAME)).build();
        /**
         *
         * RealmConfiguration支持的方法：
         Builder.name : 指定数据库的名称。如不指定默认名为default。
         Builder.schemaVersion : 指定数据库的版本号。
         Builder.encryptionKey : 指定数据库的密钥。
         Builder.migration : 指定迁移操作的迁移类。
         Builder.deleteRealmIfMigrationNeeded : 声明版本冲突时自动删除原数据库。
         Builder.inMemory : 声明数据库只在内存中持久化。
         build : 完成配置构建。
         */
        Realm.setDefaultConfiguration(config);
    }

    public static DatabaseManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DatabaseManager.class) {
                if (INSTANCE == null)
                    INSTANCE = new DatabaseManager();
            }
        }
        return INSTANCE;
    }


    public LoginInfoDao getLoginInfoDao() {
        return loginInfoDao;
    }

    public void open() {
        loginInfoDao.open();
    }

    public void close() {
        loginInfoDao.close();
    }
}
