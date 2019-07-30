package com.example.cuerpo.mainscreens.exercisetrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.cuerpo.R;
import com.example.cuerpo.SectionsPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class ExerciseActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        TodayFragment todayFragment = new TodayFragment();

        SummaryFragment summaryFragemnt = new SummaryFragment();

        adapter.addFragment(todayFragment, "TODAY");
        adapter.addFragment(summaryFragemnt, "SUMMARY");


        viewPager.setAdapter(adapter);

    }
}
