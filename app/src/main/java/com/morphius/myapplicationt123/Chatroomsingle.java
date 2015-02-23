package com.morphius.myapplicationt123;


// copy from http://gogkmit.wikidot.com/demo:socket03
/*
public class Chatroomsingle extends Activity{
    public static Handler mHandler = new Handler() {
        @Override
        public void close() {

        }

        @Override
        public void flush() {

        }

        @Override
        public void publish(LogRecord record) {

        }
    };
    TextView chatBox;
    EditText chattype;
    Button send;
    String temp;
    Socket clientsocket;

    public void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.chatroomsingle);
        chatBox = (TextView)findViewById(R.id.textViewchatbox);
        chattype = (EditText)findViewById(R.id.editTextchattype);
        send = (Button)findViewById(R.id.buttonSend);

        Thread t = new Thread(readData);
        t.start();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clientsocket.isConnected()){
                    BufferedWriter bw;
                    try {
                        // 取得網路輸出串流
                        bw = new BufferedWriter( new OutputStreamWriter(clientsocket.getOutputStream()));

                        // 寫入訊息
                        bw.write(username.getText()+":"+chattype.getText()+"\n");

                        // 立即發送
                        bw.flush();
                    } catch (IOException e) {

                    }
                    // 將文字方塊清空
                    chattype.setText("");
                }
            }

        });

    }
    // 顯示更新訊息
    private Runnable updateText = new Runnable() {
        public void run() {
            // 加入新訊息並換行
            chatBox.append(temp + "\n");
        }
    };

    // 取得網路資料
    private Runnable readData = new Runnable() {
        public void run() {
            // server端的IP
            InetAddress serverIp;

            try {
                // 以內定(本機電腦端)IP為Server端
                serverIp = InetAddress.getByName("10.0.2.2");
                int serverPort = 5050;
                clientsocket = new Socket(serverIp, serverPort);

                // 取得網路輸入串流
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        clientsocket.getInputStream()));

                // 當連線後
                while (clientsocket.isConnected()) {
                    // 取得網路訊息
                    temp = br.readLine();

                    // 如果不是空訊息則
                    if(temp!=null)
                        // 顯示新的訊息
                        mHandler.post(updateText);
                }

            } catch (IOException e) {

            }
        }
    };

}
*/