package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.adapter.MySearchAdapter;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/23/0023.
 * 注释 :
 */
public class SearchActivity extends BaseActivity{

    private ListView lvSearch;
    private ArrayList<String> list = new ArrayList();
    private TextView tvSearch;
    private TextView tvReset;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        lvSearch = (ListView) findViewById(R.id.lv_search);
        tvSearch = (TextView) findViewById(R.id.tv_search);
        tvReset =  (TextView) findViewById(R.id.tv_reset);
        Common.iconId = 2;
        Common.titleSearchId = 1;
    }

    @Override
    protected void initData() {
        setTitleText("账单查询");
        tvReset.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        lvSearch.setAdapter(new MySearchAdapter(this));
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
    }
}
