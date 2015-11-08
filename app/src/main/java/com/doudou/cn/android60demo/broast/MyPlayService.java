package com.doudou.cn.android60demo.broast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by jinliang on 15/11/5.
 */
public class MyPlayService  extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyPlayService:","oncreate--Method:");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("MyPlayService:", "onStartMethod ");
    }

    /**
     * 含有intent 传至的内容 ；
     * 接受 startService 传来的数据信息
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("MyPlayService:", "onstartCommandMethod ");
        return super.onStartCommand(intent, flags, startId);

    }

    /**
     * 销毁service实力的内容
     *
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyPlayService","onDestoryMethod");
    }
}
