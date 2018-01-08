package net.zoker.ucommon.app.realm1.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import net.zoker.ucommon.app.R;
import net.zoker.ucommon.app.realm1.PresenterLoader;
import net.zoker.ucommon.app.realm1.dao.Iml.UserIml;
import net.zoker.ucommon.app.realm1.present.LoginPresenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by zoker on 2018/1/2.
 */

public class LoginActivity extends net.zoker.ucommon.app.realm1.view.BaseActivity<net.zoker.ucommon.app.realm1.present.LoginPresenter, net.zoker.ucommon.app.realm1.contract.LoginContract.view> implements net.zoker.ucommon.app.realm1.contract.LoginContract.view {
    private AutoCompleteTextView username;
    private EditText password;
    private Button login;
    private CompleteTextAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_room);
        username = (AutoCompleteTextView) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login(username.getText().toString(), password.getText().toString());
            }
        });
        username.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                username.setText(((net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity) adapter.getItem(i)).getUsername());
                password.setText(((net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity) adapter.getItem(i)).getPassword());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadLoginInfoCache();
    }

    @Override
    public void setupUserNameTip(final List<net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity> loginInfoEntities) {
        username.post(new Runnable() {
            @Override
            public void run() {
                username.setAdapter(adapter = new CompleteTextAdapter(loginInfoEntities));
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginSuccess() {
        username.setText("");
        password.setText("");
    }

    @Override
    public void loginFail() {
        username.setText("");
        password.setText("");
    }

    public class CompleteTextAdapter extends BaseAdapter implements Filterable {
        List<net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity> loginInfoEntities;
        List<net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity> dbInfoEntities;

        public CompleteTextAdapter(List<net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity> loginInfoEntities) {
            this.dbInfoEntities = loginInfoEntities;

        }

        @Override
        public int getCount() {
            if (loginInfoEntities == null) {
                return 0;
            }
            return loginInfoEntities.size();
        }

        @Override
        public Object getItem(int i) {
            return loginInfoEntities.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_login_tip, viewGroup, false);
            }
            ((TextView) view).setText(((net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity) getItem(i)).getUsername());
            return view;
        }


        SimpleFilter simpleFilter;

        @Override
        public Filter getFilter() {
            if (simpleFilter == null) {
                simpleFilter = new SimpleFilter();
            }
            return simpleFilter;
        }

        private List<net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity> getDbDatas() {
            return dbInfoEntities;
        }

        public class SimpleFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                List<net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity> loginInfoEntities = new ArrayList<>();
                List<net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity> dbEntity = getDbDatas();
                if (dbEntity != null && !dbEntity.isEmpty()) {
                    for (net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity loginInfoEntity : getDbDatas()) {
                        if (loginInfoEntity.getUsername().contains(charSequence))
                            loginInfoEntities.add(loginInfoEntity);
                    }
                }
                filterResults.values = loginInfoEntities;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                //noinspection unchecked
                if (filterResults != null) {
                    loginInfoEntities = (List<net.zoker.ucommon.app.realm1.dao.local.database.entities.LoginInfoEntity>) filterResults.values;
                    if (loginInfoEntities!=null && loginInfoEntities.size() > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            }
        }
    }


    @Override
    public Loader<LoginPresenter> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<LoginPresenter>(this, new net.zoker.ucommon.app.realm1.PresenterFactory<net.zoker.ucommon.app.realm1.present.LoginPresenter>() {
            @Override
            public net.zoker.ucommon.app.realm1.present.LoginPresenter create() {
                return new LoginPresenter(new UserIml(LoginActivity.this));
            }
        });
    }
}
