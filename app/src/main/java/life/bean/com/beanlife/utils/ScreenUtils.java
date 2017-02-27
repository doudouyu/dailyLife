package life.bean.com.beanlife.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 作者 : bean on 2017/2/21/0021.
 * 注释 :
 */
public class ScreenUtils {

    private static DisplayMetrics dm2;

    public static int getScreenWidth(Context context) {
        dm2 = context.getResources().getDisplayMetrics();
        return dm2.widthPixels;
    }
    public static int getScreenHeight(Context context) {
        dm2 = context.getResources().getDisplayMetrics();
        return dm2.heightPixels;
    }
}
