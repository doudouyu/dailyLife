package life.bean.com.beanlife.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class InviteFriendActivity extends BaseActivity{

    private TextView tvShow;
    @Override
    public int getLayoutId() {
        return R.layout.layout_invite_friend;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        tvShow = (TextView)findViewById(R.id.tv_scan_show);
    }

    @Override
    protected void initData() {
        setTitleText("联手记邀请");
        tvShow.setOnClickListener(this);
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()){
            case R.id.tv_scan_show:
                findViewById(R.id.ll_invite_by_phone).setVisibility(View.VISIBLE);
                break;
        }
    }

}
