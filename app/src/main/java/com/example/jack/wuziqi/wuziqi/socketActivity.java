package com.example.jack.wuziqi.wuziqi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jack.wuziqi.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by 72408 on 2016/12/11.
 */

public class socketActivity extends Activity  {

    TextView show ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        show = (TextView) findViewById(R.id.text3);

        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    Socket socket = new Socket("192.168.1.101" , 10020);

                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String line = br.readLine();
                    show.setText("来自服务器的数据:" + line);

                    br.close();
                    socket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
