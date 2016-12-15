package com.example.jack.wuziqi.wuziqi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jack.wuziqi.R;

/**
 * Created by 72408 on 2016/12/13.
 */

public class MMainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        findViewById(R.id.wuziqi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MMainActivity.this,MainActivity.class));
            }
        });
        findViewById(R.id.anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MMainActivity.this , AnimActivity.class));
            }
        });
        findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MMainActivity.this ,
                        com.example.jack.wuziqi.download.MainActivity.class));
            }
        });
    }
}
