package com.example.jack.wuziqi.JsonTest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.example.jack.wuziqi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.net.URL;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 72408 on 2016/12/20.
 */

public class MainActivity extends Activity {
    private String url = "https://api.heweather.com/x3/weather";
    private TextView textJson ;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        textJson = (TextView) findViewById(R.id.text_json);
        handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 10: textJson.setText(msg.getData().getString("JsonString"));
                        break;
                }
            }
        };


        findViewById(R.id.button_receive_json).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonThread jsonThread = new JsonThread(url);
                jsonThread.start();

            }
        });
    }
    class JsonThread extends  Thread{
        private String JsonString;
        private String url;
        private Response response ;

        public JsonThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            OkHttpClient client = new OkHttpClient();
            HttpUrl httpUrl = HttpUrl.parse(url).
                    newBuilder().
                    addQueryParameter("city","beijing").
                    addQueryParameter("key","d17ce22ec5404ed883e1cfcaca0ecaa7").
                    build();
            System.out.println(httpUrl.toString());
            Request request = new Request.Builder().url(httpUrl).build();
            try {
                response = client.newCall(request).execute();
                if (response.isSuccessful()){
                    JsonString = response.body().string();

                    Message mes = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JsonString" , JsonString);
                    mes.setData(bundle);
                    mes.what = 10;
                    handler.sendMessage(mes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}

















