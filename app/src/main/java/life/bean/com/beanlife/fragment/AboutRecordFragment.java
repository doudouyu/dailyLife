package life.bean.com.beanlife.fragment;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.view.MyTextView;

/**
 * 作者 : bean on 2017/3/2/0002.
 * 注释 :
 */
public class AboutRecordFragment extends BaseFragment {

    private MyTextView ll_privacy_policy;
    private MyTextView ll_user_agreement;
    private TextView tv_version;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about_record;
    }

    @Override
    public void initView(View view) {
        ll_privacy_policy = (MyTextView) view.findViewById(R.id.ll_privacy_policy);
        ll_user_agreement = (MyTextView) view.findViewById(R.id.ll_user_agreement);
        tv_version = (TextView) view.findViewById(R.id.tv_version);
        ll_privacy_policy.setOnClickListener(this);
        ll_user_agreement.setOnClickListener(this);
    }

    @Override
    public void initData() {
        tv_version.setText("版本号V:"+getCurrentVersion());
    }

    private String getCurrentVersion() {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String currentVersion = info.versionName + info.versionCode;
            return currentVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.ll_privacy_policy:
                showToast("隐私政策");
                break;
            case R.id.ll_user_agreement:
                showToast("用户协议");
                break;
            default:
                break;
        }
    }


}
