package com.example.root.uialantest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by root on 15-6-18.
 */
public class ListCellData extends Activity {

    public ListCellData(Context context, String controlName, Intent relatedIntent) {
        this.context = context;
        this.controlName = controlName;
        this.relatedIntent = relatedIntent;
    }

    private Context context;
    private String controlName;
    private Intent relatedIntent;

    public Context getContext() {
        return context;
    }

    public String getControlName() {
        return controlName;
    }

    public Intent getRelatedIntent() {
        return relatedIntent;
    }

    public void startActivity() {
        getContext().startActivity(getRelatedIntent());
    }

    public String toString() {
        return getControlName();
    }

}
