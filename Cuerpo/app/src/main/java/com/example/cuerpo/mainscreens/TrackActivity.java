package com.example.cuerpo.mainscreens;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import com.example.cuerpo.R;
import com.example.cuerpo.SectionsPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class TrackActivity extends AppCompatActivity {
//    private static final String KEY1 = "key1";
//    private static final String KEY2 = "key2";
//    private static final String WATER = "water";
//    private static final String SLEEP = "sleep";

    private SectionsPageAdapter mSectionsPageAdapter;

    private int glassOfWater;
    private double sleepHours;

    private ViewPager mViewPager;
    private TabLayout tabLayout;

    private Bundle bund;
    private Bundle bund1;

    private final int[] tabIcons = {
            R.drawable.tab_calories,
            R.drawable.tab_water,
            R.drawable.tab_sleep
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

//        Bundle bundle = getIntent().getExtras();
//        glassOfWater = bundle.getInt(KEY1);
//        sleepHours = bundle.getDouble(KEY2);
//        bund = new Bundle();
//        bund.putInt(WATER, glassOfWater);
//
//        bund1 = new Bundle();
//        bund1.putDouble(SLEEP, glassOfWater);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();


    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        CalorieTrackFragment calorieTrackFragemnt = new CalorieTrackFragment();
        WaterTrackFragment waterTrackFragemnt = new WaterTrackFragment();
//        waterTrackFragemnt.setArguments(bund);
        SleepTrackFragment sleepTrackFragemnt = new SleepTrackFragment();
//        sleepTrackFragemnt.setArguments(bund1);
        adapter.addFragment(calorieTrackFragemnt, "CALORIES");
        adapter.addFragment(waterTrackFragemnt, "WATER");


        adapter.addFragment(sleepTrackFragemnt, "SLEEP");
        viewPager.setAdapter(adapter);

    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }


}