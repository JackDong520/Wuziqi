package com.example.jack.wuziqi.MyTopbar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jack.wuziqi.R;

/**
 * Created by 72408 on 2016/12/17.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_myactionbar);
        Topbar topbar = (Topbar) findViewById(R.id.topbar);

        topbar.setOnTopbarClickListener(new Topbar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this  , "IMMOC LEFT" ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this  , "IMMOC RIGHT" ,Toast.LENGTH_SHORT).show();
            }
        });
        topbar.setLeftIsvisable(false);
    }
}
