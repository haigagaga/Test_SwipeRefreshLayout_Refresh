package com.example.administrator.test_swiperefreshlayout_refresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 *      适配器模式的应用
 *      1.降低程序耦合性
 *      2.容易拓展
 */

/**
 * ListView的缓存机制： 每个手机屏幕大小是有限的，
 * listview不会全部加载出来。当进行滑动时，上面的数据被回收到recycler（View缓冲池）中，
 * 而新要显示的数据从recycler中取出布局文件，重新设置数据显示出来。
 * 总结出来一句话，需要才显示，显示完就被回收到缓存
 *
 * */

public class MyAdapter extends BaseAdapter {

    LayoutInflater inflater;
    List<ItemBean> beanList;

    public MyAdapter(Context context,List<ItemBean> list) {
        inflater = LayoutInflater.from(context);
        beanList = list;
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //ListView缓存，更通过ViewHolder类来实现显示数据的仕途的缓存，避免多次通过findviewbyid...寻找控件。
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //文艺式
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.content= (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        ItemBean bean = beanList.get(position);
        viewHolder.imageView.setImageResource(bean.imageResid);
        viewHolder.title.setText(bean.title);
        viewHolder.content.setText(bean.content);

        return convertView;

    }
        //每个ViewHolder代表一个item的内容
    class ViewHolder{
        //避免重复的findViewbyId
        public ImageView imageView;
        public TextView title;
        public TextView content;

    }
}
