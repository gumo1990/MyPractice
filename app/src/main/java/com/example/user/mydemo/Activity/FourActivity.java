package com.example.user.mydemo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.mydemo.Adapters.TextAdapter;
import com.example.user.mydemo.Beans.Fruits;
import com.example.user.mydemo.R;

import java.util.ArrayList;
import java.util.List;

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
