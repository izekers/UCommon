package net.zoker.ucommon.app.realm1.dao.local.database.DAO;

import io.realm.Realm;

/**
 * Created by zoker on 2018/1/8.
 */

public class RealmDaoIml implements BaseDao {
    private Realm mRealm;
    private boolean isOpen = false;

    protected Realm getRealm() {
        if (!isOpen)
            throw new RuntimeException("please open database");
        return mRealm;
    }

    @Override
    public void open() {
        if (!isOpen)
            mRealm = Realm.getDefaultInstance();
        isOpen = true;
    }

    @Override
    public void close() {
        if (isOpen)
            mRealm.close();
        isOpen = false;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }
}
