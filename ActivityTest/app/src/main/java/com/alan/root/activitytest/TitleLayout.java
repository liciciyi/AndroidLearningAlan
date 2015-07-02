package com.alan.root.activitytest;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by root on 15-6-21.
 */
public class TitleLayout extends LinearLayout {

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
/*
* inflate()方法就可以动态加载一个布局文件,inflate()方法接收两个参数,第
* 一个参数是要加载的布局文件的 id,这里我们传入 R.layout.title,第二个参
* 布局再添加一个父布局,这里我们想要指定为 TitleLayout,于是直接传入 this。
* 数是给加载好的
*/
        LayoutInflater.from(context).inflate(R.layout.title, this);

        findViewById(R.id.title_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });

        findViewById(R.id.title_edit).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You click the Edit button", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
