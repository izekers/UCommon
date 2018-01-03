package net.zoker.ucommon.app.demo2.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import net.zoker.ucommon.app.demo2.contract.BaseContract;
import net.zoker.ucommon.app.demo2.contract.Contract;

/**
 * Created by zoker on 2018/1/2.
 */

public class BaseActivity extends AppCompatActivity implements Contract.MvpView {
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
