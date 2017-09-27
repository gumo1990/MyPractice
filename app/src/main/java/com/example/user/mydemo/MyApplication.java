package com.example.user.mydemo;

import android.app.Application;
import android.os.Handler;

/**
 * Created by whq on 2017/9/27.
 * 全局变量
 */

public class MyApplication extends Application {

    private static Handler handler;
    public static MyApplication mApp;

    public MyApplication() {
        mApp = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
    }

    public static Handler getHandler() {
        return handler;
    }
}
