package life.bean.com.beanlife.fragment;

import android.view.View;
import android.widget.LinearLayout;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.AboutUsActivity;

/**
 * 作者 : bean on 2017/2/28/0028.
 * 注释 :
 */
public class AboutUsFragment extends BaseFragment {
    private LinearLayout common_problem;
    private LinearLayout check_update;
    private LinearLayout recommend_friend;
    private LinearLayout about_us;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about_us;
    }

    @Override
    public void initView(View view) {
        common_problem = (LinearLayout) view.findViewById(R.id.common_problem);
        check_update = (LinearLayout) view.findViewById(R.id.check_update);
        recommend_friend = (LinearLayout) view.findViewById(R.id.recommend_friend);
        about_us = (LinearLayout) view.findViewById(R.id.about_us);
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

    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        AboutUsActivity activity = (AboutUsActivity) getActivity();
        switch (v.getId()) {

            case R.id.common_problem:
                activity.setTitleText("常见问题");
                getFragmentManager().beginTransaction().replace(R.id.frame_about_us,new CommonProblemFragment()).commit();
                break;
            case R.id.check_update:
                break;
            case R.id.recommend_friend:
                break;
            case R.id.about_us:
                activity.setTitleText("关于微记账");
                getFragmentManager().beginTransaction().replace(R.id.frame_about_us,new CommonProblemFragment()).commit();
                break;
            default:
                break;
        }
    }
}
