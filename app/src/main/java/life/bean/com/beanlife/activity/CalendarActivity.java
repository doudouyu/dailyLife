package life.bean.com.beanlife.activity;

import android.content.Intent;
import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/3/30/0030.
 * 注释 :
 */
public class CalendarActivity extends BaseActivity{

    private String titleName;

    @Override
    public int getLayoutId() {
        Intent intent = getIntent();
        titleName = intent.getStringExtra("titleName");
        if ("1".equals(titleName)){
            return R.layout.layout_canlend;
        }else {
            return R.layout.layout_location;
        }
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        if ("1".equals(titleName)){
            Common.titleSearchId = 1;
        }else {
            Common.titleSearchId = 2;
        }

    }

    @Override
    public void initData() {
        if ("1".equals(titleName)){
            setTitleText("选择日期");
        }else {
            setTitleText("所在位置");
        }
    }
}
