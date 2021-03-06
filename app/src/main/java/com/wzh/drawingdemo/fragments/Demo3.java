package com.wzh.drawingdemo.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wzh.drawingdemo.R;
import com.wzh.drawingdemo.view.ProgressButton;

public class Demo3 extends Fragment {

    private ProgressButton button;
    private int progress = 0;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress += 3;
            button.setProgress(progress);
            sendEmptyMessageDelayed(0, 100);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demo3,container,false);
        button = view.findViewById(R.id.btn);
        handler.sendEmptyMessageDelayed(0, 200);
        return view;

    }
}
