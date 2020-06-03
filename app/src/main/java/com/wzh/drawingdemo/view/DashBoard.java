package com.wzh.drawingdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wzh.drawingdemo.Utils;

public class DashBoard extends View {

    private static final int ANGLE = 120; // 弧的角度
    private static final float RADIUS = Utils.dp2px(120); // 半径
    private static final float LENGTH = Utils.dp2px(100); // 指针长度
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path dash = new Path(); // 虚线
    Path arc = new Path();
    PathDashPathEffect pathEffect; // 比DashPathEffect 多一个前缀 Path ，所以顾名思义，它是使用一个 Path 来绘制虚线

    public DashBoard(Context context) {
        super(context);
    }

    public DashBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DashBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(2));

        // 添加矩形path，Path.addRect()， CW 顺时针
        // 从圆弧的切点处画矩形
        dash.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CW);
        // 添加圆弧Path
        arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE);
        // 测量圆弧Path的长度，PathMeasure用来测量长度
        PathMeasure pathMeasure = new PathMeasure(arc, false);
        //————————————————
        // PathDashPathEffect表示用shape来绘制path
        // advance:两个shape的间距
        // phase:第一个点的偏移
        // PathDashPathEffect.Style 拐弯的风格
        //————————————————
        pathEffect = new PathDashPathEffect(dash, (pathMeasure.getLength() - Utils.dp2px(2)) / 20, 0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画一个弧线，useCenter = true表示连接起来
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 -RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE, false, paint);

        // 画刻度
        paint.setPathEffect(pathEffect); // 启用PathEffect来画弧线
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 -RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE, false, paint);
        paint.setPathEffect(null); // 关掉PathEffect

        // 画指针
        canvas.drawLine(getWidth() / 2, getHeight() / 2,
                (float) Math.cos(Math.toRadians(getAngleFromMark(5))) * LENGTH + getWidth() / 2,
                (float) Math.sin(Math.toRadians(getAngleFromMark(5))) * LENGTH + getHeight() / 2,
                paint);
    }

    private int getAngleFromMark(int mark) {

        return (int) (90 + (float) ANGLE / 2 + (360 - (float) ANGLE) / 20 * mark);

    }
}
