/**
 * 
 */
package life.bean.com.beanlife.gesture;

import android.content.Context;
import android.view.WindowManager;

public class AppUtil {
    
	/**
     * 获取屏幕分辨�?
     * @param context
     * @return
     */
    public static int[] getScreenDispaly(Context context) {
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		@SuppressWarnings("deprecation")
		int width = windowManager.getDefaultDisplay().getWidth();
		@SuppressWarnings("deprecation")
		int height = windowManager.getDefaultDisplay().getHeight();
		int result[] = { width, height };
		return result;
	}
    
}
