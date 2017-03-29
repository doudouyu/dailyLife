package life.bean.com.beanlife.utils;

import android.net.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 作者 : bean on 2017/3/29/0029.
 * 注释 :
 */
public class DateUtils {
    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss-EEEE");
        String str = format.format(new Date(System.currentTimeMillis()));
        return str;
    }
    /**
     * 转换时间格式
     *
     * @return
     */
    public static String getHourTime(long l) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(l);
        return str;
    }
    public static String getWeek(String pTime) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar c = Calendar.getInstance();
            try {
                c.setTime(format.parse(pTime));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            Week += "日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 2) {
            Week += "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 3) {
            Week += "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 4) {
            Week += "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 5) {
            Week += "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 6) {
            Week += "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == 7) {
            Week += "六";
        }



        return Week;
    }


}
