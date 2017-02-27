package life.bean.com.beanlife.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import life.bean.com.beanlife.R;

/**
 * 作者 : bean on 2017/2/22/0022.
 * 注释 :
 */
public class HomeFragment extends BaseFragment {

    private ViewPager pager;
    private ArrayList<BaseFragment> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.layout_content;
    }

    @Override
    public void initView(View view) {
        pager = (ViewPager) view.findViewById(R.id.vp_detail);
    }


    @Override
    public void initData() {
        list.add(new DailyLifeFragment());

//        pager.setAdapter(new MyPagerAdapter(context,list));
    }

    @Override
    public void dealCommon() {

    }

    @Override
    public void onClick(View v) {

    }
}
