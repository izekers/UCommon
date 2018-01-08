package net.zoker.ucommon.app.realm1;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import net.zoker.ucommon.VersionHelper;
import net.zoker.ucommon.app.R;
import net.zoker.ucommon.app.dao.ModelBean;
import net.zoker.ucommon.app.realm1.view.LoginActivity;
import net.zoker.ucommon.app.viewholder.MainViewHolder;
import net.zoker.unirecycler.base.ListFragment;
import net.zoker.unirecycler.fragment.SingleListFragment;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListFragment<ModelBean> mainFragment;
    private ModelBean[] datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datas = new ModelBean[]{
                new ModelBean("基础的MVP框架", this, net.zoker.ucommon.app.demo1.view.LoginActivity.class),
                new ModelBean("改进的MVP框架", this, net.zoker.ucommon.app.demo2.view.LoginActivity.class),
                new ModelBean("再改进的MVP框架", this, net.zoker.ucommon.app.demo3.view.LoginActivity.class),
                new ModelBean("初识Room数据库", this, LoginActivity.class),
        };

        getSupportFragmentManager().beginTransaction().add(R.id.root, mainFragment = new MainFragment()).commit();

        mainFragment.setData(Arrays.asList(datas));
    }

    public static class MainFragment extends SingleListFragment<ModelBean, net.zoker.ucommon.app.realm1.viewholder.MainViewHolder> {

        @Override
        public void onBindViewHolder(final net.zoker.ucommon.app.realm1.viewholder.MainViewHolder holder, int position) {
            holder.nameView.setText(getItemData(position).getTitle());
            holder.nameView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(getItemData(holder.getAdapterPosition()).getIntent());
                }
            });
        }

        @Override
        public net.zoker.ucommon.app.realm1.viewholder.MainViewHolder onCreateViewHolder(ViewGroup parent) {
            return  new net.zoker.ucommon.app.realm1.viewholder.MainViewHolder(parent);
        }
    }
}
