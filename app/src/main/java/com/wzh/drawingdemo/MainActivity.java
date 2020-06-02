package com.wzh.drawingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.wzh.drawingdemo.fragments.Demo1;
import com.wzh.drawingdemo.fragments.Demo2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.viewpager);
        initData();
    }

    private void initData() {
        List<Fragment> listFragment = new ArrayList<Fragment>();
//        listFragment.add(new Demo1());
        listFragment.add(new Demo2());

        FragmentManager fragmentManager = getSupportFragmentManager();
        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(fragmentManager);
        fragmentAdapter.setListFragment(listFragment);
        mViewPager.setAdapter(fragmentAdapter);
    }
}
