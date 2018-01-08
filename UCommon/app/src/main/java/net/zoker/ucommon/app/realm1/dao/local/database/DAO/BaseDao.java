package net.zoker.ucommon.app.realm1.dao.local.database.DAO;

/**
 * Created by zoker on 2018/1/8.
 */

public interface BaseDao {

    void open();

    void close();

    boolean isOpen();
}
