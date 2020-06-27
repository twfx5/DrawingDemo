package com.wzh.drawingdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;

import com.wzh.drawingdemo.Utils;


public class ProgressButton extends androidx.appcompat.widget.AppCompatButton {

    private static final String TAG = "ProgressButton";

    private int mProgress;
    private int mMaxProgress = 100;
    private int mMinProgress = 0;
    private boolean mFinish;
    private GradientDrawable mDrawableButton = new GradientDrawable();
    private GradientDrawable mDrawableProgress = new GradientDrawable(); // 进度条颜色
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

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
        paint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 先绘制的内容(背景)
        super.onDraw(canvas);
        // 再绘制的内容
        if (!mFinish) {
            // 计算进度
            int progressWidth = (int) (mProgress * getMeasuredWidth() * 0.01);
            // 设置进度
            mDrawableProgress.setBounds(0, 0, progressWidth , getMeasuredHeight());
            // 绘制进度
            mDrawableProgress.draw(canvas);
            canvas.drawText(mProgress + "%", getWidth() / 2 - Utils.dp2px(20), getHeight() / 2 + Utils.dp2px(8), paint);
            Log.d(TAG, "onDraw: " + mProgress);
            if (mProgress >= mMaxProgress) {
                setBackground(mDrawableProgress);
                mFinish = true;
            }
        } else {
            canvas.drawText("100%", getWidth() / 2 - Utils.dp2px(20), getHeight() / 2 + Utils.dp2px(8), paint);
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
