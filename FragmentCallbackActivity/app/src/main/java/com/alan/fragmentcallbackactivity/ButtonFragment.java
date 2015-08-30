package com.alan.fragmentcallbackactivity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Alan on 2015/8/30.
 */
public class ButtonFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.button_fragment,container,false);
        return view;
    }

    public void updateText(String value){
        EditText editText = (EditText) getView();
        editText.setText(value);
    }
}
