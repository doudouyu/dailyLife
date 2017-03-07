package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class SearchResultActivity extends BaseActivity{

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initView() {


    }

    @Override
    public void initData() {
       setTitleText("账单查询结果");
        Common.titleSearchId = 1;
        Common.iconId = 2;
    }

}
