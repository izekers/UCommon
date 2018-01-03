package net.zoker.ucommon.app.demo2.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.zoker.ucommon.app.R;
import net.zoker.ucommon.app.demo2.contract.LoginContract;
import net.zoker.ucommon.app.demo2.dao.Iml.UserIml;
import net.zoker.ucommon.app.demo2.present.LoginPresenter;

/**
 * Created by zoker on 2018/1/2.
 */

public class LoginActivity extends BaseActivity implements LoginContract.view {
    private EditText username;
    private EditText password;
    private Button login;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        presenter = new LoginPresenter(new UserIml());
        presenter.attachView(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(username.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();//这里与View断开连接
        super.onDestroy();

    }
}
