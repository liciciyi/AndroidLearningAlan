package com.example.alan.fragmentpractice;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Alan on 2015/8/25.
 */
public class LeftFragment extends Fragment implements AdapterView.OnItemClickListener {

    private String[] data = new String[]{
            "灵魂战车2", "变形金刚3", "敢死队2",
    };

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.left_fragment, null);

        listView = (ListView) view.findViewById(R.id.listview_movie);
        listView.setOnItemClickListener(this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (
                getActivity(), android.R.layout.simple_list_item_activated_1,
                data);
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        try {
            TextView textView = (TextView) getActivity().findViewById(R.id.textview_detail);
            InputStream is = getActivity().getResources().getAssets().open("m" + position);

            byte[] buffer = new byte[1024];
            int count = is.read(buffer);
//            把读进来的数据放到detail中
            String detail = new String(buffer,0,count,"utf-8");

            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("detail", detail);
            startActivity(intent);

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

