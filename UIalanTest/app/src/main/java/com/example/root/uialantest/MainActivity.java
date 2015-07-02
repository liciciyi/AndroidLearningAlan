package com.example.root.uialantest;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ListActivity {

    private ArrayAdapter<ListCellData> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ArrayAdapter<ListCellData>(this, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);

        adapter.add(new ListCellData(this, "Toast", new Intent(this, AtyUsingToast.class)));
        adapter.add(new ListCellData(this, "Intent", new Intent(this, AtyUsingIntent.class)));


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        ListCellData data = adapter.getItem(position);
        data.startActivity();
        super.onListItemClick(l, v, position, id);
    }

    @Override

    /**************************************************
    *  通过 getMenuInflater()方法能够得到 MenuInflater 对象,再调用它的
    *  inflate()方法就可以给当前活动创建菜单了。inflate()方法接收两个参数,
    *  第一个参数用于指定我们通过哪一个资源文件来创建菜单,这里当然传入
    *  R.menu.main,第二个参数用于指定我们的菜单项将添加到哪一个 Menu 对象当中,
    *  这里直接使用 onCreateOptionsMenu()方法中传入的 menu 参数。然后给这个
    *  方法返回 true,表示允许创建的菜单显示出来,如果返回了 false,创建的菜单将
    *  无法显示。当然,仅仅让菜单显示出来是不够的,我们定义菜单不仅是为了看的,关键
    *  是要菜单真正可用才行,因此还要再定义菜单响应事件。在 FirstActivity 中
    *  重写 onOptionsItemSelected()方法:
    **************************************************/

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_menu :
                Toast.makeText(this,"you click the add Menu",Toast.LENGTH_SHORT).show();
                break;

            case R.id.remove_menu :
                Toast.makeText(this,"you click the Remove Menu",Toast.LENGTH_SHORT).show();
                break;

            default:
        }

        return true;
    }

}
