package com.alan.root.servicetest;

        import android.app.IntentService;
        import android.content.Intent;
        import android.util.Log;

/**
 * Created by Alan on 2015/7/2.
 */
public class MyIntentService extends IntentService{

    public MyIntentService(){
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("MyIntentService","Thread id is " + Thread.currentThread().getId());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy Executed");
    }
}