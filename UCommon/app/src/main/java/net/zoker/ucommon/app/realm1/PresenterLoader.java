package net.zoker.ucommon.app.realm1;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by zoker on 2018/1/3.
 */

public class PresenterLoader<T extends net.zoker.ucommon.app.realm1.contract.Contract.Presenter> extends Loader<T> {
    private final net.zoker.ucommon.app.realm1.PresenterFactory<T> factory;

    private T presenter;

    public PresenterLoader(Context context, net.zoker.ucommon.app.realm1.PresenterFactory factory) {
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
