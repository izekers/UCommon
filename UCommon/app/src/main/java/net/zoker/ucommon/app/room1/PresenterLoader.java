package net.zoker.ucommon.app.room1;

import android.content.Context;
import android.support.v4.content.Loader;

import net.zoker.ucommon.app.room1.contract.Contract;

/**
 * Created by zoker on 2018/1/3.
 */

public class PresenterLoader<T extends Contract.Presenter> extends Loader<T> {
    private final PresenterFactory<T> factory;

    private T presenter;

    public PresenterLoader(Context context, PresenterFactory factory) {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading() {
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        presenter = factory.create();
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
        presenter = null;
    }
}
