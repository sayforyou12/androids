package com.cafe.adminapp.cafeinfo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cafe.adminapp.cafelist.Cafelist_tabFragment1;
import com.cafe.adminapp.cafelist.Cafelist_tabFragment2;
import com.cafe.adminapp.cafelist.Cafelist_tabFragment3;


public class InfoTabPagerAdapter extends FragmentPagerAdapter {

    // tab 갯수
    private int tabCount = 0;

    public InfoTabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new Cafeinfo_tabFragment1();
                break;
            case 1:
                fragment = new Cafeinfo_tabFragment2();
                break;
            case 2:
                fragment = new Cafeinfo_tabFragment3();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}

