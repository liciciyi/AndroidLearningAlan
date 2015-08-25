package com.example.alan.fragmentpractice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Alan on 2015/8/25.
 */
public class DetailActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        TextView detail = (TextView)findViewById(R.id.textview_detail);
        detail.setText(getIntent().getExtras().getString("detail"));
    }
}
