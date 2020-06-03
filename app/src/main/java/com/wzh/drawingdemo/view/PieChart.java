package com.wzh.drawingdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wzh.drawingdemo.Utils;

public class PieChart extends View {

    private static final int RADIUS = (int) Utils.dp2px(120); // 半径
    private static final int LENGTH = (int) Utils.dp2px(20); // 第三个饼图偏移距离

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF bounds = new RectF(); // 定义一个矩形，饼图的扇形在矩形范围中绘制

    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bounds.set(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.GREEN);
        canvas.drawArc(bounds, 0, 60, true, paint);

        paint.setColor(Color.BLACK);
        canvas.drawArc(bounds, 60, 90, true, paint);

        // 把第三个饼图从中心处，向外偏移LENGTH
        //————————————————
        // 还是可以看作从中心处，像外偏移了LENGTH---->把画板向外移动LENGTH
        // 怎么定移动的方向：
        // 移动的方向 = 已经绘制完毕的扇形角度 + 将要绘制扇形角度 / 2
        // 通过移动的角度的cos、sin的值，计算出偏移的x, y
        // x = cos(角度) * LENGTH， y = sin(角度) * LENGTH
        //————————————————
        paint.setColor(Color.BLUE);
        canvas.save();
        canvas.translate((float) Math.cos(Math.toRadians(150 + 60)) * LENGTH,
                (float) Math.sin(Math.toRadians(150 + 60)) * LENGTH);
        canvas.drawArc(bounds, 150, 120, true, paint);
        canvas.restore();

        paint.setColor(Color.RED);
        canvas.drawArc(bounds, 270, 90, true, paint);

    }
}
