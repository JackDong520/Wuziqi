package com.example.jack.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas ;
    private SimpleAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initViews();
        mAdapter = new SimpleAdapter(this , mDatas);
        mRecyclerView.setAdapter(mAdapter);

        //设置RecyclerView布局管理
        LinearLayoutManager lm = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL ,false);
        mRecyclerView.setLayoutManager(lm);
        //设置RecyclerView的Item间的分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerView);
    }

    private void initDatas() {

        mDatas = new ArrayList<String>();

        for (int i = 'A' ; i <='z';i++){
            mDatas.add(""+(char)i);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){


            case R.id.action_add:

                mAdapter.addData(1);

                break;

            case R.id.action_delete:

                mAdapter.delete(1);
                break;
            case R.id.action_gridview:

                mRecyclerView.setLayoutManager(new GridLayoutManager(this , 3));

                break;
            case R.id.action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

                break;
            case R.id.action_staggered:

                break;
            case R.id.action__hor_gridview:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5 ,
                        StaggeredGridLayoutManager.HORIZONTAL));

                break;

        }


        return super.onOptionsItemSelected(item);
    }
}
