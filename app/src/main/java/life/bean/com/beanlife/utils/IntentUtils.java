package life.bean.com.beanlife.utils;

import android.content.Context;
import android.content.Intent;

import life.bean.com.beanlife.MyApplication;
import life.bean.com.beanlife.activity.BaseActivity;
import life.bean.com.beanlife.activity.CalendarActivity;

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
    public static void startActivity(Class<?> clazz,Intent intent) {
        context = BaseActivity.getContextInstance();
        if (context != null&& intent!=null) {
            context.startActivity(intent);
        }
    }

    public static void startActivity(Class<CalendarActivity> clazz, String titleName, String i) {
        context = BaseActivity.getContextInstance();
        if (context != null) {
            Intent intent = new Intent(context, clazz);
            intent.putExtra(titleName,i);
            context.startActivity(intent);
        }
    }
}
