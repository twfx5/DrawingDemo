package com.wzh.drawingdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.wzh.drawingdemo.Utils;

/**
 * 多行文字的显示
 *
 * 换行的两种方法
 */
public class ImageTextView extends View {

    private String text = "有的时候，一段绘制代码写在不同的绘制方法中效果是一样的，" +
            "这时你可以选一个自己喜欢或者习惯的绘制方法来重写。但有一个例外：如果绘制代码既可以写在 onDraw() 里，" +
            "也可以写在其他绘制方法里，那么优先写在 onDraw() ，因为 Android 有相关的优化，" +
            "可以在不需要重绘的时候自动跳过 onDraw() 的重复执行，以提升开发效率。享受这种优化的只有 onDraw() 一个方法。";

    private TextPaint textPaint = new TextPaint();
    private StaticLayout staticLayout;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint breakTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float LENGTH = Utils.dp2px(100);
    private float[] cutWidth = new float[1];

    public ImageTextView(Context context) {
        super(context);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        textPaint.setTextSize(Utils.dp2px(15));
        staticLayout = new StaticLayout(text, textPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1, 0, false);

        breakTextPaint.setTextSize(Utils.dp2px(15));

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(8));
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //————————————————
        // 第一种方法：如果对多行文字的显示没有要求，直接使用自带的StaticLayout，它会自动把文字换行
        staticLayout.draw(canvas);


        canvas.drawRect(getWidth() - LENGTH, Utils.dp2px(240), getWidth(), Utils.dp2px(240) + LENGTH, paint);
        //————————————————
        // 第二种方法： breakText来指定换行的位置，从图片位置开始换行
        // cutWidth 是截取后文字的宽度
        // index 是截取文字的下标
        int index = breakTextPaint.breakText(text, 0, text.length(), true, getWidth() - LENGTH, cutWidth);
        // 绘制文字，从起始位置0开始，到截取的位置index
        canvas.drawText(text, 0, index, 0, Utils.dp2px(250), breakTextPaint);

        int oldIndex = index;
        index = breakTextPaint.breakText(text, index, text.length(), true, getWidth() - LENGTH, cutWidth);
        // 绘制文字，上下间距FontSpacing
        canvas.drawText(text, oldIndex, oldIndex + index, 0, Utils.dp2px(250) + breakTextPaint.getFontSpacing(), breakTextPaint);

        oldIndex += index;
        index = breakTextPaint.breakText(text, index, text.length(), true, getWidth() - LENGTH, cutWidth);
        canvas.drawText(text, oldIndex, oldIndex +index, 0 ,Utils.dp2px(250) + breakTextPaint.getFontSpacing() * 2, breakTextPaint);

    }
}
