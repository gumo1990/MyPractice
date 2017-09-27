package com.example.user.mydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.mydemo.utils.StringUtils;

/**
 * Created by whq on 2017/9/27.
 */

public class BaseLayout extends RelativeLayout {

    private Context mcontext;
    private View header_bar;
    private TextView tv_top_title, tv_top_right;
    private TextView btn_top_back;

    public BaseLayout(Context context) {
        super(context);

    }

    public BaseLayout(Context context, int layoutResourceId) {
        super(context);
    }

    public BaseLayout(Context context, int layoutResourceId, String type) {
        super(context);
        mcontext = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setHeaderBar(layoutInflater);

        View view = layoutInflater.inflate(layoutResourceId, null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        params.addRule(RelativeLayout.BELOW, R.id.header_bar);
        addView(view, params);


    }

    protected void setHeaderBar(LayoutInflater layoutInflater) {
        header_bar = layoutInflater.inflate(R.layout.header_bar, null);
        tv_top_title = (TextView) header_bar.findViewById(R.id.tv_top_title);
        btn_top_back = (TextView) header_bar.findViewById(R.id.btn_top_back);
        tv_top_right = (TextView) header_bar.findViewById(R.id.tv_top_right);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        addView(header_bar, params);
    }
    public void setTitle(String ss) {
        tv_top_title.setText(ss);
    }

    public void hidBack(){
        btn_top_back.setVisibility(GONE);
    }

    public void hidNext(){
        tv_top_right.setVisibility(GONE);
    }

}
