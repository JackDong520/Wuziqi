package com.example.jack.wuziqi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jack.wuziqi.wuziqi.AnimActivity;
import com.example.jack.wuziqi.wuziqi.MainActivity;

/**
 * Created by 72408 on 2016/12/13.
 */

public class MMainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        com.markupartist.android.widget.ActionBar actionBar =
                (com.markupartist.android.widget.ActionBar) findViewById(R.id.actionbar);



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
        findViewById(R.id.MyTopbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MMainActivity.this , com.example.jack.wuziqi.MyTopbar.MainActivity.class));
            }
        });
        findViewById(R.id.Json).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MMainActivity.this , com.example.jack.wuziqi.JsonTest.MainActivity.class));
            }
        });



        final com.markupartist.android.widget.ActionBar.Action shareAction =
                new com.markupartist.android.widget.ActionBar.IntentAction(this,
                        createShareIntent(), R.drawable.ic_add_circle_black_24dp);
        actionBar.addAction(shareAction);

        final com.markupartist.android.widget.ActionBar.Action otherAction =
                new com.markupartist.android.widget.ActionBar.IntentAction(this,
                        new Intent(this, com.example.jack.wuziqi.download.MainActivity.class), R.drawable.ic_chevron_left_black_24dp);

        actionBar.addAction(otherAction);
    }
    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return i;
    }

    private Intent createShareIntent() {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Shared from the ActionBar widget.");
        return Intent.createChooser(intent, "Share");
    }
}
