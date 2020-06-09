package com.wzh.drawingdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wzh.drawingdemo.Utils;

/**
 * Canvas的几何变换
 * translate,rotate,scale,skew，是基于view的坐标系
 * canvas.drawXXX是基于自己的坐标系
 * 如果需要做多次变换，代码的顺序需要反着写
 * 因为canvas有自己的坐标，我们画的view是绘制在canvas坐标上，view整体有坐标，它们是两个坐标
 * 为了便于理解，反着写（统一按view的坐标来计算）
 *
 * 如果用canvas的坐标计算，就不用反着写（比较麻烦）
 */
public class CanvasView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float RADIUS = Utils.dp2px(150); // 半径
    private static final int WIDTH = (int) Utils.dp2px(150); // 图片宽度
    private Bitmap bitmap;


    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    {
        bitmap = Utils.getAvatar(getResources(), WIDTH);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(4));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 先绘制个正方形
        canvas.drawRect(RADIUS, RADIUS, RADIUS * 2, RADIUS * 2, paint);

        //————————————————
        // 我想要的效果：
        // 先画图形
        // 在移动到(150,150)的位置
        // 在(150,150)的点，旋转30度
        //————————————————
        // 代码顺序反着写
        canvas.rotate(30, RADIUS, RADIUS);
        canvas.translate(RADIUS, RADIUS);
        canvas.drawBitmap(bitmap, 0 ,0 , paint);

    }
}
