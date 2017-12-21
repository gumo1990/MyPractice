package com.example.user.mydemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.user.mydemo.BaseActivity;
import com.example.user.mydemo.R;
import com.example.user.mydemo.views.CountdownView;

import butterknife.OnClick;

/**
 * Created by whq on 2017/9/29.
 * 计时器页面
 */

public class SixActivity extends BaseActivity implements View.OnClickListener,CountdownView.OnCountdownEndListener{

    CountdownView cv_countdownViewTest2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_six,1);
        initView();
    }

    private void initView() {
        setTitle("SixActivity");
        cv_countdownViewTest2 = (CountdownView) findViewById(R.id.cv_countdownViewTest2);
        cv_countdownViewTest2.setTag("test1");
        cv_countdownViewTest2.setOnCountdownEndListener(this);
        long time = 10 * 1000;
        cv_countdownViewTest2.start(time);
    }



    @OnClick({R.id.btn_top_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_top_back:
                finish();
                break;
            default:
                break;
        }

    }

    @Override
    public void onEnd(CountdownView cv) {
        Log.d("whq","SSSSSS");
    }
}
