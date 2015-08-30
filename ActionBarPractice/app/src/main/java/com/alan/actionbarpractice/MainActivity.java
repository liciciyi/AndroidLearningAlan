package com.alan.actionbarpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnHideActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHideActionBar = (Button) findViewById(R.id.button_hide_show_actionbar);
    }

    public void onClick_HideActionBar(View view){
        if (getSupportActionBar() == null){
            Toast.makeText(this,"null",Toast.LENGTH_SHORT).show();
            return;
        }
        if (getSupportActionBar().isShowing()){
            getSupportActionBar().hide();
            btnHideActionBar.setText("显示ActionBar");
        }
        else {
            getSupportActionBar().show();
            btnHideActionBar.setText("隐藏ActionBar");
        }
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


}
