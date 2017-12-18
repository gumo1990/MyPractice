package com.example.user.mydemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by whq on 2017/9/28.
 * 重写onTouchEvent事件，防止与外层Scrollview
 * 滑动冲突
 */

public class MyWebView extends WebView {

    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);//父控件不拦截
                break;
            case MotionEvent.ACTION_MOVE:
                boolean scroll = true;
                if(isTop()){//是否滑到顶部
                    scroll =false;
                }else if(isBottom()){
                    scroll = false;
                }
                getParent().requestDisallowInterceptTouchEvent(scroll);
                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean isBottom() {
        float htmlHeight = getContentHeight() * getScale();
        float currentheight = getHeight() + getScrollY();
        return htmlHeight == currentheight;
    }
    private boolean isTop() {
        return getScrollY() == 0;
    }
}
