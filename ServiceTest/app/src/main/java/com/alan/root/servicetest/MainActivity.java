package com.alan.root.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btnStopService, btnStartService, btnBindService,
            btnUnbindService, btnStartIntentService, btnLongRunningService;

    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = (Button) findViewById(R.id.btnStartService);
        btnStopService = (Button) findViewById(R.id.btnStopService);
        btnBindService = (Button) findViewById(R.id.btnBindService);
        btnUnbindService = (Button) findViewById(R.id.btnUnbindService);
        btnStartIntentService = (Button) findViewById(R.id.btnStartIntentService);
        btnLongRunningService = (Button) findViewById(R.id.btnLongRunningService);

        btnStopService.setOnClickListener(this);
        btnStartService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);
        btnStartIntentService.setOnClickListener(this);
        btnLongRunningService.setOnClickListener(this);
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

            case R.id.btnStartService:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;

            case R.id.btnStopService:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;

            case R.id.btnBindService:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;

            case R.id.btnUnbindService:
                unbindService(connection);
                break;

            case R.id.btnStartIntentService:
                Log.d("MainActivity", "Thread id is " + Thread.currentThread().getId());
                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;

            case R.id.btnLongRunningService:
                Log.d("MainActivity", "Thread id is " + Thread.currentThread().getId());
                Intent longRunningService = new Intent(this, LongRunningService.class);
                startService(longRunningService);
                break;

            default:
                break;
        }
    }
}