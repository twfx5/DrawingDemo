package com.wzh.drawingdemo;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wang on 2017/12/31.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> listFragment;

    public MyFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void setListFragment(List<Fragment> listFragment) {
        this.listFragment = listFragment;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }


    @Override
    public int getCount() {
        return listFragment.size();
    }

}
