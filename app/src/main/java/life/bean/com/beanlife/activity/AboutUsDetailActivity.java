package life.bean.com.beanlife.activity;

import android.content.Intent;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.fragment.AboutRecordFragment;
import life.bean.com.beanlife.fragment.CommonProblemFragment;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/3/2/0002.
 * 注释 :
 */
public class AboutUsDetailActivity extends BaseActivity {

    private String title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_us_detail;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        Intent intent = getIntent();
        title = intent.getStringExtra("titleName");
    }

    @Override
    public void initData() {
        setTitleText(title);
        if ("常见问题".equals(title)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.about_us_detail, new CommonProblemFragment()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.about_us_detail, new AboutRecordFragment()).commit();
        }
    }

}
