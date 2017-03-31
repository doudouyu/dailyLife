package life.bean.com.beanlife.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.ShortEditorActivity;
import life.bean.com.beanlife.adapter.MyGridViewAdapter;
import life.bean.com.beanlife.bean.CategoryInfo;

/**
 * 作者 : bean on 2017/3/30/0030.
 * 注释 :
 */
public class InComeFragment extends BaseFragment {
    private ArrayList<CategoryInfo> list = new ArrayList<>();
    private GridView gv_category;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_income;
    }

    @Override
    public void initView(View view) {
        initList();
        gv_category = (GridView) view.findViewById(R.id.gv_category);
    }

    private void initList() {
        list.add(new CategoryInfo(R.mipmap.catering, "餐饮食品"));
        list.add(new CategoryInfo(R.mipmap.clothes, "衣服饰品"));
        list.add(new CategoryInfo(R.mipmap.house, "居家生活"));
        list.add(new CategoryInfo(R.mipmap.transportation, "行车交通"));

    }
    @Override
    public void initData() {
        gv_category.setAdapter(new MyGridViewAdapter(context,list));

    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        gv_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShortEditorActivity activity = (ShortEditorActivity) getActivity();
                activity.setIvIcon(list.get(position).getIcon());
                activity.setIvText(list.get(position).getName());
            }
        });
    }
}
