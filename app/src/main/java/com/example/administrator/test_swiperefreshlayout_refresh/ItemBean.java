package com.example.administrator.test_swiperefreshlayout_refresh;

import android.media.Image;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */

public class ItemBean {
    public int imageResid;
    public String title;
    public String content;

    public ItemBean(int imageResid, String title, String content) {
        this.imageResid = imageResid;
        this.title = title;
        this.content = content;
    }

    public int getImageResid() {
        return imageResid;
    }

    public void setImageResid(int imageResid) {
        this.imageResid = imageResid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
