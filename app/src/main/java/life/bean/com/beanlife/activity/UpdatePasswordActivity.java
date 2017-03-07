package life.bean.com.beanlife.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/3/3/0003.
 * 注释 :
 */
public class UpdatePasswordActivity extends BaseActivity {

    private TextView tvFindPassword;
    private boolean isPhone = true;
    private EditText et_input_number;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_password;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        tvFindPassword = (TextView) findViewById(R.id.tv_forget_password);
        et_input_number = (EditText) findViewById(R.id.et_input_number);

    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        tvFindPassword.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitleText("手机号找回密码");
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.tv_forget_password:
                if (isPhone) {
                    setTitleText("手机号找回密码");
                    tvFindPassword.setText("邮箱找回密码");
                    et_input_number.setHint("请输入手机号");
                    isPhone = false;
                } else {
                    setTitleText("邮箱找回密码");
                    tvFindPassword.setText("手机号找回密码");
                    et_input_number.setHint("请输入邮箱号");
                    isPhone = true;
                }
                break;
        }
    }
}
