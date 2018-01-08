package net.zoker.ucommon.app.realm1.dao.Iml;

import net.zoker.ucommon.app.realm1.dao.Iml.local.LocalModelIml;
import net.zoker.ucommon.app.realm1.dao.Iml.network.NetModelIml;
import net.zoker.ucommon.app.realm1.dao.model.IModel;

/**
 * Created by zoker on 2018/1/8.
 */

public class MoelIml implements IModel {
    private LocalModelIml localModelIml;
    private NetModelIml netModelIml;

    public MoelIml(LocalModelIml localModelIml, NetModelIml netModelIml) {
        this.localModelIml = localModelIml;
        this.netModelIml = netModelIml;
    }

    @Override
    public void init() {
        if (localModelIml != null)
            localModelIml.init();

        if (netModelIml != null)
            netModelIml.init();
    }

    @Override
    public void release() {

        if (localModelIml != null)
            localModelIml.release();

        if (netModelIml != null)
            netModelIml.release();
    }
}
