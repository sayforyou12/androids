package com.cafe.adminapp.cafelist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListTabPagerAdapter extends FragmentPagerAdapter {

    // tab 갯수
    private int tabCount = 0;

    public ListTabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new Cafelist_tabFragment1();
                break;
            case 1:
                fragment = new Cafelist_tabFragment2();
                break;
            case 2:
                fragment = new Cafelist_tabFragment3();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}

