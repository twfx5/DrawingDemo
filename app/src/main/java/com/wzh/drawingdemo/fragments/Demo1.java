package com.wzh.drawingdemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wzh.drawingdemo.R;

/**
 * Created by wang on 2017/12/27.
 *
 * 绘制基础 canvas和paint的基本用法，和自定义组合图形的简单实用PathView --> drawPath()
 */

public class Demo1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo1,container,false);
        return view;
    }
}
