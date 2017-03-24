package life.bean.com.beanlife.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作者 : bean on 2017/3/17/0017.
 * 注释 :
 */
public class SharepreferenceUtils {
    /**
     *  存储String类型的数据
     * @param context
     * @param name sp文件的名字
     * @param mode sp文件的存储形式
     * @param key 要存的值对应的key
     * @param value 要存的值对应的key
     */
    public static void setSharepreference(Context context,String name,int mode,String key,String value){
        SharedPreferences preferences = context.getSharedPreferences(name,mode);
        preferences.edit().putString(key,value).commit();
    }

    /**
     * 获取String类型的数据
     * @param context
     * @param name sp文件的名字
     * @param mode sp文件的存储形式
     * @param key 要取的值对应的key
     * @return
     */
    public static String getSharepreference(Context context,String name,int mode,String key){
        SharedPreferences preferences = context.getSharedPreferences(name,mode);
        String value = preferences.getString(key,"");
        return value;
    }
    /**
     *  存储Boolean类型的数据
     * @param context
     * @param name sp文件的名字
     * @param mode sp文件的存储形式
     * @param key 要存的值对应的key
     * @param value 要存的值对应的key
     */
    public static void setBooleanSharepreference(Context context,String name,int mode,String key,Boolean value){
        SharedPreferences preferences = context.getSharedPreferences(name,mode);
        preferences.edit().putBoolean(key,value).commit();
    }

    /**
     * 获取Boolean类型的数据
     * @param context
     * @param name sp文件的名字
     * @param mode sp文件的存储形式
     * @param key 要取的值对应的key
     * @return 要取的值对应的value
     */
    public static Boolean getBooleanSharepreference(Context context,String name,int mode,String key){
        SharedPreferences preferences = context.getSharedPreferences(name,mode);
        Boolean value = preferences.getBoolean(key,false);
        return value;
    }
}
