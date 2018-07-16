package com.bril.base.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment;                         //fragment列表
    private List<String> listTitle;                              //tab名的列表

    public ViewPagerAdapter(FragmentManager fm, LinkedHashMap<String, Fragment> mapList) {
        super(fm);
        Iterator<String> iterator = mapList.keySet().iterator();
        this.listFragment = new ArrayList<>();
        this.listTitle = new ArrayList<>();
        while (iterator.hasNext()) {
            String title = iterator.next();
            listTitle.add(title);
            listFragment.add(mapList.get(title));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position % listTitle.size());
    }
}
