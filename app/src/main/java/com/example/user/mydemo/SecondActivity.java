package com.example.user.mydemo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Fragments.MyAddFragment;
import Fragments.MyDelateFragment;

/**
 * Created by whq on 17/6/19 0019.
 */

public class SecondActivity extends Activity {
    FragmentManager manager;
    FragmentTransaction transaction;
    Button btn_second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initData();
    }

    private void initView() {
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        btn_second = (Button) findViewById(R.id.btn_second);
        btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, ThreeActivity.class));
            }
        });

    }

    private void initData() {
        //添加Fragment
        transaction.add(R.id.fl_second1, new MyAddFragment());
        //替换Fragment
        transaction.replace(R.id.fl_second2, new MyDelateFragment());
        transaction.commit();


    }

}
