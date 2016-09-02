package com.example.administrator.test_swiperefreshlayout_refresh;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
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
        for (int i = 0; i < 100; i++) {
            ItemBean item = new ItemBean(R.mipmap.ic_launcher,"我是标题"+i,"我是内容"+i);
            itemList.add(item);
        }

        listView = (ListView) findViewById(R.id.lv_id);
        myAdapter = new MyAdapter(this,itemList);
        listView.setAdapter(myAdapter);
        //列表项的监听
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_FLING:
                Log.i("ant","用户在手指离开屏幕之前，由于用力地滑了一下，视图仍依靠惯性继续滑动");
//                Map<String,Object> map = new HashMap<String,Object>();
//                map.put("p")
                break;
            case SCROLL_STATE_IDLE:
                Log.i("ant","视图已经停止滑动");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("ant","（手指未离开屏幕）正在滑动");
                break;

        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text =listView.getItemAtPosition(position)+"";
        Toast.makeText(this, "position="+position+" text="+text, Toast.LENGTH_SHORT).show();
    }
}
