package com.doudou.cn.android60demo.doudou;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;

import com.doudou.cn.android60demo.R;
import com.doudou.cn.android60demo.testEasyRecyClerView.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jinliang on 15/11/8.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class TextHashMap extends Activity {

    private Map<String,String> map = new HashMap<>();
    private  Map<String,String> map2 = new ArrayMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_empty);

//      long start  = System.currentTimeMillis();
//        for(int i=0;i<50;i++){
//            map.put(""+i,""+i);
//
//        }
//        map.get(30);
//        Log.i("第一次消耗的时间:", "" + String.valueOf(System.currentTimeMillis() - start));
//
//        long start2  = System.currentTimeMillis();
//        for(int i=0;i<50;i++){
//            map2.put(""+i,""+i);
//
//        }
//        map2.get(30);
//        Log.i("第er次消耗的时间:",""+String.valueOf(System.currentTimeMillis()-start2));

        Student student = new Student();
        student.age =  20+"";

    }
}

