package com.example.user.mydemo.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.mydemo.R;

/**
 * Created by whq on 17/7/6 0006.
 */

public class StringUtils {
    /**
     * 判断不为空
     *
     * @param text
     * @return
     */
    public static boolean isNullOrEmpty(String text) {
        if (text == null || "".equals(text.trim()) || text.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 关闭软键盘
     */

    public static void hintSoftKeyboard(Activity context) {
        View view = context.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 是否点击的是EditText
     */
    public static boolean isShouldHideInput(View view, MotionEvent event) {
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
     * 普通toast
     */
    public static void toast(Context mContext, String mString){
        String oldMsg = null;
        Toast toast = null;
        long firstTime = 0;
        long secondTime =0;
        if(toast == null){
            toast = Toast.makeText(mContext, mString, Toast.LENGTH_SHORT);
            toast.show();
            firstTime = System.currentTimeMillis();
        }else{
            secondTime = System.currentTimeMillis();
            if(mString.equals(oldMsg)){
                if(secondTime - firstTime > Toast.LENGTH_SHORT){
                    toast.show();
                }
            }else{
                oldMsg = mString;
                toast.setText(mString);
                toast.show();
            }
        }
        firstTime = secondTime;
    }


    /**
     * 屏幕居中Toast
     */
    private static TextView mTextView;
    private static ImageView mImageView;
    public static void centerToast(Context mContext, String mMsg){
        //加载Toast布局
        View toRoot = LayoutInflater.from(mContext).inflate(R.layout.activity_toast, null);
        //初始化布局控件
        mTextView = (TextView) toRoot.findViewById(R.id.tv_hf_toast);
        mImageView = (ImageView) toRoot.findViewById(R.id.iv_hf_toast);
        mTextView.setText(mMsg);
        mImageView.setImageResource(R.drawable.hf_coupon_success);
        //Toast初始化
        Toast toStart = new Toast(mContext);
        //获取屏幕高度
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        //Toast的Y坐标是屏幕高度的1/3
        toStart.setGravity(Gravity.TOP, 0, height/2);
        toStart.setDuration(Toast.LENGTH_LONG);
        toStart.setView(toRoot);
        toStart.show();
    }
}
