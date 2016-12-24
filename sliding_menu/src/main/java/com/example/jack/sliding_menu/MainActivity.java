package com.example.jack.sliding_menu;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.jack.sliding_menu.Phoenix.RecyclerViewFragment;
import com.example.jack.sliding_menu.view.SlindingMenu;

public class MainActivity extends AppCompatActivity {

    private SlindingMenu slindingMenu ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_pager);
        FragmentManager fm = getSupportFragmentManager();

        viewPager.setAdapter(new SectionPagerAdapter(fm));
        tabLayout.setupWithViewPager(viewPager);


    }
    public void toggleMenu(View view){

        slindingMenu = (SlindingMenu) findViewById(R.id.menu);
        slindingMenu.toggle();
    }
    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new RecyclerViewFragment();
                case 1:
                    return new RecyclerViewFragment();
                case 2:
                default:
                    return new RecyclerViewFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ListView";
                case 1:

                    return "RecyclerView";
                case 2:
                default:
                    return "RecyclerView";
            }
        }
    }


}
