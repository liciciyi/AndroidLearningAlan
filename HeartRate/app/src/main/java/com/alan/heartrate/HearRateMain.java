package com.alan.heartrate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HearRateMain extends AppCompatActivity implements MenuItem.OnActionExpandListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hear_rate_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hear_rate_main, menu);
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
    public boolean onMenuItemActionExpand(MenuItem item) {
        /*
        * 以后再这里写展开的逻辑
        * */
        Toast.makeText(this, "<" + item.getTitle() + ">已经展开", Toast.LENGTH_SHORT)
                .show();

        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {

        /*
        * 以后在这里写回收的逻辑
        * */
        Toast.makeText(this, "<" + item.getTitle() + ">已经展开", Toast.LENGTH_SHORT)
                .show();

        return true;
    }
}
