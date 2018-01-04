package net.zoker.ucommon.app.room1.dao.local.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import net.zoker.ucommon.app.room1.dao.local.database.DAO.LoginInfoDao;
import net.zoker.ucommon.app.room1.dao.local.database.entities.LoginInfoEntity;

/**
 * Created by zoker on 2018/1/3.
 */
@Database(entities = {LoginInfoEntity.class},version = 1,exportSchema = false)  //@Database 声明  version 版本
public abstract class AppDatabase extends RoomDatabase{                      //抽象对象，Room会自动为该对象创建实例

    public abstract LoginInfoDao getLoginInfoDao();
}
