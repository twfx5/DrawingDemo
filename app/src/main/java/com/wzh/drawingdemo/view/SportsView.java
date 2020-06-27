package com.wzh.drawingdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wzh.drawingdemo.Utils;


/**
 * 文字居中的显示
 *
 * 文字BaseLine设置的两种方法：Paint.getTextBounds 和 Paint.FontMetrics
 *
 * 文字的左对齐显示
 */
public class SportsView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float RADIUS = Utils.dp2px(120); // 半径

    private Rect bounds = new Rect();
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public SportsView(Context context) {
        super(context);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        textPaint.setStyle(Paint.Style.FILL); // 文字Style要设置为填充Fill
        textPaint.setTextSize(Utils.dp2px(80));
        textPaint.setColor(Color.GREEN);
        // 文字水平居中，很简单Paint.Align.CENTER
        // 竖直居中需要根据Baseline来做偏移
        textPaint.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(15));
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90, 180, false, paint);

        paint.setColor(Color.RED);
        paint.setStrokeCap(Paint.Cap.ROUND); // 圆形的头
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                250, 240, false, paint);


        // 计算竖直方向偏移
        //————————————————
        // 第一种方法：使用text的bounds paint.getTextBounds
        // 优点：如果是固定文字，显示的会非常居中
        // 缺点：如果动态设置文字内容，会造成文字上下抖动
        //————————————————
//        textPaint.getTextBounds("abab", 0, "abab".length(), bounds);hu
        // 计算偏移
//        float offSet = (bounds.top + bounds.bottom) / 2;
        // 用原有的Baseline减去偏移，得到正确的Baseline
//        canvas.drawText("abab", 0, "abab".length(), getWidth() / 2, getHeight() / 2 - offSet, textPaint);


        //————————————————
        // 第一种方法：使用FontMetrics
        // 通过文字顶部 ascent和底部 descent来计算偏移，比上面bounds要大
        // 无论是aaaa、abab、pppp这些参差不齐的文字，都是显示在同一个区域内
        // 优点：如果动态设置文字内容，文字不会上下抖动
        //————————————————
        textPaint.getFontMetrics(fontMetrics);
        // 计算偏移
        float offSet = (fontMetrics.ascent + fontMetrics.descent ) / 2;
        // 用原有的Baseline减去偏移，得到正确的Baseline
        canvas.drawText("abab", 0, "abab".length(), getWidth() / 2, getHeight() / 2 - offSet, textPaint);

        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTextSize(Utils.dp2px(200));
        canvas.drawText("aaaa", 0, Utils.dp2px(120), textPaint); // 直接这样设置不会和屏幕左对齐，左边会有间距

        textPaint.getTextBounds("aaaa", 0, "aaaa".length(), bounds);
        canvas.drawText("aaaa", -bounds.left, getHeight() - Utils.dp2px(50), textPaint); // 左边从减去bound.left的位置开始绘制，偏移一下就能左对齐
    }
}
