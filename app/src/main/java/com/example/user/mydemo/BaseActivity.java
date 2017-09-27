package com.example.user.mydemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by whq on 2017/9/27.
 */

public class BaseActivity extends Activity {

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
}
