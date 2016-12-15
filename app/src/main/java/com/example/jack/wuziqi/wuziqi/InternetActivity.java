package com.example.jack.wuziqi.wuziqi;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.jack.wuziqi.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by 72408 on 2016/12/11.
 */

public class InternetActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        textView = (TextView) findViewById(R.id.text);

        new Thread(runnable).start();


    }
    final  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String x =  data.getString("x");
            String y =  data.getString("y");
            textView.setText(x+y);
        }
    };
    Runnable runnable =new Runnable() {
        @Override
        public void run() {

            Message message =new Message();
                Bundle data = new Bundle();
                data.putString("x", "1");
                data.putString("y", "2");
                message.setData(data);

                handler.sendMessage(message);
//            try {
//                Log.i("xxxxxx","xxxxx");
//
//
//                InputStream inputStream = socket.getInputStream();
//
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//
//                String line = null;
//                line = bufferedReader.readLine();
//                String[] point = line.split(" ");
//                Point piece = new Point(Integer.parseInt(point[0]), Integer.parseInt(point[1]));
//                bufferedReader.close();
//                socket.close();
//
//                Message message =new Message();
//                Bundle data = new Bundle();
//                data.putString("x", String.valueOf(piece.x));
//                data.putString("y", String.valueOf(piece.y));
//                message.setData(data);
//                Handler handler = new Handler();
//                handler.sendMessage(message);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//         }
    }
    };
}
