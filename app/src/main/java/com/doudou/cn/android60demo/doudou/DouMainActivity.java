package com.doudou.cn.android60demo.doudou;

import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.doudou.cn.android60demo.MainActivity;
import com.doudou.cn.android60demo.R;
import com.doudou.cn.android60demo.model.ViewModel;
import com.doudou.cn.android60demo.testEasyRecyClerView.Person;
import com.doudou.cn.android60demo.testEasyRecyClerView.PersonAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.DynamicPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by jinliang on 15/11/4.
 * android6.0 新特性
 * 界面新特性
 */
public class DouMainActivity extends AppCompatActivity implements
        RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

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
    EasyRecyclerView recyclerView2;


    private RollPagerView mRollViewPager;
    private PersonAdapter adapter;
    private Handler handler = new Handler();
    private int page = 0;
    private View drawer_head;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dou_activity_welcome);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ButterKnife.bind(DouMainActivity.this);
        // initRecyClerView();
        initToolbar();
        initCirCleBtnEvent();

        /**
         * 测试代码的操作
         */
        initRecyClerViewData();

        initNavigationView();

    }

    /**
     * 初始化左侧的菜单的内容
     */
    private void initNavigationView() {

        // Menu meu=navigationView.findViewById(R.id.username);
        drawer_head = navigationView.inflateHeaderView(R.layout.drawer_header);
        TextView textView = (TextView) drawer_head.findViewById(R.id.username);
        textView.setText("鸣人");
        TextView  email = (TextView) drawer_head.findViewById(R.id.email);
        email.setText("12242384738478@qq.com");

        // 获取memu 的菜单 设备menu 的设置
        Menu menu = navigationView.getMenu();

        MenuItem item = menu.getItem(0);
        item.setTitle("nihao");
        item.setIcon(R.drawable.ic_launcher);

        MenuItem item2 = menu.getItem(2);
        item2.setChecked(true);
    }

    private void initRecyClerViewData() {
        //       mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        // recycler.setLayoutManager(new LinearLayoutManager(this));//这里用线性宫格显示 类似于grid view
        //recycler.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));

        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapterWithProgress(adapter = new PersonAdapter(this));
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rollviewpager, null);
                // return mRollViewPager;
            }

            @Override
            public void onBindView(View headerView) {
                mRollViewPager = (RollPagerView) headerView.findViewById(R.id.roll_view_pager);
                mRollViewPager.setAnimationDurtion(1000);
                mRollViewPager.setAdapter(new TestAdapter());
            }
        });
        adapter.setMore(R.layout.view_more, this);
        adapter.setNoMore(R.layout.view_nomore);

        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemClick(int position) {
                adapter.remove(position);
                return true;
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(DouMainActivity.this, "你点击的是：" + position, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setError(R.layout.view_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.resumeMore();
            }
        });
//        top.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                recyclerView2.scrollToPosition(3);
//            }
//        });
        recyclerView2.setRefreshListener(this);


        onRefresh();
    }

    private void initCirCleBtnEvent() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DouMainActivity.this, "点击圆形图标", Toast.LENGTH_SHORT).show();
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


    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Person> arr = new ArrayList<>();
                final int ipage = page;
                arr.add(new Person("http://i2.hdslb.com/52_52/user/61175/6117592/myface.jpg", "月の星く雪" + "————————第" + ipage + "页", "完结来补"));
                arr.add(new Person("http://i1.hdslb.com/52_52/user/6738/673856/myface.jpg", "影·蓝玉", "一看评论被***了一脸，伐开心。"));
                arr.add(new Person("http://i0.hdslb.com/52_52/account/face/6247858/e779259d/myface.png", "i琳夏i", "(｀・ω・´)"));
                arr.add(new Person("http://i0.hdslb.com/52_52/user/18494/1849483/myface.jpg", "Minerva。", "为啥下载不能了？π_π"));
                arr.add(new Person("http://i0.hdslb.com/52_52/account/face/4613528/303f4f5a/myface.png", "如歌行极", "求生肉（/TДT)/"));
                arr.add(new Person("http://i0.hdslb.com/52_52/account/face/611203/76c02248/myface.png", "GERM", "第一次看 看弹幕那些说什么影帝模式啥的 感觉日了狗了 让我怎么往后看啊 艹"));
                arr.add(new Person("http://i2.hdslb.com/52_52/user/46230/4623018/myface.jpg", "じ★ve↘魅惑", "开头吾王裙子被撩起来怎么回事！→_→"));
                arr.add(new Person("http://i2.hdslb.com/52_52/user/66723/6672394/myface.jpg", "道尘一梦", "@伪 · 卫宫士郎"));
                //刷新
                if (false) {
                    adapter.pauseMore();
                    return;
                }
                page++;
                adapter.addAll(arr);
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        page = 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Person> arr = new ArrayList<>();
                final int ipage = page;
                arr.add(new Person("http://i2.hdslb.com/52_52/user/61175/6117592/myface.jpg", "月の星く雪" + "————————第" + ipage + "页", "完结来补"));
                arr.add(new Person("http://i1.hdslb.com/52_52/user/6738/673856/myface.jpg", "影·蓝玉", "一看评论被***了一脸，伐开心。"));
                arr.add(new Person("http://i0.hdslb.com/52_52/account/face/6247858/e779259d/myface.png", "i琳夏i", "(｀・ω・´)"));
                arr.add(new Person("http://i0.hdslb.com/52_52/user/18494/1849483/myface.jpg", "Minerva。", "为啥下载不能了？π_π"));
                arr.add(new Person("http://i0.hdslb.com/52_52/account/face/4613528/303f4f5a/myface.png", "如歌行极", "求生肉（/TДT)/"));
                arr.add(new Person("http://i0.hdslb.com/52_52/account/face/611203/76c02248/myface.png", "GERM", "第一次看 看弹幕那些说什么影帝模式啥的 感觉日了狗了 让我怎么往后看啊 艹"));
                arr.add(new Person("http://i2.hdslb.com/52_52/user/46230/4623018/myface.jpg", "じ★ve↘魅惑", "开头吾王裙子被撩起来怎么回事！→_→"));
                arr.add(new Person("http://i2.hdslb.com/52_52/user/66723/6672394/myface.jpg", "道尘一梦", "@伪 · 卫宫士郎"));

                adapter.clear();
                //刷新
                if (false) {
                    adapter.pauseMore();
                    return;
                }
                page = 1;
                adapter.addAll(arr);
            }
        }, 1000);
    }


    private class TestAdapter extends DynamicPagerAdapter {
        private int[] imgs = {
                R.drawable.img1,
                R.drawable.img2,
                R.drawable.img3,
                R.drawable.img4,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
