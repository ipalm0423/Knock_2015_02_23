package com.morphius.myapplicationt123;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class Addjoin extends Activity {
    private SeekBar seekBartimer = null;
    private EditText editroomName = null;

    @Override
    protected void onCreate(Bundle saveinstancestate){
        super.onCreate(saveinstancestate);
        setContentView(R.layout.addjoin);
        Button buttonj2c = (Button)findViewById(R.id.buttonjoin2chat);
        Button buttonj2m = (Button)findViewById(R.id.buttonjoin2main);
        Button buttonCreate = (Button)findViewById(R.id.buttonCreateroom);
        editroomName = (EditText)findViewById(R.id.editTextroomname);
        buttonj2c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Addjoin.this, Chatroom.class);
                startActivity(intent);
                finish();
            }
        });
        buttonj2m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Addjoin.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editroomName.getText().toString() != null){
                    add();
                }else {
                    Toast.makeText(Addjoin.this, "請輸入房間名稱", Toast.LENGTH_SHORT).show();
                }
            }
        });
        seekBartimer = (SeekBar)findViewById(R.id.seekBartimer);
        final TextView textViewtimer = (TextView)findViewById(R.id.textViewtimebar);

        seekBartimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewtimer.setText(seekBartimer.getProgress() + "Hours");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (seekBartimer.getProgress() <= 24){
                    Toast.makeText(Addjoin.this, seekBartimer.getProgress() + " Hours", Toast.LENGTH_SHORT).show();
                }
                else if (seekBartimer.getProgress() > 24 ) {
                    int time = seekBartimer.getProgress();
                    int days = time / 24;
                    int hours = time % 24;
                    Toast.makeText(Addjoin.this, days + " Days and " + hours + " Hours", Toast.LENGTH_SHORT).show();

                }
                else if (seekBartimer.getProgress() > 720) {
                    int time = seekBartimer.getProgress();
                    int days = (time % 720) / 24;
                    int hours = (time % 720) % 24;
                    int month = time / 720;
                    Toast.makeText(Addjoin.this, month + " Months, " + days + " Days, " + hours + " Hours", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    private void add(){
        SQLiteDatabase db = MainActivity.dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDBhelper.NAME_COLUMN, editroomName.getText().toString());
        values.put(MyDBhelper.COUNTTIME_COLUMN, seekBartimer.getProgress());
        //values.put(MyDBhelper.HIDEID_COLUMN, editEmail.getText().toString()); 等待伺服器設定HIDEID
        db.insert(MyDBhelper.TABLE_NAME, null, values);
        Toast.makeText(Addjoin.this, "已建立房間: " + editroomName.getText().toString(), Toast.LENGTH_SHORT).show();
        cleanEditText();
    }
    private void cleanEditText(){
        editroomName.setText("");
    }
}
