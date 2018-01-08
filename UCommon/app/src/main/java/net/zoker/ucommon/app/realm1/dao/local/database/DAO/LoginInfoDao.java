package net.zoker.ucommon.app.realm1.dao.local.database.DAO;



import net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity;

import java.util.List;

/**
 * Created by zoker on 2018/1/3.
 */
public interface LoginInfoDao extends BaseDao{

    void addLoginInfo(LoginInfoEntity loginInfoEntity);

    void addLoginInfos(List<LoginInfoEntity> loginInfoEntities);

    void deleteLoginInfo(LoginInfoEntity loginInfoEntities);

    void deleteLoginInfos(List<LoginInfoEntity> loginInfoEntities);


    void updateLoginInfo(LoginInfoEntity loginInfoEntities);

    void updateLoginInfo(List<LoginInfoEntity> loginInfoEntities);

    List<LoginInfoEntity> getLoginInfo();

    LoginInfoEntity getLoginInfoByName(String name);

    List<NameTuple> getNameTuple();

    public class NameTuple {
        public String username;
    }
}
