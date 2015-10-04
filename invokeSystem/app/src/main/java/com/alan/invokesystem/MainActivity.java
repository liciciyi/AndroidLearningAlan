package com.alan.invokesystem;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btnCall = (Button) findViewById(R.id.btnCall);
        Button btnTouchDialer = (Button) findViewById(R.id.btnTouchDialer);
        Button btnDial = (Button) findViewById(R.id.btnDial);
        Button btnWeb = (Button) findViewById(R.id.btnWeb);
        Button btnAudio = (Button) findViewById(R.id.btnAudio);
        Button btnGoHome = (Button) findViewById(R.id.btnGoHome);
        Button btnContactList = (Button) findViewById(R.id.btnContactList);
        Button btnSettings = (Button) findViewById(R.id.btnSettings);
        Button btnWifiSettings = (Button) findViewById(R.id.btnWifiSettings);

        btnCall.setOnClickListener(this);
        btnTouchDialer.setOnClickListener(this);
        btnDial.setOnClickListener(this);
        btnWeb.setOnClickListener(this);
        btnAudio.setOnClickListener(this);
        btnGoHome.setOnClickListener(this);
        btnContactList.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        btnWifiSettings.setOnClickListener(this);

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCall:
                Intent callIntent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:10086"));
                startActivity(callIntent);
                break;
            case R.id.btnTouchDialer:
                Intent touchDialerIntent = new Intent("com.android.phone.action.TOUCH_DIALER");
                startActivity(touchDialerIntent);
                break;
            case R.id.btnDial:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:1887777"));
                startActivity(dialIntent);
                break;
            case R.id.btnWeb:
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.baidu.com"));
                startActivity(webIntent);
                break;
            case R.id.btnAudio:
                Intent audioIntent = new Intent(Intent.ACTION_GET_CONTENT);
                audioIntent.setType("audio/*");
                startActivity(Intent.createChooser(audioIntent, "选择音频程序"));
                break;
            case R.id.btnGoHome:
                Intent gohomeIntent = new Intent("android.intent.action.MAIN");
                gohomeIntent.addCategory("android.intent.category.HOME");
                startActivity(gohomeIntent);
                break;
            case R.id.btnContactList:
                Intent contactListIntent = new Intent(
                        "com.android.contacts.action.LIST_CONTACTS");
                startActivity(contactListIntent);
                break;
            case R.id.btnSettings:
                Intent settingsIntent = new Intent(
                        "android.settings.SETTINGS");
                startActivity(settingsIntent);
                break;
            case R.id.btnWifiSettings:
                Intent wifiSettingsIntent = new Intent(
                        "android.settings.WIFI_SETTINGS");
                startActivity(wifiSettingsIntent);
                break;

        }
    }
}
