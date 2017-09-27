package com.example.user.mydemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.user.mydemo.BaseActivity;
import com.example.user.mydemo.R;

import butterknife.OnClick;

/**
 * Created by whq on 2017/8/15.
 */

public class FourActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_four, 1);
        initView();
        initData();
        registListener();

    }

    private void initView() {
        setTitle("哈哈");
    }

    private void initData() {

    }

    private void registListener() {

    }

    @OnClick({R.id.tv_four, R.id.btn_top_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_four:
                Toast.makeText(mContext, "hhhhhhhhh", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_top_back:
                finish();
                break;
            default:
                break;
        }
    }


}
