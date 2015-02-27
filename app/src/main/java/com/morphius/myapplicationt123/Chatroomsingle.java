package com.morphius.myapplicationt123;


// copy from http://fecbob.pixnet.net/blog/post/39255891-android%E5%AF%A6%E7%8F%BE%E7%B0%A1%E5%96%AE%E7%9A%84%E8%81%8A%E5%A4%A9%E5%AE%A4
// http://gogkmit.wikidot.com/demo:socket03

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Chatroomsingle extends Activity{

    EditText editTextType, editTextList;
    Button buttonSend, buttonBack;
    OutputStream outputStream;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatroomsingle);
        editTextList = (EditText)findViewById(R.id.editTestChatList);
        editTextType = (EditText)findViewById(R.id.editTextType);
        buttonSend = (Button)findViewById(R.id.buttonSend);
        buttonBack = (Button)findViewById(R.id.buttonBack);


        try {
            //定義客戶連接的socket
            Socket clientsocket = new Socket("122.116.90.83",30000);
            //启動客戶端監聽線程
            new Thread(new ClientThread(clientsocket,handler)).start();
            outputStream = clientsocket.getOutputStream();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //得到輸入框中的內容，寫入到輸入流中
                    String msgTemp = editTextType.getText().toString();
                    outputStream.write((msgTemp+"\r\n").getBytes("utf-8"));
                    editTextList.append("\n"+msgTemp);
                    editTextType.setText("");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Chatroomsingle.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                if (msg.what == 1){
                    //得到服務器返回的信息
                    editTextList.append("\n"+msg.obj.toString());
                }
            }
        };


    }


}
