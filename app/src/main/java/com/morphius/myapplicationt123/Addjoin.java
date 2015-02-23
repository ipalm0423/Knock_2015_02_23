package com.morphius.myapplicationt123;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 2 on 2015/2/23.
 */
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
    }
}
