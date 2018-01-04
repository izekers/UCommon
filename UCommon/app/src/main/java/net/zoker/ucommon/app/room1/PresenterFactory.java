package net.zoker.ucommon.app.room1;

import net.zoker.ucommon.app.room1.contract.Contract;

/**
 * Created by zoker on 2018/1/3.
 */

public interface PresenterFactory<T extends Contract.Presenter> {
    T create();
}
