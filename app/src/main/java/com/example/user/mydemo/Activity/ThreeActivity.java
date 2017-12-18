package com.example.user.mydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.user.mydemo.BaseActivity;
import com.example.user.mydemo.R;
import com.example.user.mydemo.utils.MyListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by whq on 17/6/26 0026.
 * ScrollView嵌套Listview（高度写死）,导致滑动冲突的解决
 * 第一种，给listview设置监听事件，滑动时候父层不拦截
 * 第二种，自定义scrollview，不拦截事件--最外层是自定义ScrollView
 * 第三种，自定义listview设置不让父控件拦截
 */

public class ThreeActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.lv_three)
    MyListView lv_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_three, 1);
        initView();
        initData();
    }

    private void initView() {


    }

    private void initData() {
        setTitle("ThreeActivity");
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

    @OnClick({R.id.btn_top_back, R.id.tv_top_right})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_top_back:
                finish();
                break;
            case R.id.tv_top_right:
                startActivity(new Intent(ThreeActivity.this, FourActivity.class));
                break;
            default:
                break;
        }
    }
}
