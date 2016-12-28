package com.example.jack.sliding_menu.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jack.sliding_menu.Phoenix.RecyclerViewFragment;

/**
 * Created by 72408 on 2016/12/24.
 */

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