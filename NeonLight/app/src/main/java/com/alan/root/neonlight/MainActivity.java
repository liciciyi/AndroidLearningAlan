package com.alan.root.neonlight;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;



public class MainActivity extends ActionBarActivity implements Runnable {

//    5个TextView的颜色

    private int[] colors = new int[] {0xFFFF0000, 0xFF00FF00, 0xFF0000FF,
    0xFFFF00FF, 0xFF00FFFF };

    private int[] nextColorPointers = new int[] { 1, 2, 3, 4, 0};

//    保持5个TextView
    private View[] views;

    private int currentPointer = 0;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        views = new View[]{
                findViewById(R.id.textview1),
                findViewById(R.id.textview2),
                findViewById(R.id.textview3),
                findViewById(R.id.textview4),
                findViewById(R.id.textview5),
        };

        handler = new Handler();
        handler.postDelayed(this,300);
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

    @Override
    public void run() {
        int nextColorPointer = currentPointer;
        for (int i = views.length - 1; i >= 0; i--){
//            设置当前TextView的颜色
            views[i].setBackgroundColor(colors[nextColorPointers[nextColorPointer]]);
            nextColorPointer = nextColorPointers[nextColorPointer];
        }
        currentPointer++;
        if (currentPointer == 5){
            currentPointer =0;
        }
        handler.postDelayed(this,300);
    }
}
