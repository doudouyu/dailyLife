package life.bean.com.beanlife.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import life.bean.com.beanlife.utils.ScreenUtils;

/**
 * 作者 : bean on 2017/2/21/0021.
 * 注释 :
 */
public class SlideView extends HorizontalScrollView {
    private  Context context;
    private boolean isOpen ;
    private int menuWidth ;
    private boolean isOnce ;
    private int mScreenWidth;
    private int halfScreenWidth;
    private int mMenuPaddingRight = 100;

    public SlideView(Context context) {
        super(context);
        this.context = context;
        mScreenWidth = ScreenUtils.getScreenWidth(context);
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mScreenWidth = ScreenUtils.getScreenWidth(context);
    }

    public SlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        mScreenWidth = ScreenUtils.getScreenWidth(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!isOnce) {
            LinearLayout layout = (LinearLayout) getChildAt(0);
            ViewGroup menu = (ViewGroup) layout.getChildAt(0);
            ViewGroup common = (ViewGroup) layout.getChildAt(1);
            mMenuPaddingRight = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, mMenuPaddingRight, context
                            .getResources().getDisplayMetrics());
            halfScreenWidth = mScreenWidth/2;
            menuWidth = mScreenWidth-mMenuPaddingRight;
            menu.getLayoutParams().width = menuWidth;
            common.getLayoutParams().width = mScreenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.smoothScrollTo(menuWidth,0);
            isOnce = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:
                int x = getScrollX();
                if (x>halfScreenWidth)
                    this.smoothScrollTo(menuWidth,0);
                else
                    this.smoothScrollTo(0,0);
                return true;
        }

        return super.onTouchEvent(ev);

    }

    private void toggle() {
        if (isOpen) {
            openMenu();
        } else {
            closeMenu();
        }
    }

    /**
     * 关闭菜单
     */
    private void closeMenu() {
        if (isOpen) {
            this.smoothScrollTo(menuWidth, 0);
            isOpen = false;
        }
    }

    /**
     * 打开菜单
     */
    private void openMenu() {
        if (isOpen) {
            return;
        } else {
            this.smoothScrollTo(0, 0);
            isOpen = true;
        }
    }
}
