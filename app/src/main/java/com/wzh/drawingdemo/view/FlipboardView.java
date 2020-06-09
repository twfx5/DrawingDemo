package com.wzh.drawingdemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wzh.drawingdemo.Utils;

/**
 * 仿写 Flipboard效果
 * 上面是正常的，下面是拉伸的
 *
 * 提前需要知道：
 * Canvas的几何变换
 * translate,rotate,scale,skew，是基于view的坐标系
 * canvas.drawXXX是基于自己的坐标系
 * 如果需要做多次变换，代码的顺序需要反着写
 */
public class FlipboardView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int WIDTH = (int) Utils.dp2px(150); // 图片宽度
    private static final int LENGTH = (int) Utils.dp2px(60); // 图片移动距离
    private Bitmap bitmap;
    private Camera camera = new Camera();

    public FlipboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = Utils.getAvatar(getResources(), WIDTH);
        camera.rotateX(45);
        camera.setLocation(0 , 0, Utils.getZForCamera());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         // 正常效果
        canvas.save();
        // 绘制上半部分（反着来）
        canvas.translate(LENGTH, LENGTH);
        canvas.clipRect(0, 0, WIDTH, WIDTH /2); // 切割上半部分
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

        canvas.save();
        // 绘制下半部分（拉伸->三维变换camera.applyToCanvas，图像要移动到原点，再移动回来）
        // 先画图像
        // 移动到原点（图像的中点和view坐标原点重合）
        // 切割 下半部分
        // 再做三维变换（投影）
        // 最后移动到想要的位置
        canvas.translate(WIDTH / 2 + LENGTH, WIDTH / 2 + LENGTH);
        camera.applyToCanvas(canvas);
        canvas.clipRect(-WIDTH / 2, 0, WIDTH / 2, WIDTH /2);
        canvas.translate(-(WIDTH / 2), -(WIDTH / 2));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();
         */


        // 带点旋转的效果
        canvas.save();
        // 绘制上半部分（反着来）
        canvas.translate(WIDTH / 2 + LENGTH, WIDTH / 2 + LENGTH);
        canvas.rotate(-20);
        canvas.clipRect(-WIDTH, -WIDTH, WIDTH, 0); // 切割上半部分
        canvas.rotate(20);
        canvas.translate(-(WIDTH / 2 + LENGTH), -(WIDTH / 2 + LENGTH));
        canvas.drawBitmap(bitmap, LENGTH, LENGTH, paint);
        canvas.restore();

        canvas.save();
        // 绘制下半部分（拉伸->三维变换camera.applyToCanvas，图像要移动到原点，再移动回来）
        // 先画图像
        // 移动到原点（图像的中点和view坐标原点重合）
        // 旋转
        // 切割 下半部分 (旋转后切割的范围变大，不会大于原有的根号2倍)
        // 再做三维变换（投影）
        // 旋转回来
        // 最后移动到想要的位置
        canvas.translate(WIDTH / 2 + LENGTH, WIDTH / 2 + LENGTH);
        canvas.rotate(-20);
        camera.applyToCanvas(canvas);
        canvas.clipRect(-WIDTH, 0, WIDTH, WIDTH); // 这里切割范围设为原来的2倍，肯定都能切到
        canvas.rotate(20);
        canvas.translate(-(WIDTH / 2 + LENGTH), -(WIDTH / 2 + LENGTH));
        canvas.drawBitmap(bitmap, LENGTH, LENGTH, paint);

        canvas.restore();

    }
}
