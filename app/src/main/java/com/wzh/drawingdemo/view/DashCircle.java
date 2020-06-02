package com.wzh.drawingdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wzh.drawingdemo.Utils;

/**
 * 使用DashPathEffect来绘制虚线圆
 */
public class DashCircle extends View {

    private static final float RADIUS = Utils.dp2px(120); // 半径

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    PathEffect pathEffect; // 给图形的轮廓设置效果

    {
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(2));
        // intervals： 实线长度、空白线的长度，实线长度、空白线的长度（数组长度必须为偶数）
        // phase： 绘制的虚线相对了起始地址（Path起点）的取余偏移
        //————————————————
        // new DashPathEffect(new float[] { 8, 10, 8, 10}, 0);
        //    这时偏移为0，先绘制实线，再绘制透明。
        //
        //    new DashPathEffect(new float[] { 8, 10, 8, 10}, 8);
        //    这时偏移为8，先绘制了透明，再绘制了实线.(实线被偏移过去了)
        //————————————————
        pathEffect = new DashPathEffect(new float[]{Utils.dp2px(10),Utils.dp2px(10)}, 0);
        paint.setPathEffect(pathEffect);
    }

    // new View(this)时，调用
    public DashCircle(Context context) {
        super(context);
    }

    // 在xml文件中使用时调用，attrs表示属性如：layout_width、layout_height
    public DashCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //不会自动调用的，当我们在 Theme 中定义了 Style 属性时通常在第二个构造方法中手动调用
    public DashCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, Paint paint) {
        super(context, attrs, defStyleAttr);
        this.paint = paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint);
    }
}
