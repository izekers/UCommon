package net.zoker.ucommon.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.zoker.ucommon.VersionHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VersionHelper.createInstance().addControlWithVersion3(new VersionHelper.VersionAction() {
            @Override
            public void overVersion(int version, int currentVersion) {

            }

            @Override
            public void belowVersion(int version, int currentVersion) {

            }
        }).done();
    }
}
