package Utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by whq on 17/7/6 0006.
 */

public class StringUtils {
    /**
     * 判断不为空
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

}
