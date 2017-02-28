package life.bean.com.beanlife.activity;

import android.view.View;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.fragment.AboutUsFragment;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/28/0028.
 * 注释 :
 */
public class AboutUsActivity extends BaseActivity {


    private boolean isSelf = false;

    @Override
    public int getLayoutId() {
        return R.layout.layout_about_us;
    }

    @Override
    public void initView() {
        Common.iconId = 1;
        Common.titleSearchId = 1;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_about_us, new AboutUsFragment()).commit();
        isSelf = false;
    }

    @Override
    public void dealCommon() {
        super.dealCommon();

    }

    @Override
    protected void initData() {
        setTitleText("关于我们");
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()){
            case R.id.selected:
                if (isSelf){
                    finish();
                }else {
                    getSupportFragmentManager().popBackStack();
                }
                break;
        }

    }
}
