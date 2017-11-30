package com.bwie.liyongkai.listview_demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liyongkai on 2017/11/29.
 */

public class BallView extends View {
    private Paint paint;
    private String text;
    private float rx = 100, ry = 200;
    private float radius = 50;
    //    private int width;
//    private int height;
    private boolean isScale;

    public BallView(Context context) {
        this(context, null);
    }

    public BallView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BallView);
        //获取自己定义的值
        text = a.getString(R.styleable.BallView_text);
        int color = a.getColor(R.styleable.BallView_textColor, Color.BLACK);
        float dimension = a.getDimension(R.styleable.BallView_textSize, 20);

        //回收
        a.recycle();
        //创建画笔
        paint = new Paint();

        //设置抗锯齿
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setTextSize(dimension);

    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Log.e("log", "onMeasure");
//        //获取测量模式
//        int mode = MeasureSpec.getMode(widthMeasureSpec);
//        switch (mode) {
//            case MeasureSpec.AT_MOST:
//                Log.e("ddd", "AT_MOST");
//                setMeasuredDimension(width, height);
//                break;
//            case MeasureSpec.UNSPECIFIED:
//                Log.e("ddd", "UNSPECIFIED");
//                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//                break;
//            case MeasureSpec.EXACTLY:
//                Log.e("ddd", "EXACTLY");
//                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//                break;
//        }
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置背景颜色
        canvas.drawColor(Color.WHITE);
        //设置画笔颜色
        paint.setColor(Color.RED);
        method1(canvas);


    }

    protected void method1(Canvas canvas) {
        canvas.drawCircle(rx, ry, radius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("hehe", rx + "" + ry);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                rx = event.getX();
                ry = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                rx = event.getX();
                ry = event.getY();
                if (isScale) {
                    float x1 = event.getX(0);
                    float y1 = event.getY(0);
                    float x2 = event.getX(1);
                    float y2 = event.getY(1);
                    Log.e("log2", "x1:" + x1 + " y1:" + y1 + " x2:" + x2 + " y2:" + y2);
                    radius = (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
                }
                break;
            case MotionEvent.ACTION_UP:
                rx = event.getX();
                ry = event.getY();
                break;
            case MotionEvent.ACTION_POINTER_UP:
                isScale = false;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.e("log2", "ACTION_POINTER_DOWN");
                isScale = true;

                break;

        }
        invalidate();
        return true;
    }

}
