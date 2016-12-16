package com.example.jack.wuziqi.drawerlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jack.wuziqi.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 72408 on 2016/12/15.
 */

public class Mainactivity extends Activity implements AdapterView.OnItemClickListener {
    private DrawerLayout drawerLayout ;
    private FrameLayout flContent ;
    private ListView lv ;
    private ActionBarDrawerToggle drawerToggle;

    private List<String> datas;
    private ArrayAdapter<String>adapter;
    private String title;
    private String[]cities = {"上海","北京","深圳","广州","杭州"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);

        title = (String) getTitle();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        flContent = (FrameLayout) findViewById(R.id.flContent);
        lv = (ListView) findViewById(R.id.lv);
        datas = new ArrayList<String>();
        datas.addAll(Arrays.asList(cities));

        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, datas);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
