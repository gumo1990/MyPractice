package com.example.user.mydemo.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.mydemo.BaseActivity;
import com.example.user.mydemo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by whq on 2017/12/05.
 * 1.属性动画，objectAnimation
 * 2.Glide图片加载
 * 引用依赖包compile 'com.github.bumptech.glide:glide:4.0.0-RC0'
 * 3.handler使用，防止内存泄露所以从新继承,注意弱引用
 * 4.使用url读取网页内容
 */


public class FiveActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_five2)
    TextView tv_five2;
    @BindView(R.id.tv_five)
    TextView tv_five;
    @BindView(R.id.five_image)
    ImageView five_image;

    private MyHandler mhandler = new MyHandler(mContext);

    private static  class MyHandler extends Handler{
        private WeakReference<FiveActivity> fv;
        public MyHandler(Context context) {
            fv  = new WeakReference<FiveActivity>((FiveActivity) context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FiveActivity fiveActivity = fv.get();
            switch (msg.what){
                case 1:
                    if(fiveActivity != null)
                        fiveActivity.tv_five2.setBackgroundResource(R.drawable.btn_back_n);
                    break;
                case 2:

                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        unbinder = ButterKnife.bind(this);//绑定注解
        setImmerseLayout(findViewById(R.id.ic_bar),true);
        initView();

    }

    private void initView() {
        final ValueAnimator anim = ValueAnimator.ofFloat(0f, 1f);
        //   final ValueAnimator anim = ValueAnimator.ofInt(0, 100);
        anim.setDuration(300);
        anim.setRepeatCount(3);//播放次数
        //   anim.setStartDelay(5000);//设置动画延迟播放的时间
        anim.setRepeatMode(ValueAnimator.RESTART);//重新播放和倒序播放的意思
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
             //   Log.d("whq", "-----ainn---" + anim.getAnimatedValue());
            }
        });
        anim.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                message.obj = "hhhfive";
                mhandler.sendMessage(message);

                try {
                    URL url = new URL("https://www.baidu.com");
                    Log.d("whq", "======="+url);
                    InputStream is = url.openStream();//通过openStream方法获取资源的字节输入流
                    //将字节输入流转换为字符输入流
                    InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                    //字符输入流添加缓冲,提高读取效率
                    BufferedReader br = new BufferedReader(isr);
                    //读取数据
                    String data = br.readLine();
                    while (data != null){
                        Log.d("whq", "======="+data);
                        data = br.readLine();
                    }
                    br.close();
                    isr.close();
                    is.close();

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("whq", "===eee===="+e);
                }
            }
        }).start();


    }
    public void loadIamge(View view){
        String url = "http://www.svgouwu.com//data//files//store_17127//goods_177//201712011056178191.jpg";
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.btn_back_n);//默认图片
        requestOptions.error(R.drawable.ic_logo);//网络加载错误出现的图片
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);//去除缓存
        requestOptions.override(100,100);//固定写死图片大小
    //    Glide.with(this).setDefaultRequestOptions(requestOptions).load(url).into(five_image);
        Glide.with(this).load(url).into(five_image);

    }

    @OnClick({R.id.tv_five, R.id.tv_five2})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_five:
                //   float x = tv_five.getTextScaleX();
                //ObjectAnimator继承了ValueAnimator，
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv_five, "scaleY", 1f, 3f, 1f);
                //  ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv_five, "translationX",x,-500f,x);
                //   ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv_five, "alpha", 1f, 0f, 1f);
                //旋转rotation，0f, 360f
                //向左移出，在移回 float x = tv_five.getTranslationX(); translationX,x,-500f,x
                objectAnimator.setDuration(5000);
                objectAnimator.start();
                break;
            case R.id.tv_five2:
                float x = tv_five.getTextScaleX();
                ObjectAnimator move = ObjectAnimator.ofFloat(tv_five2, "translationX", x, -500f, x);
                ObjectAnimator rotate = ObjectAnimator.ofFloat(tv_five2, "rotation", 0f, 360f);
                ObjectAnimator fadeInout = ObjectAnimator.ofFloat(tv_five2, "alpha", 1f, 0f, 1f);
                AnimatorSet set = new AnimatorSet();
                set.play(rotate).with(fadeInout).after(move);
                set.setDuration(5000);
                set.start();
                //四个方法太多了
                set.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                set.addListener(new AnimatorListenerAdapter() {
                    //任意选择重写的方法
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                });
                break;
            default:
                break;
        }
    }
}
