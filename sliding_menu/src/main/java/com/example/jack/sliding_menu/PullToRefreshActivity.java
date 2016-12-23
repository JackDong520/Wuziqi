package com.example.jack.sliding_menu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.jack.sliding_menu.Phoenix.ListViewFragment;
import com.example.jack.sliding_menu.Phoenix.RecyclerViewFragment;
import com.example.jack.sliding_menu.R;

/**
 * Created by Oleksii Shliama.
 */
public class PullToRefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//        }
        FragmentManager fm = getSupportFragmentManager();

        viewPager.setAdapter(new SectionPagerAdapter(fm));
        tabLayout.setupWithViewPager(viewPager);
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
