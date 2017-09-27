package com.example.user.mydemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.mydemo.R;
import com.example.user.mydemo.adapters.FiveAdapter;

import java.util.ArrayList;

/**
 * Created by whq on 2017/8/30.
 */


public class FiveActivity extends Activity {
    RecyclerView rcv_fivie;
    Button btn_select, btn_delect;
    TextView tv_select;
    ArrayList<String> listdata = new ArrayList<>();
    FiveAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        initview();
        initData();
        registListener();

    }

    private void initview() {
        rcv_fivie = (RecyclerView) findViewById(R.id.rcv_fivie);
        tv_select = (TextView) findViewById(R.id.tv_select);//已选XX个
        btn_select = (Button) findViewById(R.id.btn_select);//全选
        btn_delect = (Button) findViewById(R.id.btn_delect);//全删
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            listdata.add("" + i);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_fivie.setLayoutManager(manager);
        adapter = new FiveAdapter(this, listdata);
        rcv_fivie.setAdapter(adapter);
    }

    private void registListener() {
        btn_delect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.remove();
                Log.d("whq", "--------activity" + listdata.size());
            }
        });
    }


}
