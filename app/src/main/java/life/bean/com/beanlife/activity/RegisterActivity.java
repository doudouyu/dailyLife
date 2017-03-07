package life.bean.com.beanlife.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import life.bean.com.beanlife.R;

/**
 * 作者 : bean on 2017/3/3/0003.
 * 注释 :
 */
public class RegisterActivity extends BaseActivity {

    private TextView tv_forget_password;
    private EditText et_input_number;
    private EditText et_input_code;
    private EditText et_input_password;
    private TextView tv_register;
    private boolean isPhone = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_forget_password = (TextView) findViewById(R.id.tv_forget_password);
        et_input_number = (EditText) findViewById(R.id.et_input_number);
        et_input_code = (EditText) findViewById(R.id.et_input_code);
        et_input_password = (EditText) findViewById(R.id.et_input_password);


    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        tv_forget_password.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.tv_forget_password:
                if (isPhone) {
                    tv_forget_password.setText("邮箱注册");
                    et_input_number.setHint("请输入手机号");
                    et_input_code.setVisibility(View.VISIBLE);
                    tv_register.setVisibility(View.VISIBLE);
                    isPhone = false;
                } else {
                    tv_forget_password.setText("手机号注册");
                    et_input_code.setVisibility(View.GONE);
                    tv_register.setVisibility(View.GONE);
                    et_input_number.setHint("请输入邮箱号");
                    isPhone = true;
                }
                break;
        }
    }
}
