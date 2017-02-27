package life.bean.com.beanlife.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import life.bean.com.beanlife.fragment.BaseFragment;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<BaseFragment> list;

    public MyPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
