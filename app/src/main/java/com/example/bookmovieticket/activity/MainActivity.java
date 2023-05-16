package com.example.bookmovieticket.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;


import com.example.bookmovieticket.R;
import com.example.bookmovieticket.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private ViewPagerAdapter adapter;

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        setViewAdapter();
        openFragment();
    }

    private void openFragment() {
        //Set id navigation for viewPager2
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                switch (position) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.movie).setChecked(true);
                        break;
                }
            }
        });

        //Set event click item navigation
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    viewPager2.setCurrentItem(0);
                    break;
                case R.id.movie:
                    viewPager2.setCurrentItem(1);
                    break;
            }
            return true;
        });
    }

    private void setViewAdapter() {
       viewPager2.setAdapter(adapter);
    }

    private void findView() {
        viewPager2 = findViewById(R.id.view_pager2);
        adapter = new ViewPagerAdapter(this);
        navigationView = findViewById(R.id.navi_bt);
    }


}