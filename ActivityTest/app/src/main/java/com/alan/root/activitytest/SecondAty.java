package com.alan.root.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by root on 15-6-19.
 */
public class SecondAty extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondAty", "Task ID is " + getTaskId());
        setContentView(R.layout.second_aty);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondAty.this,ThirdAty.class);
                startActivity(intent);
            }
        });
    }
}
