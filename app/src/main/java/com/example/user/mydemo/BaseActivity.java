package com.example.user.mydemo;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.user.mydemo.utils.ScreenUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by whq on 2017/9/27.
 */

public class BaseActivity extends FragmentActivity {

    protected Context mContext;

    protected Unbinder unbinder;

    protected BaseLayout baseLayout;


    public BaseActivity() {
        this.mContext = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setView(int layoutId, int type) {
        switch (type) {
            case 0:
                //没有头
                setContentView(layoutId);
                break;
            case 1:
                //有头布局
                baseLayout = new BaseLayout(this, layoutId, "1");
                setContentView(baseLayout);
                break;
            default:
                break;
        }
        unbinder = ButterKnife.bind(this);//绑定注解

    }

    /**
     * 设置标题
     * @param s
     */
    protected void setTitle(String s) {
        baseLayout.setTitle(s);
    }

    /**
     * 隐藏返回按钮
     * @param type
     */
    protected void hideBack(boolean type){
        if(type){
            baseLayout.hidBack();
        }
    }

    /**
     * 隐藏下一页
     */
    protected void hidNext(boolean type){
        if(type){
            baseLayout.hidBack();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!isFinishing()){
            Log.d("whq","-------------");
            unbinder.unbind();//取消绑定
        }else{
            Log.d("whq","111111");
        }
    }

    /**
     * 设置沉浸式样式
     * type=ture---有标题栏
     * @param view
     */
    protected void setImmerseLayout(View view, boolean type) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
              /*  window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);*/
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            if(type){
                int statusBarHeight = ScreenUtil.getStatusBarHeight(this.getBaseContext());

                ViewGroup.LayoutParams lp=(ViewGroup.LayoutParams)view.getLayoutParams();
                lp.height+=statusBarHeight;
                view.setLayoutParams(lp);
                view.setPadding(0, statusBarHeight, 0, 0);//为了控件中的子控件位置不偏移
            }

        }
    }
}
