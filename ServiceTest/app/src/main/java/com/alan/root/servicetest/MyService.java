package com.alan.root.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
/**
 * Created by Alan on 2015/7/2.
 */
public class MyService extends Service{

    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate Executed");

        Notification notification = new Notification(R.mipmap.ic_launcher,"Notification comes",
                System.currentTimeMillis());
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        notification.setLatestEventInfo(this,"This is a title","this is content",pendingIntent);
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand Executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy Executed");

    }

    class DownloadBinder extends Binder{

        public void startDownload(){
            Log.d("MyService","start downloading");
        }

        public int getProgress(){

            Log.d("MyService","getProgress executed");
            return 0;
        }
    }
}