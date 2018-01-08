package net.zoker.ucommon.app.realm1;

/**
 * Created by zoker on 2018/1/3.
 */

public interface PresenterFactory<T extends net.zoker.ucommon.app.realm1.contract.Contract.Presenter> {
    T create();
}
