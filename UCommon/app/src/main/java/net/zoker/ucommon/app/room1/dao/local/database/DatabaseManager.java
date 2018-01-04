package net.zoker.ucommon.app.room1.dao.local.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import net.zoker.ucommon.app.room1.dao.local.database.DAO.LoginInfoDao;

/**
 * Created by zoker on 2018/1/3.
 */

public class DatabaseManager {
    private AppDatabase database;

    private DatabaseManager() {

    }

    public static DatabaseManager with(Context context) {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.database = databaseManager.getDatabase(context);
        return databaseManager;
    }

    public LoginInfoDao getLoginInfoDao() {
        return database.getLoginInfoDao();
    }

    //版本迁移
    private AppDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "appDatabase")
//                .allowMainThreadQueries() //允许主线程访问db
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                    + "`name` TEXT, PRIMARY KEY(`id`))");
        }
    };
}
