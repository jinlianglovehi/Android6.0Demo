package com.doudou.cn.android60demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doudou.cn.android60demo.testEasyRecyClerView.Person;
import com.doudou.cn.android60demo.testEasyRecyClerView.PersonAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jinliang on 15/11/7.
 */
public class EasyRecyClerView extends Activity implements
        RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener

{
    private static  final String TAG = EasyRecyClerView.class.getSimpleName();
    @Bind(R.id.recyclerView2)
    EasyRecyclerView recyclerView2;
    private Handler handler = new Handler();

    private int page = 0;
    private PersonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layou);
//        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setContentView(R.layout.activity_easyrecyclerview);
        ButterKnife.bind(this);
        //recyclerView2.add
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapterWithProgress(adapter = new PersonAdapter(this));
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return LayoutInflater.from(parent.getContext()).inflate(R.layout.view_error,null);
            }

            @Override
            public void onBindView(View headerView) {

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

        Log.i("onrefresh","onrefresh");
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
}
