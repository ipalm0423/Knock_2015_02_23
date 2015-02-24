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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
                finish();

            }
        });
        btm2aj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Addjoin.class);
                startActivity(intent);
                finish();
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
        SQLiteDatabase dbRead = dbhelper.getReadableDatabase();
        Cursor cursor = dbRead.rawQuery("select name from " + TABLE_NAME + " ORDER BY counttimer DESC", null);
        String[] friendlist = new String[cursor.getCount()];
        int row_count = cursor.getCount();
        if (row_count != 0) {
            cursor.moveToFirst();
            for (int i = 0; i < row_count; i++){

                friendlist[i] = cursor.getString(0);
            }

        }

        ListView listViewfriends = (ListView)findViewById(R.id.listViewFriends);
        listViewfriends.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, friendlist));






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