package com.example.user.mydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * EditText添加到一个listView中
 */
public class MainActivity extends Activity {

    Button btn_main;
    EditText et_main;
    ListView lv_main;
    ArrayList<String> todoItems = new ArrayList<>();
    ArrayAdapter<String> aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        onClickListener();
    }

    private void initView() {
        btn_main = (Button) findViewById(R.id.btn_main);
        et_main = (EditText) findViewById(R.id.et_main);
        lv_main = (ListView) findViewById(R.id.lv_main);
        aa = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,todoItems);
    }
    private void initData(){
        lv_main.setAdapter(aa);
    }
    private void onClickListener(){
        et_main.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER){
                        todoItems.add(0, et_main.getText().toString());
                        aa.notifyDataSetChanged();
                        et_main.setText("");
                        return true;
                    }
                }

                return false;
            }
        });
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }
}
