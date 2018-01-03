package net.zoker.ucommon.app.demo1.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.zoker.ucommon.app.*;
import net.zoker.ucommon.app.demo1.contract.LoginContract;
import net.zoker.ucommon.app.demo1.dao.Iml.UserIml;
import net.zoker.ucommon.app.demo1.dao.model.IUserModel;
import net.zoker.ucommon.app.demo1.present.LoginPresenter;

import zoker.net.mvp.base.Callback;

/**
 * Created by zoker on 2018/1/2.
 */

public class LoginActivity extends AppCompatActivity implements LoginContract.view{
    private EditText username;
    private EditText password;
    private Button login;
    private LoginContract.Presenter presenter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        presenter = new LoginPresenter(this,new UserIml());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(getUsername(),getPassword());
            }
        });
    }

    @Override
    public String getUsername() {
        return username.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
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
    public void showResult(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
}
