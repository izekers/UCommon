package net.zoker.ucommon.app.demo3.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.zoker.ucommon.app.R;
import net.zoker.ucommon.app.demo3.PresenterFactory;
import net.zoker.ucommon.app.demo3.PresenterLoader;
import net.zoker.ucommon.app.demo3.contract.LoginContract;
import net.zoker.ucommon.app.demo3.dao.Iml.UserIml;
import net.zoker.ucommon.app.demo3.present.LoginPresenter;

/**
 * Created by zoker on 2018/1/2.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginContract.view>  implements LoginContract.view {
    private EditText username;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(username.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public Loader<LoginPresenter> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<LoginPresenter>(this, new PresenterFactory<LoginPresenter>() {
            @Override
            public LoginPresenter create() {
                return new LoginPresenter(new UserIml());
            }
        });
    }
}
