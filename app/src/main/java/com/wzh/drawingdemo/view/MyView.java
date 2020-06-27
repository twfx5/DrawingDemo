package com.wzh.drawingdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wzh.drawingdemo.Utils;

public class MyView extends View {

    private static final int ANGLE = 120; // 弧的角度
    private static final float RADIUS = Utils.dp2px(120); // 半径
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    PathDashPathEffect pathEffect;
    Path dash = new Path();

    private int radius;
    private Paint linePaint;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(2));
        paint.setColor(Color.BLUE);

        dash.addRect(0, 0, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CW);
        Path arc = new Path();
        arc.addArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE);
        PathMeasure pathMeasure = new PathMeasure(arc, false);
        pathEffect = new PathDashPathEffect(dash, (pathMeasure.getLength() - Utils.dp2px(2)) / 20, 0, PathDashPathEffect.Style.ROTATE);

        radius = getWidth() / 2;
        // 初始化画笔
        linePaint = new Paint();
        linePaint.setColor(Color.GREEN);
        // 设置画笔的宽度（线的粗细）
        linePaint.setStrokeWidth(2);
        // 设置抗锯齿
        linePaint.setAntiAlias(true);
    }

    // 刻度经过角度范围
    private float targetAngle = 360 - ANGLE;
    float a = targetAngle / 100;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
                90 + ANGLE / 2, 360 - ANGLE, false, paint);

//        paint.setPathEffect(pathEffect);
//        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS,
//                90 + ANGLE / 2, 360 - ANGLE, false, paint);
//        paint.setPathEffect(null);
        drawViewLine(canvas);
    }

    private void drawViewLine(Canvas canvas) {
        // 保存之前的画布状态
        canvas.save();
        // 移动画布，实际上是改变坐标系的位置
        canvas.translate(radius, radius);
        // 旋转坐标系,需要确定旋转角度
        canvas.rotate(ANGLE);
        // 累计叠加的角度
        for (int i = 0; i <= 100; i++) {
            canvas.drawLine(0, radius, 0, radius - 20, linePaint);
            canvas.rotate(a);
        }
        // 恢复画布状态。
        canvas.restore();
    }
}
