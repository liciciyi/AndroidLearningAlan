package com.alan.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alan.root.labeledittext.R;

/**
 * Created by root on 15-8-23.
 * <p/>
 * 自定义控件的方法之一：
 * 组合方式，当前控件从容器类控件继承，并将若干个控件添加到当前容器中。
 * 容器类，可以显示其他布局的类，比如布局类
 */
public class LabelEditText extends LinearLayout {

    private TextView textView;
    private String labelText;
    private int labelFontSize;
    private String labelPosition;

    public LabelEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

//        读取labelText属性的资源ID
        int resouceId = attrs.getAttributeResourceValue(null, "labelText", 0);
//        未获取资源ID，继续读取属性值
        if (resouceId == 0)
            labelText = attrs.getAttributeValue(null, "labelText");
//        从资源文件中获取label属性的值
        else
            labelText = getResources().getString(resouceId);
//        如果两种方式都无法获得labelText属性的值，则表示未设置该属性，抛出异常
        if (labelText == null) {
            throw new RuntimeException("必须设置labelText的属性");
        }


//        读取labelFontSize属性的资源ID
        resouceId = attrs.getAttributeResourceValue(null, "labelFontSize", 0);
//        未获取资源ID，继续读取属性的值，如果没有设置，则设置为14
        if (resouceId == 0) {
            labelFontSize = attrs.getAttributeIntValue(null, "labelFontSize", 14);
        }
//        从资源文件中获取labelFontSize属性的值
        else
            labelFontSize = getResources().getInteger(resouceId);


//        读取labelPosition属性的资源ID
        resouceId = attrs.getAttributeResourceValue(null, "labelPosition", 0);
//        未获取资源ID，继续读取属性的值.
        if (resouceId == 0) {
            labelPosition = attrs.getAttributeValue(null, "labelPosition");
        }
//        从资源文件中获取labelPosition属性的值
        else
            labelPosition = getResources().getString(resouceId);

        if (labelPosition == null) {
            labelPosition = "left";
        }

        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li;
//        获得LAYOUT_INFLATER_SERVICE服务
        li = (LayoutInflater) context.getSystemService(infService);
        LinearLayout linearLayout = null;
//       根据labelPosition属性的值装载不同的布局文件
        if ("left".equals(labelPosition))
            linearLayout = (LinearLayout)li.inflate(R.layout.labeledittext_horizontal,this);
        else if ("top".equals(labelPosition))
            linearLayout = (LinearLayout)li.inflate(R.layout.labeledittext_vertical,this);
        else
            throw new RuntimeException("labelPosition属性只有left或者top");
//  下面的代码从相应的布局文件中获得了TextView对象，并根据LabelTextView的属性值设置TextView的属性
        textView = (TextView) findViewById(R.id.textview);
        textView.setTextSize(labelFontSize);
        textView.setText(labelText);

    }
}
