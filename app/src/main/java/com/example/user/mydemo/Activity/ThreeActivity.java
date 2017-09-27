package com.example.user.mydemo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.user.mydemo.R;
import com.example.user.mydemo.Utils.MyListView;

import java.util.ArrayList;

/**
 * Created by whq on 17/6/26 0026.
 * ScrollView嵌套Listview（高度写死）,导致滑动冲突
 * 第一种，给listview设置监听事件，滑动时候父层不拦截
 * 第二种，自定义scrollview，不拦截事件--最外层是自定义ScrollView
 * 第三种，自定义listview设置不让父控件拦截
 */

public class ThreeActivity extends Activity {
    Button btn_three;
    MyListView lv_three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        initView();
        initData();
    }

    private void initView() {
        btn_three = (Button) findViewById(R.id.btn_three);
        lv_three = (MyListView) findViewById(R.id.lv_three);
        btn_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThreeActivity.this,FourActivity.class));
            }
        });
    }

    private void initData() {
       /* SharedPreferences sharedPreferences = getSharedPreferences("hh",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("first", false);
        editor.apply();*/
        ArrayList<String> ss = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            ss.add(i + "");
        }
        ArrayAdapter adapter = new ArrayAdapter(ThreeActivity.this,
                R.layout.support_simple_spinner_dropdown_item, ss);
        lv_three.setAdapter(adapter);
        /*lv_three.setOnTouchListener(new View.OnTouchListener() {
        //listview设置监听事件，阻止父控件拦截
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;

                }
                return false;
            }
        });*/

    }
}
