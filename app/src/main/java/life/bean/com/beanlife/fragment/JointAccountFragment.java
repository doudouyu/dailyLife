package life.bean.com.beanlife.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.AccountRememberActivity;
import life.bean.com.beanlife.activity.InviteFriendActivity;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class JointAccountFragment extends BaseFragment {

    private TextView tvAccountDescribe;
    private TextView tvJoin;

    @Override
    public int getLayoutId() {
        return R.layout.layout_joint_fragment;
    }

    @Override
    public void initView(View view) {
        tvAccountDescribe = (TextView) view.findViewById(R.id.tv_describe);
        tvJoin = (TextView) view.findViewById(R.id.tv_join);
    }

    @Override
    public void initData() {
        tvAccountDescribe.setOnClickListener(this);
        tvJoin.setOnClickListener(this);
    }

    @Override
    public void dealCommon() {

    }
    @Override
    public void onClick(View v) {
        Intent intent ;
        switch (v.getId()){

            case R.id.tv_describe:
                 intent = new Intent(context,AccountRememberActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_join:
                 intent = new Intent(context,InviteFriendActivity.class);
                startActivity(intent);
                break;
        }
    }
}
