package com.morphius.myapplicationt123;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class Addjoin extends Activity {

    @Override
    protected void onCreate(Bundle saveinstancestate){
        super.onCreate(saveinstancestate);
        setContentView(R.layout.addjoin);
        Button buttonj2c = (Button)findViewById(R.id.buttonjoin2chat);
        Button buttonj2m = (Button)findViewById(R.id.buttonjoin2main);
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
        final SeekBar seekBartimer = (SeekBar)findViewById(R.id.seekBartimer);
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
}
