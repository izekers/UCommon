package net.zoker.ucommon.app.room1.dao.local.database.DAO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import net.zoker.ucommon.app.room1.dao.local.database.entities.LoginInfoEntity;

import java.util.List;

/**
 * Created by zoker on 2018/1/3.
 */
@Dao                                //组件声明
public interface LoginInfoDao {
    /**
        OnConflictStrategy.REPLACE：冲突策略是取代旧数据同时继续事务
        OnConflictStrategy.ROLLBACK：冲突策略是回滚事务
        OnConflictStrategy.ABORT：冲突策略是终止事务  默认策略
        OnConflictStrategy.FAIL：冲突策略是事务失败
        OnConflictStrategy.IGNORE：冲突策略是忽略冲突
    **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addLoginInfo(LoginInfoEntity loginInfoEntity);

    @Insert
    void addLoginInfos(List<LoginInfoEntity> loginInfoEntities);

    @Delete
    void deleteLoginInfo(LoginInfoEntity loginInfoEntities);

    @Delete
    void deleteLoginInfos(List<LoginInfoEntity> loginInfoEntities);


    @Update
    void updateLoginInfo(LoginInfoEntity loginInfoEntities);

    @Update
    void updateLoginInfo(List<LoginInfoEntity> loginInfoEntities);


    @Query("select * from loginInfo")
    List<LoginInfoEntity> getLoginInfo();

    @Query("select * from loginInfo where username=:name")
    LoginInfoEntity getLoginInfoByName(String name);

    @Query("select username from loginInfo")    //查询子集
    List<NameTuple> getNameTuple();

    public class NameTuple {
        @ColumnInfo(name="username")
        public String username;
    }
}
