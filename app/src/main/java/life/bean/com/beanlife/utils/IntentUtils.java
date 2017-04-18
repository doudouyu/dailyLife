package life.bean.com.beanlife.utils;

import android.content.Context;
import android.content.Intent;

import life.bean.com.beanlife.MyApplication;
import life.bean.com.beanlife.activity.BaseActivity;

/**
 * 作者 : bean on 2017/4/18/0018.
 * 注释 :
 */
public class IntentUtils {
    public static Context context;

    public static void startActivity(Class<?> clazz) {
        context = BaseActivity.getContextInstance();
        if (context != null) {
            Intent intent = new Intent(context, clazz);
            context.startActivity(intent);
        }
    }

}
