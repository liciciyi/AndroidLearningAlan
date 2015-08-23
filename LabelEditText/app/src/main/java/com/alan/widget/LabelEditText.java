package com.alan.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        if (resouceId == 0) {
            labelText = attrs.getAttributeValue(null, "labelText");
        }
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
//        从资源文件中获取labelFontSize属性的值
        else
            labelPosition = getResources().getString(resouceId);

        if (labelPosition == null) {
            labelPosition = "left";
        }

    }
}
