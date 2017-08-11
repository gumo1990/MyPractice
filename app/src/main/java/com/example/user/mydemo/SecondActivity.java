package com.example.user.mydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

import Utils.ListScrollView;

/**
 * Created by whq on 17/6/19 0019.
 * Scrollview嵌套Listview只显示一行，两种办法，
 * 一种自定义listview，重写onMeasure方法，(listview充满屏幕)
 * 第二种listview写死高度(会产生滑动冲突--listview无法滑动)
 */

public class SecondActivity extends Activity {

    Button btn_second;
    ListScrollView listView;
    ListScrollView lv_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initData();
    }

    private void initView() {

        btn_second = (Button) findViewById(R.id.btn_second);
        btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, ThreeActivity.class));
            }
        });
        listView = (ListScrollView) findViewById(R.id.lv_second);
        lv_ll = (ListScrollView) findViewById(R.id.lv_ll);

    }

    private void initData() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("" + i);
        }
        ArrayAdapter adapter = new ArrayAdapter(SecondActivity.this,
                R.layout.support_simple_spinner_dropdown_item, strings);

        listView.setAdapter(adapter);
        lv_ll.setAdapter(adapter);

    }


}
