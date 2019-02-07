package com.example.natalia.diary_lmu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.widget.TabHost;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    FirebaseUser user;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getUserName());


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Day"));
        tabLayout.addTab(tabLayout.newTab().setText("Week"));
        tabLayout.addTab(tabLayout.newTab().setText("Month"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
//        mTabHost = getTabHost();
//        mTabHost.setup();
//
//        TabHost.TabSpec tabSpec1 = mTabHost.newTabSpec("tab1").setIndicator("day").setContent(new Intent(this,tab1Activity.class));
//        TabHost.TabSpec tabSpec2 = mTabHost.newTabSpec("tab2").setIndicator("week").setContent(new Intent(this,tab2Activity.class));
//        TabHost.TabSpec tabSpec3 = mTabHost.newTabSpec("tab3").setIndicator("month").setContent(new Intent(this,tab3Activity.class));
//
//        mTabHost.addTab(tabSpec1);
//        mTabHost.addTab(tabSpec2);
//        mTabHost.addTab(tabSpec3);

    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.miProfile) {
            Intent intent =new Intent(HomeActivity.this,ProfileActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    String getUserName(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        user  = mAuth.getCurrentUser();
        String displayName = user.getEmail();
//        for (UserInfo userInfo : user.getProviderData()) {
//            if (displayName == null && userInfo.getDisplayName() != null) {
//                displayName = userInfo.getDisplayName();
//            }
//        }

        return displayName;
    }

}
