package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/28/0028.
 * 注释 :
 */
public class AboutUsActivity extends BaseActivity {

    private LinearLayout common_problem;
    private LinearLayout check_update;
    private LinearLayout recommend_friend;
    private LinearLayout about_us;

    @Override
    public int getLayoutId() {
        return R.layout.layout_about_us;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        common_problem = (LinearLayout) findViewById(R.id.common_problem);
        check_update = (LinearLayout) findViewById(R.id.check_update);
        recommend_friend = (LinearLayout) findViewById(R.id.recommend_friend);
        about_us = (LinearLayout) findViewById(R.id.about_us);
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        common_problem.setOnClickListener(this);
        check_update.setOnClickListener(this);
        recommend_friend.setOnClickListener(this);
        about_us.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitleText("关于我们");
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        Intent intent = new Intent(AboutUsActivity.this, AboutUsDetailActivity.class);
        switch (v.getId()) {

            case R.id.common_problem:
                intent.putExtra("titleName", "常见问题");
                startActivity(intent);
                break;

            case R.id.about_us:
                intent.putExtra("titleName", "关于微记账");
                startActivity(intent);
                break;
            case R.id.check_update:
                break;
            case R.id.recommend_friend:
                break;
            default:
                break;
        }

    }

}
