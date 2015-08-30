package com.alan.fragmentcallbackactivity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Alan on 2015/8/30.
 */
public class TopFragment extends Fragment implements View.OnClickListener {

    private OnTopButtonClickedListener listener;

//    内部接口
    public interface OnTopButtonClickedListener{
        public void onClick(String name);
    }

    /*
    * 获得当前实现这个接口的活动
    * */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getActivity() instanceof OnTopButtonClickedListener){
            listener = (OnTopButtonClickedListener)getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.top_fragment,container,false);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (listener != null){
            listener.onClick("Top Fragment Demo");
        }
    }
}
