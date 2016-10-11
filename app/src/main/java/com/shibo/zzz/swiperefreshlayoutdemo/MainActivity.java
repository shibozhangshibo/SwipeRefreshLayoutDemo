package com.shibo.zzz.swiperefreshlayoutdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout mLayout;
    private RecyclerView mRecyclerView;
    private List<String> datas = new ArrayList<>();
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatas();
mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mLayout= (SwipeRefreshLayout) findViewById(R.id.refreshlayout);
        initRecyclerView();
        initRefreshLayout();
    }

    private void initRecyclerView() {
        myAdapter = new MyAdapter(datas);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager
//        .HORIZONTAL));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener(){

            @Override
            public void onClick(View v, int position, String city) {
                Toast.makeText(MainActivity.this,"city"+city+"position"+position,Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    private void initRefreshLayout(){
        //圈圈的颜色
        mLayout.setColorSchemeResources(android.R.color.holo_orange_dark
        ,android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_purple);
        //下拉可以触发刷新的距离
        mLayout.setDistanceToTriggerSync(100);
        //圈圈的北京色
        mLayout.setProgressBackgroundColorSchemeColor
                (getResources().getColor(R.color.item_press));
        //圈圈的大小
        mLayout.setSize(SwipeRefreshLayout.LARGE);
        mLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<10;i++){
                            myAdapter.addData(i,"new city:"+i);
                        }
                        myAdapter.notifyItemRangeChanged(0,10);
                        mRecyclerView.scrollToPosition(0);
                        mLayout.setRefreshing(false);
                       // mLayout.isRefreshing();

                    }
                },3000);
            }
        });
    }

    private void initDatas() {
    for (int i = 0;i<10;i++) {
        datas.add("int"+i);
    }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.id_action_add:
                myAdapter.addData(0,"new City");
                break;
            case R.id.id_action_delete:
                myAdapter.removeData(1);
                break;
            case R.id.id_action_girdview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
                break;
            case R.id.id_action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_staview:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                        StaggeredGridLayoutManager.VERTICAL));
                break;
        }



        return super.onOptionsItemSelected(item);
    }
}
