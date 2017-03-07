package life.bean.com.beanlife.activity;


import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class AccountRememberActivity extends BaseActivity{


    @Override
    public int getLayoutId() {
        return R.layout.layout_account_remember;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
    }

    @Override
    public void initData() {
        setTitleText("什么是联手记？");
    }

}
