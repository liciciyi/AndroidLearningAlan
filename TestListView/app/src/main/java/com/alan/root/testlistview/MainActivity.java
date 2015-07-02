package com.alan.root.testlistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 15-6-21.
 */
public class MainActivity extends Activity {

    private ListView msgListView;

    private EditText inputText;

    private Button btnSend;

    private MsgAdapter adapter;

    private List<Msg> msgList = new ArrayList<Msg>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMsg();

        adapter = new MsgAdapter(MainActivity.this,R.layout.msg_item,msgList);
        inputText = (EditText)findViewById(R.id.input_text);
        btnSend = (Button)findViewById(R.id.btnSend);
        msgListView = (ListView)findViewById(R.id.list_view);

        msgListView.setAdapter(adapter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();
                    msgListView.setSelection(msgList.size());

                    inputText.setText("");
                }
            }
        });



    }

    private void initMsg(){

        Msg msg1 = new Msg("hello guy.",Msg.TYPE_RECEIVED);
        msgList.add(msg1);

        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SEND);
        msgList.add(msg2);

        Msg msg3 = new Msg("This is Alan. Nice talking to you. ",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }

}
