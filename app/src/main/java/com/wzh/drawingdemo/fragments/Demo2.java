package com.wzh.drawingdemo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wzh.drawingdemo.R;

/**
 * 绘制顺序
 * 1.背景
 * 2.主体（onDraw()）
 * 3.子 View（dispatchDraw()）
 * 4.滑动边缘渐变和滑动条
 * 5.前景
 *
 * 4和5的方法是onDrawForeground，这个方法是 API 23 才引入的，
 * 所以在重写这个方法的时候要确认你的 minSdk 达到了 23，不然低版本的手机装上你的软件会没有效果
 *
 *
 * View.java 的 draw() 方法的简化版大致结构（是大致结构，不是源码哦）

    public void draw(Canvas canvas) {
    ...

    drawBackground(Canvas); // 绘制背景（不能重写）
    onDraw(Canvas); // 绘制主体
    dispatchDraw(Canvas); // 绘制子 View
    onDrawForeground(Canvas); // 绘制滑动相关和前景

    ...
 }
 * Created by wang on 2017/12/27.
 */

public class Demo2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo2,container,false);
        return view;

    }
}
