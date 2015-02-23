package com.morphius.myapplicationt123;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Chatroom extends Activity {


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.chatroom);
        Button btc2m = (Button)findViewById(R.id.buttonc2m);
        Button btc2aj = (Button)findViewById(R.id.buttonc2aj);
        btc2m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Chatroom.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btc2aj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Chatroom.this, Addjoin.class);
                startActivity(intent);
                finish();
            }
        });




    }
}
