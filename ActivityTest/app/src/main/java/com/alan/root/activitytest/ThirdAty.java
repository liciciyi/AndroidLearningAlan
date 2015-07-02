package com.alan.root.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by root on 15-6-21.
 */
public class ThirdAty extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ThirdAty","Task ID is " + getTaskId());
        setContentView(R.layout.third_aty);
    }
}
