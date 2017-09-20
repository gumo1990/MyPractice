package com.example.user.mydemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import Adapters.TextAdapter;
import Beans.Fruits;

/**
 * Created by whq on 2017/8/15.
 */

public class FourActivity extends Activity {

    private List<Fruits> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        initData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.ry_four);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        TextAdapter adapter = new TextAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        for(int i=0; i<200; i++){
            list.add(new Fruits("苹果"+i));
        }
    }
}
