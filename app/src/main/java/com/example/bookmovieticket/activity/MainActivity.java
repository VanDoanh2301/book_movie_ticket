package com.example.bookmovieticket.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.bookmovieticket.R;
import com.example.bookmovieticket.adapter.ViewPagerAdapter;
import com.example.bookmovieticket.fragment.HomeFragment;
import com.example.bookmovieticket.fragment.MovieFragment;
import com.example.bookmovieticket.fragment.SticketFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment = new HomeFragment();
    private MovieFragment movieFragment = new MovieFragment();

    private SticketFragment sticketFragment = new SticketFragment();

    private ArrayList<Fragment> list = new ArrayList<>();
    private ViewPager2 viewPager2;
    private ViewPagerAdapter adapter;

    private BottomNavigationView navigationView;

    TextView txtSeeAll;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSeeAll = findViewById(R.id.textView4);


        findView();
        setViewAdapter();
        openFragment();
    }


    @Override
    public void onBackPressed() {
       homeFragment.clearEditText();
    }

    public void gotoMovie() {
        viewPager2.setCurrentItem(1);
    }
    public void gotoHome() {
        viewPager2.setCurrentItem(0);
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
                    case 2:
                        navigationView.getMenu().findItem(R.id.user).setChecked(true);
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
                case R.id.user:
                    viewPager2.setCurrentItem(2);
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
        list.add(homeFragment);
        list.add(movieFragment);
        list.add(sticketFragment);
        adapter = new ViewPagerAdapter(this, list);
        navigationView = findViewById(R.id.navi_bt);
    }
}