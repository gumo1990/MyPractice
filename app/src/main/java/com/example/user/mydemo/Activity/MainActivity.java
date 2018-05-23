package com.example.user.mydemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.mydemo.BaseActivity;
import com.example.user.mydemo.MyApplication;
import com.example.user.mydemo.R;
import com.example.user.mydemo.adapters.MainAdapter;
import com.example.user.mydemo.utils.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.OnClick;

/**
 * EditText添加到一个listView中
 * 1.Radiobutton选中事件
 * 2.点击EditText其他位置，软键盘隐藏
 * 3.将EditText内容添加到listview
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    EditText et_main;
    ListView lv_main;
    ArrayList<String> todoItems = new ArrayList<>();
    ArrayAdapter<String> aa;
    RecyclerView rv_main;
    /**
     * compile 'com.jakewharton:butterknife:8.5.1'
     * annotationProcessor 'com.jakewharton:butterknife-compiler:8.5.1'--添加注解
     * 在onCreate中先绑定
     * unbinder = ButterKnife.bind(this);
     * 销毁时候要解绑
     * unbinder.unbind();
     */

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    ArrayList<String> all = new ArrayList<>();
    ArrayList<Integer> ain = new ArrayList<>();
    ArrayList<String> mList = new ArrayList<>();
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_main, 1);//0-无头，1-有头
        if (savedInstanceState != null) {
            Log.d("whq", "onCreate: " + savedInstanceState.getString("data_key"));
        }
        all.add("hhhhh");
        all.add("lll");
        all.add("mmmm");
        ain.add(3);
        ain.add(13);
        ain.add(223);
        printlns(all);
        printlns(ain);
        initView();
        initData();
        onClickListener();
    }

    private  void printlns(ArrayList<?> obj) {
        Iterator<?> iterator = obj.iterator();
        while (iterator.hasNext()) {
            Log.d("whq", "=====obj===" + iterator.next());
        }
    }

    private void initView() {
        et_main = (EditText) findViewById(R.id.et_main);
        lv_main = (ListView) findViewById(R.id.lv_main);
        rv_main = (RecyclerView) findViewById(R.id.rv_main);
        aa = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, todoItems);
    }

    private void initData() {
        setTitle("MainActivity");
        hideBack(true);
        lv_main.setAdapter(aa);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_main.setLayoutManager(linearLayoutManager);
        rv_main.setItemAnimator(new DefaultItemAnimator());
        for(int i=1; i< 50;i++){
            mList.add(i+"");
        }
        mainAdapter = new MainAdapter(this, mList);
        rv_main.setAdapter(mainAdapter);
    }

    private void onClickListener() {
        et_main.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER) {
                        if (!StringUtils.isNullOrEmpty(et_main.getText().toString().trim())) {
                            todoItems.add(0, et_main.getText().toString());
                        }
                        aa.notifyDataSetChanged();
                        et_main.setText("");
                        return true;
                    }
                }

                return false;
            }
        });
        mainAdapter.setOnItemClickListener(new MainAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, "点击第"+(position+1)+"条", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, final int position) {
                new AlertDialog.Builder(mContext).setTitle("确认删除第"+(position +1)+"条吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确认" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainAdapter.removeData(position);
                    }
                }).show();
            }
        });

    }

    @OnClick({R.id.rb_one, R.id.tv_top_right, R.id.rb_second, R.id.rb_three, R.id.rb_four})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_one:


                StringUtils.toast(mContext, "跳转第四页");
                startActivity(new Intent(mContext, FourActivity.class));
                break;
            case R.id.rb_second:
                startActivity(new Intent(mContext, FiveActivity.class));
                StringUtils.centerToast(mContext, "跳转第五页");
                break;
            case R.id.rb_three:
                startActivity(new Intent(mContext, SixActivity.class));
                StringUtils.centerToast(mContext, "跳转第六页");
                break;
            case R.id.tv_top_right:
                startActivity(new Intent(mContext, SecondActivity.class));
                break;
            case R.id.rb_four:
                startActivity(new Intent(mContext, EightActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 点击其他地方隐藏软键盘
     *
     * @param
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (StringUtils.isShouldHideInput(view, ev)) {
                //是否点击的是EditText。返回true，非EditText
                StringUtils.hintSoftKeyboard(MainActivity.this);
            }
            return super.dispatchTouchEvent(ev);
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


    /**
     * 物理返回按钮点击事件
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //   Toast.makeText(mContext, "00000000000", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(mContext, R.string.text_exit, Toast.LENGTH_SHORT).show();
            MyApplication.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);

        } else {
            finish();
        }
    }

    /**
     * 保存临时数据
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String s = "22";
        outState.putString("data_key", s);
    }

}
