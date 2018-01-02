package net.zoker.ucommon.app;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import net.zoker.ucommon.VersionHelper;
import net.zoker.ucommon.app.dao.ModelBean;
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
        };

        getSupportFragmentManager().beginTransaction().add(R.id.root, mainFragment = new MainFragment()).commit();

        mainFragment.setData(Arrays.asList(datas));
    }


    public static class MainFragment extends SingleListFragment<ModelBean,MainViewHolder> {

        @Override
        public void onBindViewHolder(final MainViewHolder holder, int position) {
            holder.nameView.setText(getItemData(position).getTitle());
            holder.nameView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(getItemData(holder.getAdapterPosition()).getIntent());
                }
            });
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent) {
            return  new MainViewHolder(parent);
        }
    }
}
