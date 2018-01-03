package net.zoker.ucommon.app.demo3;

import net.zoker.ucommon.app.demo3.contract.Contract;

/**
 * Created by zoker on 2018/1/3.
 */

public interface PresenterFactory<T extends Contract.Presenter> {
    T create();
}
