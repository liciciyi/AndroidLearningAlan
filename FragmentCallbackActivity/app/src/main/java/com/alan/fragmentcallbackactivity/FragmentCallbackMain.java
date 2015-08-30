package com.alan.fragmentcallbackactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
/**
 * Created by Alan on 2015/8/30.
 *
 * 本demo的逻辑是验证Fragment的独立性
 *不能用按钮里面直接获得edittext，比如不需要在单击事件里面直接获得另一个fragment
 *
 */

public class FragmentCallbackMain extends AppCompatActivity implements TopFragment.OnTopButtonClickedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_callback_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragment_callback_main, menu);
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
    public void onClick(String name) {
        ButtonFragment fragment = (ButtonFragment) getFragmentManager()
                .findFragmentByTag("button_fragment");
        fragment.updateText("onClick:" + name);
    }
}
