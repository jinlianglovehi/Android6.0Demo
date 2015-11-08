package com.doudou.cn.android60demo.model;

/**
 * Created by jinliang on 15/11/4.
 * recyclerView 实体信息
 */
public class ViewModel {
    private String text ;
    private String image;

    public ViewModel(String text, String image) {
        this.text = text;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
