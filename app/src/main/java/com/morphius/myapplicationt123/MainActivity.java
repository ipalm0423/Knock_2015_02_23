package com.morphius.myapplicationt123;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import static android.provider.BaseColumns._ID;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static com.morphius.myapplicationt123.MyDBhelper.COUNTTIME_COLUMN;
import static com.morphius.myapplicationt123.MyDBhelper.HIDEID_COLUMN;
import static com.morphius.myapplicationt123.MyDBhelper.NAME_COLUMN;
import static com.morphius.myapplicationt123.MyDBhelper.TABLE_NAME;


public class MainActivity extends Activity {

    public static MyDBhelper dbhelper;


    public void initView(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btm2c = (Button)findViewById(R.id.buttonm2c);
        Button btm2aj = (Button)findViewById(R.id.buttonm2aj);
        btm2c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Chatroom.class);
                startActivity(intent);


            }
        });
        btm2aj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Addjoin.class);
                startActivity(intent);

            }
        });
        initView();
        openDatabase();
        showInList();

    }
    protected void onDestroy() {
        super.onDestroy();
        closeDatabase();
    }

    private void openDatabase(){
        dbhelper = new MyDBhelper(this);
    }

    private void closeDatabase(){
        dbhelper.close();
    }
    private Cursor getCursor(){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String[] columns = {_ID, NAME_COLUMN, HIDEID_COLUMN, COUNTTIME_COLUMN};
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        startManagingCursor(cursor);
        return cursor;
    }
    private void showInList(){
        ArrayList<HashMap<String, Object>> friendList = new ArrayList<HashMap<String, Object>>();
        Cursor cursor = getCursor();
        int row_number = cursor.getCount();

        if (row_number != 0){
            cursor.moveToFirst();
            for (int i = 0; i < row_number; i++) {
                String name = cursor.getString(1);
                int counttime = cursor.getInt(3);
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("name", name);
                item.put("counttime", "聊天時間剩下:" + counttime + "小時");
                friendList.add(item);
                cursor.moveToNext();
            }

        }
        SimpleAdapter adapter = new SimpleAdapter(this, friendList, R.layout.singlefriendlist, new String[] {"name", "counttime"}, new int[]{R.id.title_text, R.id.date_text});
        final ListView listViewFriends = (ListView)findViewById(R.id.listViewFriends);
        listViewFriends.setAdapter(adapter);
        /*listViewFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //等待聊天室java建立
                Toast.makeText(getApplicationContext(), "你選擇了" + friendList[position], Toast.LENGTH_SHORT).show();
            }
        }); */

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
}