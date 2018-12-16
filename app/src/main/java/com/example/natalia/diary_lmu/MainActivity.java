package com.example.natalia.diary_lmu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("test");

        mTabHost = getTabHost();
        mTabHost.setup();

        TabHost.TabSpec tabSpec1 = mTabHost.newTabSpec("tab1").setIndicator("day").setContent(new Intent(this,tab1Activity.class));
        TabHost.TabSpec tabSpec2 = mTabHost.newTabSpec("tab2").setIndicator("week").setContent(new Intent(this,tab2Activity.class));
        TabHost.TabSpec tabSpec3 = mTabHost.newTabSpec("tab3").setIndicator("month").setContent(new Intent(this,tab3Activity.class));

        mTabHost.addTab(tabSpec1);
        mTabHost.addTab(tabSpec2);
        mTabHost.addTab(tabSpec3);

    }
}
