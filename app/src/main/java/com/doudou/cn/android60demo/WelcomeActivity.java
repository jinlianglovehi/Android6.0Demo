package com.doudou.cn.android60demo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.doudou.cn.android60demo.adapter.RecyclerViewAdapter;
import com.doudou.cn.android60demo.model.ViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by jinliang on 15/11/4.
 * android6.0 新特性
 * 界面新特性
 */
public class WelcomeActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.content)
    CoordinatorLayout content;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private static List<ViewModel> items = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            items.add(new ViewModel("Item" + i, "http://h.hiphotos.baidu.com/image/w%3D310/sign=8eaa413779ec54e741ec1c1f89399bfd/9d82d158ccbf6c812f9fe0e1be3eb13533fa400b.jpg"));
        }

    }

    @Bind(R.id.recycler)
    RecyclerView recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(WelcomeActivity.this);
        // initRecyClerView();
        initToolbar();
        initCirCleBtnEvent();

        /**
         * 测试代码的操作
         */
        initRecyClerViewData();

    }

    private void initRecyClerViewData() {
        //       mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        // recycler.setLayoutManager(new LinearLayoutManager(this));//这里用线性宫格显示 类似于grid view
        //recycler.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplicationContext(), items);
        recycler.setAdapter(adapter);


    }

    private void initCirCleBtnEvent() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WelcomeActivity.this, "点击圆形图标", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化recyclerView
     */
//    private void initRecyClerView() {
//        myRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//    }
    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
