package com.example.jack.sliding_menu;

import android.content.Intent;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.example.jack.sliding_menu.view.SlindingMenu;

public class MainActivity extends AppCompatActivity {

    private SlindingMenu slindingMenu ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        findViewById(R.id.phoneix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this ,PullToRefreshActivity.class));
            }
        });

    }
    public void toggleMenu(View view){

        slindingMenu = (SlindingMenu) findViewById(R.id.menu);
        slindingMenu.toggle();
    }


}
