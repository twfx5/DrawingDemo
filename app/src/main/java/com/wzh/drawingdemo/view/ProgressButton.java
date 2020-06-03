package com.wzh.drawingdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;


public class ProgressButton extends Button {

    private static final String TAG = "ProgressButton";

    private int mProgress;
    private int mMaxProgress = 100;
    private int mMinProgress = 0;
    private boolean mFinish;
    private GradientDrawable mDrawableButton = new GradientDrawable();
    private GradientDrawable mDrawableProgress = new GradientDrawable(); // 进度条颜色

    public ProgressButton(Context context) {
        super(context);
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mDrawableButton.setCornerRadii(new float[]{0,0,30,30,30,30,30,30}); //左上，右上，右下，左下 8位数
        mDrawableProgress.setCornerRadii(new float[]{0,0,30,30,30,30,30,30});
        mDrawableButton.setColor(Color.BLACK);
        mDrawableProgress.setColor(Color.RED);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mProgress > mMinProgress && mProgress <= mMaxProgress && !mFinish) {
            //Calculate the width of progress
            int progressWidth = (int) (mProgress * getMeasuredWidth() * 0.01);

            //Set rect of progress
            mDrawableProgress.setBounds(0, 0, progressWidth , getMeasuredHeight());
            //Draw progress
            mDrawableProgress.draw(canvas);

            if (mProgress >= mMaxProgress) {
                setBackgroundDrawable(mDrawableProgress);
                mFinish = true;
            } else {
                setBackgroundDrawable(mDrawableButton);
            }
        }
    }

    public void setProgress(int progress) {
        if (!mFinish) {
            mProgress = progress;
            invalidate();
        }
    }

    public void reset() {
        mFinish = false;
        mProgress = mMinProgress;
    }
}
