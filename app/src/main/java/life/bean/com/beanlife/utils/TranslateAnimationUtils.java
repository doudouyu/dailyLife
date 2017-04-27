package life.bean.com.beanlife.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * 作者 : bean on 2017/4/27/0027.
 * 注释 :
 */
public class TranslateAnimationUtils {
    public static void initRightAnim(int fromX,int toX, View indicatorLine) {
        Animation translateIn = new TranslateAnimation(fromX, toX, 0, 0);
        translateIn.setDuration(100);
        translateIn.setFillAfter(true);
        indicatorLine.startAnimation(translateIn);
    }
}
