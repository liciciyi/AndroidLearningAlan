package com.example.root.uialantest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by root on 15-6-18.
 *
 * 在启动活动时传递数据的思路很简单,Intent 中提供了一系列 putExtra()方法的重载,可
 * 以把我们想要传递的数据暂存在 Intent 中,启动了另一个活动后,只需要把这些数据再从
 * Intent 中取出就可以了。比如说 FirstActivity 中有一个字符串,现在想把这个字符串传递到
 * SecondActivity 中,你就可以这样编写:
 * button1.setOnClickListener(new OnClickListener() {
 *      @Override
        public void onClick(View v) {
            String data = "Hello SecondActivity";
            Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
            intent.putExtra("extra_data", data);
            startActivity(intent);
        }
 *   });
 *
 *  这里我们还是使用显式 Intent 的方式来启动 SecondActivity,并通过 putExtra()方法传递
 *  了一个字符串。注意这里 putExtra()方法接收两个参数,第一个参数是键,用于后面从 Intent
 *  中取值,第二个参数才是真正要传递的数据。然后我们在 SecondActivity 中将传递的数据取出,
 *  并打印出来,代码如下所示:
 *   public class SecondActivity extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.second_layout);
            Intent intent = getIntent();
            String data = intent.getStringExtra("extra_data");
            Log.d("SecondActivity", data);
        }
    }
 * 首先可以通过 getIntent()方法获取到用于启动 SecondActivity 的 Intent,然后调用
 * getStringExtra()方法,传入相应的键值,就可以得到传递的数据了。这里由于我们传递的是
 * 字符串,所以使用 getStringExtra()方法来获取传递的数据,如果传递的是整型数据,则使用
 * getIntExtra()方法,传递的是布尔型数据,则使用 getBooleanExtra()方法,以此类推。
 *
 */
public class AtyUsingIntent extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_using_intent);

        findViewById(R.id.btnInetentWeb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        findViewById(R.id.btnIntentTel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });
    }
}
