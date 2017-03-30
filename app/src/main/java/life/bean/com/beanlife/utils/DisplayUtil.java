package life.bean.com.beanlife.utils;

import android.content.Context;

/**
 * 作者 : bean on 2017/3/29/0029.
 * 注释 :
 */
public class DisplayUtil {
    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }
    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

}
