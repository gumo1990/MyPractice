package com.example.user.mydemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by whq on 17/6/26 0026.
 * 地震查看器
 */

public class ThreeActivity extends Activity {
    Button btn_three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        initView();
        initData();
    }

    private void initView() {
        btn_three = (Button) findViewById(R.id.btn_three);
    }

    private void initData(){
        SharedPreferences sharedPreferences = getSharedPreferences("hh",Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("first", false);
        editor.apply();
    }
}
