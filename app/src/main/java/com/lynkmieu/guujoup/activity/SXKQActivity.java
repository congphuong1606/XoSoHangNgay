package com.lynkmieu.guujoup.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lynkmieu.guujoup.R;
import com.lynkmieu.guujoup.activity.fragment.MienBacFragment;
import com.lynkmieu.guujoup.activity.fragment.MienNamFragment;
import com.lynkmieu.guujoup.activity.fragment.MienTrungFragment;
import com.lynkmieu.guujoup.adapter.ViewPagerAdapter;


public class SXKQActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sxkq);
        finID();

        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }


    private void finID() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

    }


    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MienBacFragment(), "Bắc");
        adapter.addFragment(new MienTrungFragment(), "Trung");
        adapter.addFragment(new MienNamFragment(), "Nam");
        viewPager.setAdapter(adapter);
    }

}
