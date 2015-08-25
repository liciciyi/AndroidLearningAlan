package com.example.alan.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    /*
//    ------------------------以下是绘制图形和文本的代码-----------------------

    class MyView extends View{
        private Paint paint1 = new Paint();
        private Paint paint2 = new Paint();
        private Paint paint3 = new Paint();
        private boolean useCenter = true;

        private float[] textSizeArray = new float[]{
                15, 18, 21, 24, 27
        };

        public MyView(Context context) {
            super(context);

            setBackgroundColor(Color.WHITE);

            paint1.setColor(Color.BLACK);
            paint1.setStrokeWidth(2);
            paint2.setColor(Color.RED);
            paint2.setStrokeWidth(4);
            paint3.setColor(Color.BLUE);
            paint3.setStrokeWidth(6);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            if (useCenter){
                useCenter = false;
                paint1.setColor(Color.RED);
                paint2.setColor(Color.BLUE);
                paint3.setColor(Color.GREEN);

                paint1.setStrokeWidth(6);
                paint2.setStrokeWidth(4);
                paint3.setStrokeWidth(2);
            }
            else {
                useCenter = true;
                paint1.setColor(Color.GREEN);
                paint2.setColor(Color.RED);
                paint3.setColor(Color.BLUE);

                paint1.setStrokeWidth(6);
                paint2.setStrokeWidth(4);
                paint3.setStrokeWidth(2);
            }

            for (int i = 0; i < textSizeArray.length / 2; i++ ){
                float textSize = textSizeArray[i];

                textSizeArray[i] = textSizeArray[textSizeArray.length - i - 1];
                textSizeArray[textSizeArray.length - i - 1] = textSize;
            }
//            invalidate()是用来刷新View的，必须是在UI线程中进行工作。比如在修改某个view的显示时，
// 调用invalidate()才能看到重新绘制的界面。invalidate()的调用是把之前的旧的view
// 从主UI线程队列中pop掉。
            invalidate();
            return super.onTouchEvent(event);
        }

        *//*
        * 画一个多边形
        * *//*
        public void drawLinesExt(Canvas canvas,float[] pts, Paint paint){
            float[] points = new float[pts.length * 2 - 4];
            for (int i = 0,j = 0; i < pts.length; i = i + 2){
                points[j++] = pts[i];
                points[j++] = pts[i + 1];

                if (i > 1 && i < pts.length-2){
                    points[j++] = pts[i];
                    points[j++] = pts[i + 1];
                }
            }

            canvas.drawLines(points, paint);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPoint(60,120,paint3);
            canvas.drawPoint(70, 130, paint3);
            canvas.drawPoints(new float[]
                    { 70, 140, 75, 145, 75, 160 }, paint2);
            //canvas.drawPoints(new float[]
            //              			{ 70, 140, 75, 145, 75, 160 }, 1,4,paint2);

            canvas.drawLine(10, 10, 700, 10, paint1);
            canvas.drawLine(10, 30, 300, 30, paint2);
            canvas.drawLine(10, 50, 300, 50, paint3);

//            画了三个多边形
            drawLinesExt(canvas, new float[]
                    { 10, 70, 120, 70, 120, 170, 10, 170, 10, 70 }, paint2);
            drawLinesExt(canvas, new float[]
                    { 25, 85, 105, 85, 105, 155, 25, 155, 25, 85 }, paint3);
            drawLinesExt(canvas, new float[]
                    { 160, 70, 230, 150, 170, 155, 160, 70 }, paint2);

//            画了一个实心圆环
            paint2.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(260, 110, 40, paint2);
            paint2.setStyle(Paint.Style.FILL);
            canvas.drawCircle(260, 110, 30, paint2);

            RectF rectF = new RectF();
            rectF.left = 30;
            rectF.top = 190;
            rectF.right = 120;
            rectF.bottom = 280;

            canvas.drawArc(rectF, 0, 200, useCenter, paint2);

            rectF.left = 140;
            rectF.top = 190;
            rectF.right = 280;
            rectF.bottom = 290;
            paint2.setStyle(Paint.Style.STROKE);
            canvas.drawArc(rectF, 0, 360, useCenter, paint2);

            rectF.left = 160;
            rectF.top = 190;
            rectF.right = 260;
            rectF.bottom = 290;
            paint3.setStyle(Paint.Style.STROKE);
            canvas.drawArc(rectF, 0, 360, useCenter, paint3);

            float y = 0;
            for (int i = 0; i < textSizeArray.length; i++)
            {
                paint1.setTextSize(textSizeArray[i]);

                paint1.setColor(Color.BLUE);
                canvas.drawText("Android（宽度：" + paint1.measureText("Android")
                        + "）", 20, 315 + y, paint1);
                y += paint1.getTextSize() + 5;
            }
            paint1.setTextSize(22);
            super.onDraw(canvas);
        }
    }
//    ----------------以上是绘制图形和文本的代码----------------------
    */

//--------------------以下是绘制图像的代码--------------------

    public static class MyView extends View{

        private Bitmap bitmap1;
        private Bitmap bitmap2;
        private Bitmap bitmap3;
        private Bitmap bitmap4;
        private Drawable drawable;

        public MyView(Context context) {
            super(context);
            setBackgroundColor(Color.WHITE);
//            直接使用InputStream也可以
            java.io.InputStream is  = context.getResources().openRawResource(R.drawable.panda);

            BitmapFactory.Options options = new BitmapFactory.Options();
//            压缩50%
            options.inSampleSize = 2;
            bitmap1 = BitmapFactory.decodeStream(is, null, options);

            is = context.getResources().openRawResource(R.drawable.tiger);
            bitmap2 = BitmapFactory.decodeStream(is);

            int width = bitmap2.getWidth();
            int height = bitmap2.getHeight();

            int[] pixels = new int[width * height];
            bitmap2.getPixels(pixels,0,width,0,0,width,height);

            bitmap3 = Bitmap.createBitmap(pixels,0,width,width,height,
                    Bitmap.Config.ARGB_8888);
            bitmap4 = Bitmap.createBitmap(pixels,0,width,width,height,
                    Bitmap.Config.ARGB_4444);

            drawable = context.getResources().getDrawable(R.drawable.button);
            drawable.setBounds(50,350,180,420);

        }

        @Override
        protected void onDraw(Canvas canvas) {

            canvas.drawBitmap(bitmap1,10,10,null);
            canvas.drawBitmap(bitmap2,10,200,null);
            canvas.drawBitmap(bitmap3,110,200,null);
            canvas.drawBitmap(bitmap4,210,200,null);

            drawable.draw(canvas);
            super.onDraw(canvas);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
