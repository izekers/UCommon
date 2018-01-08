package net.zoker.ucommon.app.realm1.dao.local.database.DAO;

import net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by zoker on 2018/1/4.
 */

public class LoginInfoDaoIml extends RealmDaoIml implements LoginInfoDao {
    public LoginInfoDaoIml() {
    }

    @Override
    public void addLoginInfo(LoginInfoEntity loginInfoEntity) {
        getRealm().beginTransaction();
        LoginInfoEntity loginInfoEntity2 = getRealm().createObject(LoginInfoEntity.class, loginInfoEntity.getUsername());
        loginInfoEntity2.setUsername(loginInfoEntity.getUsername());
        loginInfoEntity2.setPassword(loginInfoEntity.getPassword());
//        realm.copyToRealmOrUpdate(loginInfoEntity);
        getRealm().commitTransaction();
    }


    @Override
    public void addLoginInfos(List<LoginInfoEntity> loginInfoEntities) {
        getRealm().beginTransaction();
        for (LoginInfoEntity loginInfoEntity : loginInfoEntities) {
            loginInfoEntity = getRealm().createObject(LoginInfoEntity.class);
            loginInfoEntity.setUsername(loginInfoEntity.getUsername());
            loginInfoEntity.setPassword(loginInfoEntity.getPassword());
        }
//        realm.copyToRealmOrUpdate(loginInfoEntities);
        getRealm().commitTransaction();
    }

    @Override
    public void deleteLoginInfo(final LoginInfoEntity loginInfoEntities) {
        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                loginInfoEntities.deleteFromRealm();

                /**
                 RealmResults<Dog> dogs=  mRealm.where(Dog.class).findAll();
                 Dog dog=dogs.get(5);
                 dog.deleteFromRealm();
                 //删除第一个数据
                 dogs.deleteFirstFromRealm();
                 //删除最后一个数据
                 dogs.deleteLastFromRealm();
                 //删除位置为1的数据
                 dogs.deleteFromRealm(1);
                 //删除所有数据
                 dogs.deleteAllFromRealm();
                 **/
            }
        });
    }

    @Override
    public void deleteLoginInfos(List<LoginInfoEntity> loginInfoEntities) {

    }

    @Override
    public void updateLoginInfo(LoginInfoEntity loginInfoEntities) {
//        LoginInfoEntity loginInfo = mRealm.where(LoginInfoEntity.class).equalTo("_id", loginInfoEntities.get_id()).findFirst();
//        if (loginInfo == null)
//            return;
        getRealm().beginTransaction();
        getRealm().copyToRealmOrUpdate(loginInfoEntities);
//        loginInfo.setUsername(loginInfoEntities.getUsername());
//        loginInfo.setPassword(loginInfoEntities.getPassword());
        getRealm().commitTransaction();
    }

    @Override
    public void updateLoginInfo(List<LoginInfoEntity> loginInfoEntities) {
        addLoginInfos(loginInfoEntities);
//        for (LoginInfoEntity loginInfoEntity : loginInfoEntities) {
//            updateLoginInfo(loginInfoEntity);
//        }
    }

    @Override
    public List<LoginInfoEntity> getLoginInfo() {
        return getRealm().where(LoginInfoEntity.class).findAll();
    }

    @Override
    public LoginInfoEntity getLoginInfoByName(String name) {
        return getRealm().where(LoginInfoEntity.class).equalTo("username", name).findFirst();
    }

    @Override
    public List<NameTuple> getNameTuple() {
        return null;
    }
}
