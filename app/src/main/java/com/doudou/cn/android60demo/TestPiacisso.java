package com.doudou.cn.android60demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jinliang on 15/11/5.
 */
public class TestPiacisso extends Activity {
    @Bind(R.id.testPiacisso)
    ImageView testPiacisso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_piacasso);
        ButterKnife.bind(this);
        Picasso.with(this).
                load("http://a.hiphotos.baidu.com/image/w%3D310/sign=f2089814efc4b7453494b117fffc1e78/0bd162d9f2d3572ce98282e18e13632762d0c3af.jpg")
                .into(testPiacisso);
    }
}
