package com.example.alan.handclock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Alan on 2015/8/24.
 */
public class HandClock extends View implements Runnable{

    private int clockImageResourceId;
    private Bitmap bitmap;
    private float scale;
    private float handCenterWidthScale;
    private float handCenterHeightScale;
    private int minuteHandSize;
    private int hourHandSize;
    private Handler handler = new Handler();

    public HandClock(Context context, AttributeSet attrs) {

        // 读取相应的属性值
        super(context, attrs);
        clockImageResourceId = attrs.getAttributeResourceValue(null, "clockImageSrc", 0);
        if(clockImageResourceId > 0)
            bitmap = BitmapFactory.decodeResource(getResources(),clockImageResourceId);

//        假如没有设置scale，默认为1
        scale = attrs.getAttributeFloatValue(null,"scale",1);
        handCenterWidthScale = attrs.getAttributeFloatValue(null, "handCenterWidthScale", bitmap.getWidth() / 2);
        handCenterHeightScale = attrs.getAttributeFloatValue(null,"handCenterHeightScale",bitmap.getHeight() / 2);
        //  在读取分针和时针长度后，将其值按图像的缩放比例进行缩放
        minuteHandSize = (int) (attrs.getAttributeIntValue(null,
                "minuteHandSize", 0) * scale);
        hourHandSize = (int) (attrs.getAttributeIntValue(null, "hourHandSize",
                0) * scale);
        int currentSecond = Calendar.getInstance().get(Calendar.SECOND);
        //  将定时器设在0分时执行run方法
        handler.postDelayed(this, (60 - currentSecond) * 1000);
    }

//    将视图从窗体上分离的时候调用该方法。这时视图已经不具有可绘制部分
    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        //  删除回调类
        handler.removeCallbacks(this);
    }

    @Override
    public void run() {
//重新绘制View
        invalidate();
//        重新设置定时器，在60秒后调用run方法
        handler.postDelayed(this, 60 * 1000);
    }

    /*
    * View在屏幕上显示出来要先经过measure（计算）和layout（布局）.
    * 1、什么时候调用onMeasure方法？
    * 当控件的父元素正要放置该控件时调用.父元素会问子控件一个问题，“你想要用多大地方啊？”，
    * 然后传入两个参数——widthMeasureSpec和heightMeasureSpec.这两个参数指明控件可获得的
    * 空间以及关于这个空间描述的元数据.更好的方法是你传递View的高度和宽度到setMeasuredDimension方法里,
    * 这样可以直接告诉父控件，需要多大地方放置子控件.
    * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) (bitmap.getWidth() * scale),(int) (bitmap.getHeight() * scale));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        Rect src = new Rect();
        Rect target = new Rect();

        src.left = 0;
        src.top = 0;
        src.right = bitmap.getWidth();
        src.bottom = bitmap.getHeight();

        target.left = 0;
        target.top = 0;
        target.right = (int) (src.right * scale);
        target.bottom = (int) (src.bottom * scale);

//        画表盘图像
        canvas.drawBitmap(bitmap,src,target,paint);
        // 计算表盘中心点的横纵坐标
        float centerX = bitmap.getWidth() * scale * handCenterWidthScale;
        float centerY = bitmap.getHeight() * scale * handCenterHeightScale;
// 表表盘中心点画一个半径为5的实心圆圈
        canvas.drawCircle(centerX, centerY, 5, paint);
// 设置分针为3个象素粗
        paint.setStrokeWidth(3);
//        获得现在的时间，记好这个写法
        Calendar calender = Calendar.getInstance();
        int currentMinute = calender.get(Calendar.MINUTE);
        int currentHour = calender.get(Calendar.HOUR);
// 计算分针和时间的弧度
        double minuteRadian = Math
                .toRadians((360 - ((currentMinute * 6) - 90)) % 360);
        double hourRadian = Math.toRadians((360 - ((currentHour * 30) - 90))
                % 360 - (30 * currentMinute / 60));
        // 在表盘上画分针
        canvas.drawLine(centerX, centerY, (int) (centerX + minuteHandSize
                * Math.cos(minuteRadian)), (int) (centerY - minuteHandSize
                * Math.sin(minuteRadian)), paint);

        // 设置时针为4个象素粗
        paint.setStrokeWidth(4);
        // 在表盘上画时针
        canvas.drawLine(centerX, centerY, (int) (centerX + hourHandSize
                * Math.cos(hourRadian)), (int) (centerY - hourHandSize
                * Math.sin(hourRadian)), paint);
    }

}
