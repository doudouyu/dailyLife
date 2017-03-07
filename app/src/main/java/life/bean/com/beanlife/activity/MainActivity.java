package life.bean.com.beanlife.activity;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import life.bean.com.beanlife.R;
import life.bean.com.beanlife.adapter.MyPagerAdapter;
import life.bean.com.beanlife.fragment.AccountRingFragment;
import life.bean.com.beanlife.fragment.BankCardFragment;
import life.bean.com.beanlife.fragment.BaseFragment;
import life.bean.com.beanlife.fragment.JointAccountFragment;
import life.bean.com.beanlife.fragment.MenuFragment;
import life.bean.com.beanlife.fragment.MoneyServiceFragment;
import life.bean.com.beanlife.fragment.SuggestionFragment;
import life.bean.com.beanlife.fragment.statisticalAnalysisFragment;
import life.bean.com.beanlife.fragment.DailyLifeFragment;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/21/0021.
 * 注释 :
 */
public class MainActivity extends BaseActivity {

    private DrawerLayout layout;
    private boolean isOpen = false;
    private FrameLayout menu;
    private ViewPager content;
    private int currentPosition;
    private ArrayList<BaseFragment> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ShareSDK.initSDK(context,"1bc0aa65ad7fd");
        layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menu = (FrameLayout) findViewById(R.id.menu);
        content = (ViewPager) findViewById(R.id.content);
        MenuFragment menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu, menuFragment).commit();
        Common.iconId = 1;
        if (currentPosition == 0){
            Common.titleSearchId = 2;
        }else {
            Common.titleSearchId = 1;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Common.iconId = 1;
        if (currentPosition == 0){
            Common.titleSearchId = 2;
        }else {
            Common.titleSearchId = 1;
        }
    }

    @Override
    public void initData() {
        list.add(new DailyLifeFragment());
        list.add(new statisticalAnalysisFragment());
        list.add(new JointAccountFragment());
        list.add(new AccountRingFragment());
        list.add(new BankCardFragment());
        list.add(new MoneyServiceFragment());
        list.add(new SuggestionFragment());
        content.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), list));
        content.setCurrentItem(0);
        setTitleText("日常生活");
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.selected:
                if (isOpen) {
                    layout.closeDrawer(menu);
                    isOpen = false;
                } else {
                    layout.openDrawer(menu);
                    isOpen = true;
                }
                break;

            default:
                break;
        }
    }

    public DrawerLayout getDrawerLayout() {
        return layout;
    }

    public FrameLayout getMenuLayout() {
        return menu;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setCurrentPagerItem(int position) {
        if (currentPosition == 0) {
           titleSearch.setVisibility(View.VISIBLE);
            Common.titleSearchId = 2;
        }else {
            titleSearch.setVisibility(View.GONE);
            Common.titleSearchId = 1;
        }
        content.setCurrentItem(position);
    }


}
