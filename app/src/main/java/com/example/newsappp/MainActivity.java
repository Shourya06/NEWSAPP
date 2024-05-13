package com.example.newsappp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class  MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem mhome, mscience, mhealth, mentertainment, msports, mtechnology, mbusiness;
    PagerAdapter pagerAdapter;

    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mhome = findViewById(R.id.home);
        mscience = findViewById(R.id.science);
        msports = findViewById (R.id.sports);
        mtechnology = findViewById(R.id.technology);

        mentertainment = findViewById(R.id.entertainment);
        mhealth = findViewById(R.id.health);
        ViewPager viewPager = findViewById(R.id.fragmentcontainer);
        tabLayout = findViewById(R.id.include);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

// Set the adapter on the ViewPager
        viewPager.setAdapter(pagerAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                System.out.println(tab.getPosition());


                if(tab.getPosition()==0 || tab.getPosition()==1 || tab.getPosition()==2 || tab.getPosition()==3 || tab.getPosition()==4 || tab.getPosition() ==5)
                {
                    viewPager.setCurrentItem(tab.getPosition());
                    Log.e("Data Parsing", "Error parsing data1");
//                    System.out.println(tab.getPosition());
                    System.out.println(viewPager.getCurrentItem());
//                    System.out.println(tab.getPosition());
//                    System.out.println(pagerAdapter);
                    if (pagerAdapter != null) {
                        Log.e("Data Parsing", "Error parsing data2");
                        pagerAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}