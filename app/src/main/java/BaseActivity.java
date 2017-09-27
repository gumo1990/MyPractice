import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by whq on 2017/9/27.
 */

public abstract class BaseActivity extends Activity {

    protected abstract int setView();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void registListener();

    protected Context mContext;

    private Unbinder unbinder;

    public BaseActivity() {
        this.mContext = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setView());
        unbinder = ButterKnife.bind(this);//绑定注解
        initView();
        initData();
        registListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();//取消绑定
    }
}
