package com.example.user.mydemo.views;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by whq on 17/6/21 0021.
 */

public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredHeight = measureHeight(heightMeasureSpec) ;
        int measureWidth = measureWidth(widthMeasureSpec);

        setMeasuredDimension(measuredHeight,measureWidth);
    }

    /**
     * 占据的高度值
     */
    private int measureHeight(int measureSpec){

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //如果不指定限制，就是默认大小
        int result = 500;
        if(specMode == MeasureSpec.AT_MOST){
            //计算控件在这个最大尺寸范围内的理想大小,即在父控件中占据的大小
            //如果控件填充了可用空间，则返回外边界
            result = specSize;
        } else if(specMode == MeasureSpec.EXACTLY){
            //如果控件可以放置在这个边界内，则返回该值，即本身自己确定占据大小
            result = specSize;
        }
        return result;
    }
    /**
     * 占据的宽度值
     */
    private int measureWidth(int measureSpec){
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //如果不指定就是默认大小
        int result = 500;
        if(specMode == MeasureSpec.AT_MOST){//UNSPECIFIED--没有指定边界，可以自己设置大小
            //占据父控件
            result = specSize;
        }else if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }
        return result;
    }
}
