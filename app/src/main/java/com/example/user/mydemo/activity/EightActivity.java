package com.example.user.mydemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.user.mydemo.BaseActivity;
import com.example.user.mydemo.R;

import butterknife.BindView;

/**
 * Created by whq on 2017/12/25.
 * 设置Activity的沉浸式
 */

public class EightActivity extends BaseActivity {
    @BindView(R.id.tv_eight)
    TextView tv_eight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_eight, 0);
    }
}
