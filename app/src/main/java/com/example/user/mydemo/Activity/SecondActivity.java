package com.example.user.mydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.user.mydemo.BaseActivity;
import com.example.user.mydemo.R;
import com.example.user.mydemo.utils.ListScrollView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by whq on 17/6/19 0019.
 * Scrollview嵌套Listview只显示一行，两种办法，
 * 一种自定义listview，重写onMeasure方法，(listview充满屏幕)
 * 第二种listview写死高度(会产生滑动冲突--listview无法滑动)
 */

public class SecondActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.lv_second)
    ListScrollView lv_second;
    @BindView(R.id.lv_ll)
    ListScrollView lv_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_second, 1);
        initView();
        initData();
    }

    private void initView() {
        lv_second = (ListScrollView) findViewById(R.id.lv_second);
        lv_ll = (ListScrollView) findViewById(R.id.lv_ll);

    }

    private void initData() {
        setTitle("SecondActivity");
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("" + i);
        }
        ArrayAdapter adapter = new ArrayAdapter(SecondActivity.this,
                R.layout.support_simple_spinner_dropdown_item, strings);

        lv_second.setAdapter(adapter);
        lv_ll.setAdapter(adapter);

    }

    @OnClick({R.id.btn_top_back, R.id.tv_top_right})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_top_back:
                finish();
                break;
            case R.id.tv_top_right:
                startActivity(new Intent(mContext, ThreeActivity.class));
                break;
            default:
                break;
        }
    }
}
