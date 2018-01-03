package net.zoker.ucommon.app.demo3.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import net.zoker.ucommon.app.demo3.contract.Contract;

/**
 * Created by zoker on 2018/1/2.
 */

public class BaseActivity<P extends Contract.Presenter<V>, V extends Contract.MvpView>  extends AppCompatActivity implements Contract.MvpView ,LoaderManager.LoaderCallbacks<P>{
    ProgressDialog dialog;
    public final static int BASE_ACTIVITY_LOADER_ID = 100;
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(BASE_ACTIVITY_LOADER_ID, null, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showLoading(String msg) {
        dialog = new ProgressDialog(this);
        dialog.show();
    }

    @Override
    public void hideLoading() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        // TODO: 子类要实现此方法创建Loader
        return null;
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P data) {
        presenter = data;
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        presenter = null;
    }
}
