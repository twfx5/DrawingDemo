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
 * Camera和canvas的交互
 * 三维坐标系
 */
public class CameraView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final int WIDTH = (int) Utils.dp2px(150); // 图片宽度
    private static final int LENGTH = (int) Utils.dp2px(60); // 图片移动距离
    private Bitmap bitmap;
    private Camera camera = new Camera();

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = Utils.getAvatar(getResources(), WIDTH);
        camera.rotateX(30); //canvas旋转30度

        //————————————————
        // 可以想象的到：camera越往后挪，图像的投影会变小；camera越往前挪，图像的投影会变大（糊你一脸）
        // 为了避免图像糊脸的效果，设置camera的location，使得在不同分辨率的手机上，位置是一样的，就要设置camera的location
        camera.setLocation(0, 0, Utils.getZForCamera());
        // 前两个参数一般是0
        // 第三个参数是camera的Z轴，即camera离屏幕的距离--单位是英寸，一英寸 = 72像素
        //————————————————
        // 手机的屏幕密度越大，图像会放大的越大。就要把camera越往后挪
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画一个梯形的头像

        // 一、直接这样设置，图形是歪的
        // 因为旋转一个正角度后，camera投影到canvas上，图形会变歪
//        camera.applyToCanvas(canvas);
//        canvas.drawBitmap(bitmap, LENGTH, LENGTH, paint);

        //————————————————
        // 二、怎样把图形弄正
        // 图形移动到原点：移动的距离 = 实际的距离 + 图片宽/高的 一半
//        canvas.translate(-(WIDTH / 2 + LENGTH), -(WIDTH / 2 + LENGTH));
        // 再投影，三维变换
//        camera.applyToCanvas(canvas);
        // 然后移动到要画的位置
//        canvas.translate(WIDTH / 2 + LENGTH, WIDTH / 2 + LENGTH);
        // 绘制
//        canvas.drawBitmap(bitmap, LENGTH, LENGTH, paint);
        //————————————————
        // 也错了，三维坐标系camera和canvas一样，也要反着写（解释很困难，反着写好理解）

        // 三、反着写代码
        // 先画图像
        // 移动到原点 （图像的中点和view坐标原点重合）
        // 再做三维变换（投影）
        // 最后移动到想要的位置
        canvas.translate(WIDTH / 2 + LENGTH, WIDTH / 2 + LENGTH);
        camera.applyToCanvas(canvas);
        canvas.translate(-(WIDTH / 2 + LENGTH), -(WIDTH / 2 + LENGTH));
        canvas.drawBitmap(bitmap, LENGTH, LENGTH, paint);
    }
}
