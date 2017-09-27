package com.example.user.mydemo.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.mydemo.R;
import com.example.user.mydemo.Utils.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * EditText添加到一个listView中
 */
public class MainActivity extends Activity {
    EditText et_main;
    ListView lv_main;
    ArrayList<String> todoItems = new ArrayList<>();
    ArrayAdapter<String> aa;
    private Unbinder  unbinder;
    /**
     * compile 'com.jakewharton:butterknife:8.5.1'
     * annotationProcessor 'com.jakewharton:butterknife-compiler:8.5.1'--添加注解
     * 在onCreate中先绑定
     * unbinder = ButterKnife.bind(this);
     * 销毁时候要解绑
     * unbinder.unbind();
     */
    @BindView(R.id.btn_main)
    Button btn_main;

    @BindView(R.id.tv_main)
    TextView tv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        if(savedInstanceState != null){
            Log.d("whq", "onCreate: "+savedInstanceState.getString("data_key"));
        }
        initView();
        initData();
        onClickListener();
    }

    private void initView() {
        et_main = (EditText) findViewById(R.id.et_main);
        lv_main = (ListView) findViewById(R.id.lv_main);
        aa = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, todoItems);
        tv_main.setText("1111");
    }

    private void initData() {
        lv_main.setAdapter(aa);
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
        /*btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });*/
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
            if (isShouldHideInput(view, ev)) {
                //返回true，非EditText
                hintSoftKeyboard();
            }
            return super.dispatchTouchEvent(ev);
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    /**
     * 关闭软键盘
     */

    public void hintSoftKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 是否点击的是EditText
     */
    private boolean isShouldHideInput(View view, MotionEvent event) {
        if (view != null && (view instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location
            view.getLocationInWindow(leftTop);
            ;
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + view.getHeight();
            int right = left + view.getWidth();
            if (event.getX() > left && event.getX() < right && event.getY() > top
                    && event.getY() < bottom) {
                //点击的是输入框，保留EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 物理返回按钮点击事件
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * 保存临时数据
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String s = "11111";
        outState.putString("data_key",s);
    }
}
