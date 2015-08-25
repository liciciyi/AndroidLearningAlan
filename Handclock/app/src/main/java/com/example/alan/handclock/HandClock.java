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

        // ��ȡ��Ӧ������ֵ
        super(context, attrs);
        clockImageResourceId = attrs.getAttributeResourceValue(null, "clockImageSrc", 0);
        if(clockImageResourceId > 0)
            bitmap = BitmapFactory.decodeResource(getResources(),clockImageResourceId);

//        ����û������scale��Ĭ��Ϊ1
        scale = attrs.getAttributeFloatValue(null,"scale",1);
        handCenterWidthScale = attrs.getAttributeFloatValue(null, "handCenterWidthScale", bitmap.getWidth() / 2);
        handCenterHeightScale = attrs.getAttributeFloatValue(null,"handCenterHeightScale",bitmap.getHeight() / 2);
        //  �ڶ�ȡ�����ʱ�볤�Ⱥ󣬽���ֵ��ͼ������ű�����������
        minuteHandSize = (int) (attrs.getAttributeIntValue(null,
                "minuteHandSize", 0) * scale);
        hourHandSize = (int) (attrs.getAttributeIntValue(null, "hourHandSize",
                0) * scale);
        int currentSecond = Calendar.getInstance().get(Calendar.SECOND);
        //  ����ʱ������0��ʱִ��run����
        handler.postDelayed(this, (60 - currentSecond) * 1000);
    }

//    ����ͼ�Ӵ����Ϸ����ʱ����ø÷�������ʱ��ͼ�Ѿ������пɻ��Ʋ���
    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        //  ɾ���ص���
        handler.removeCallbacks(this);
    }

    @Override
    public void run() {
//���»���View
        invalidate();
//        �������ö�ʱ������60������run����
        handler.postDelayed(this, 60 * 1000);
    }

    /*
    * View����Ļ����ʾ����Ҫ�Ⱦ���measure�����㣩��layout�����֣�.
    * 1��ʲôʱ�����onMeasure������
    * ���ؼ��ĸ�Ԫ����Ҫ���øÿؼ�ʱ����.��Ԫ�ػ����ӿؼ�һ�����⣬������Ҫ�ö��ط���������
    * Ȼ����������������widthMeasureSpec��heightMeasureSpec.����������ָ���ؼ��ɻ�õ�
    * �ռ��Լ���������ռ�������Ԫ����.���õķ������㴫��View�ĸ߶ȺͿ�ȵ�setMeasuredDimension������,
    * ��������ֱ�Ӹ��߸��ؼ�����Ҫ���ط������ӿؼ�.
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

//        ������ͼ��
        canvas.drawBitmap(bitmap,src,target,paint);
        // ����������ĵ�ĺ�������
        float centerX = bitmap.getWidth() * scale * handCenterWidthScale;
        float centerY = bitmap.getHeight() * scale * handCenterHeightScale;
// ��������ĵ㻭һ���뾶Ϊ5��ʵ��ԲȦ
        canvas.drawCircle(centerX, centerY, 5, paint);
// ���÷���Ϊ3�����ش�
        paint.setStrokeWidth(3);
//        ������ڵ�ʱ�䣬�Ǻ����д��
        Calendar calender = Calendar.getInstance();
        int currentMinute = calender.get(Calendar.MINUTE);
        int currentHour = calender.get(Calendar.HOUR);
// ��������ʱ��Ļ���
        double minuteRadian = Math
                .toRadians((360 - ((currentMinute * 6) - 90)) % 360);
        double hourRadian = Math.toRadians((360 - ((currentHour * 30) - 90))
                % 360 - (30 * currentMinute / 60));
        // �ڱ����ϻ�����
        canvas.drawLine(centerX, centerY, (int) (centerX + minuteHandSize
                * Math.cos(minuteRadian)), (int) (centerY - minuteHandSize
                * Math.sin(minuteRadian)), paint);

        // ����ʱ��Ϊ4�����ش�
        paint.setStrokeWidth(4);
        // �ڱ����ϻ�ʱ��
        canvas.drawLine(centerX, centerY, (int) (centerX + hourHandSize
                * Math.cos(hourRadian)), (int) (centerY - hourHandSize
                * Math.sin(hourRadian)), paint);
    }

}
