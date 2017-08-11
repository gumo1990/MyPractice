package Utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by whq on 2017/8/10.
 */

public class ListScrollView extends ListView {

    public ListScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListScrollView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
