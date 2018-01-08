package net.zoker.ucommon.app.realm1.dao.Iml.local;

import net.zoker.ucommon.app.realm1.dao.local.database.DatabaseManager;
import net.zoker.ucommon.app.realm1.dao.model.IModel;

/**
 * Created by zoker on 2018/1/8.
 */

public class LocalModelIml implements IModel {
    @Override
    public void init() {
        DatabaseManager.getInstance().open();
    }

    @Override
    public void release() {
        DatabaseManager.getInstance().close();
    }
}
