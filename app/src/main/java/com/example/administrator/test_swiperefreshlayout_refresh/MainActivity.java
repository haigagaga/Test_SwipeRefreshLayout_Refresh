package com.example.administrator.test_swiperefreshlayout_refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    //给ListView添加下拉刷新
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private MyAdapter myAdapter;
    private List<ItemBean> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        //顶部刷新的样式
        swipeRefreshLayout.setColorSchemeColors(android.R.color.holo_red_light,
                android.R.color.holo_green_light,android.R.color.holo_blue_light,
                android.R.color.holo_orange_light);
        itemList = new ArrayList<ItemBean>();
        for (int i = 0; i < 20; i++) {
            ItemBean item = new ItemBean(R.mipmap.ic_launcher,"我是标题"+i,"我是内容"+i);
            itemList.add(item);
        }

        listView = (ListView) findViewById(R.id.lv_id);
        myAdapter = new MyAdapter(this,itemList);
        listView.setAdapter(myAdapter);


    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setRefreshing更改刷新的状态，停止刷新设置为false
                swipeRefreshLayout.setRefreshing(false);
                for (int i = 0; i < 20; i++) {
                    ItemBean item = new ItemBean(R.mipmap.ic_launcher,"我是标题"+i,"我是内容"+i);
                    itemList.add(item);
                }
                myAdapter.notifyDataSetChanged();

            }
        },500);
    }
}
