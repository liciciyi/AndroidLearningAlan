package com.example.alan.icontextview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Alan on 2015/8/24.
 * �������ռ���Զ���ؼ�
 */
public class IconTextView extends TextView {

//    �����ռ��ֵ,�������ȡ
    private final String namespace = "com.example.alan.icontextview.widget.iconTextView";

//    ͼ����ԴID
    private int recourceId = 0;
    private Bitmap bitmap;

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        recourceId = attrs.getAttributeResourceValue(namespace,"iconSrc",0);
        if (recourceId > 0)
            bitmap = BitmapFactory.decodeResource(getResources(),recourceId);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (bitmap != null){
//     ��ԭͼ�Ͻ�ȡͼ��������ڱ�����Ϊ����ͼ��
            Rect src = new Rect();
//            ����ȡ��ͼ���Ƶ�bitmap�ϵ�Ŀ�������ڱ������븴��������ͬ
            Rect target = new Rect();
            src.left = 0;
            src.top = 0;
            src.right = bitmap.getWidth();
            src.bottom = bitmap.getHeight();

            int textHeight = (int) getTextSize();
            target.left = 0;
//            ����ͼ���Ƶ�Ŀ¼����������ꡣ����TextView
// ���ı����ݲ����Ǵ���˿�ʼ���Ƶģ���ˣ���Ҫ���¼������ͼ���������
            target.top = (int) ((getMeasuredHeight() - getTextSize()) / 2) +1;
            target.bottom = target.top + textHeight;
//  Ϊ�˱�֤ͼ�񲻱��Σ���Ҫ����ͼ��߶����¼���ͼ��Ŀ��
            target.right = (int)(textHeight * (bitmap.getWidth() / (float)bitmap.getHeight()));
//  ��ʼ����ͼ��
            canvas.drawBitmap(bitmap,src,target,getPaint());
//   ��TextView�е��ı������ƶ�һ���ľ��루�ڱ������ƶ���ͼ���ȼ�2�����ص��λ�ã�
            canvas.translate(target.right + 2, 0);

        }
        super.onDraw(canvas);
    }
}
