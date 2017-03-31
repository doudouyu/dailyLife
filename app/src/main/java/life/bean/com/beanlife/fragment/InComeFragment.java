package life.bean.com.beanlife.fragment;

import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.adapter.MyGridViewAdapter;
import life.bean.com.beanlife.bean.CategoryInfo;

/**
 * 作者 : bean on 2017/3/30/0030.
 * 注释 :
 */
public class InComeFragment extends BaseFragment {
    private ArrayList<CategoryInfo> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_income;
    }

    @Override
    public void initView(View view) {
        initList();
        GridView gv_category = (GridView) view.findViewById(R.id.gv_category);
        gv_category.setAdapter(new MyGridViewAdapter(context, list));
    }

    private void initList() {
        list.add(new CategoryInfo(R.mipmap.voice, "餐饮食品"));
        list.add(new CategoryInfo(R.mipmap.voice, "衣服饰品"));
        list.add(new CategoryInfo(R.mipmap.voice, "居家生活"));
        list.add(new CategoryInfo(R.mipmap.voice, "行车交通"));

    }

    @Override
    public void initData() {

    }
}
