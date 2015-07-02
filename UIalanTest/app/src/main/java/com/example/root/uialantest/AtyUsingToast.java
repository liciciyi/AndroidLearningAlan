package com.example.root.uialantest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by root on 15-6-18.
 */
public class AtyUsingToast extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_using_toast);

        findViewById(R.id.btnToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AtyUsingToast.this, "you click the button!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
