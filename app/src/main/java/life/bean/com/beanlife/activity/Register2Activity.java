package life.bean.com.beanlife.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cl253.smssdk.lib.SMSSDK;
import com.cl253.smssdk.listener.ICheckVerificationCodeCallBack;
import com.cl253.smssdk.listener.IGetVerificationCodeCallBack;

import java.util.List;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.presenter.IRegisterPresenter;
import life.bean.com.beanlife.utils.IntentUtils;
import life.bean.com.beanlife.view.IRegisterView;

/**
 * 作者 : bean on 2017/3/3/0003.
 * 注释 :
 */
public class Register2Activity extends BaseActivity implements IRegisterView {

    private static boolean checkResult;
    private TextView tv_forget_password;
    private EditText et_input_number;
    private EditText et_input_code;
    private EditText et_input_password;
    private TextView tv_register;
    private boolean isPhone = false;
    private TextView tv_get_password;
    private TextView login;
    public static String checkCode;
    private LinearLayout ll_register;
    private IRegisterPresenter registerPresenter = new IRegisterPresenter(this);

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
        tv_get_password = (TextView) findViewById(R.id.tv_get_password);
        login = (TextView) findViewById(R.id.login);
        ll_register = (LinearLayout) findViewById(R.id.ll_register);

    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        tv_forget_password.setOnClickListener(this);
        tv_get_password.setOnClickListener(this);
        login.setOnClickListener(this);
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
                    ll_register.setVisibility(View.VISIBLE);
                    tv_register.setVisibility(View.VISIBLE);
                    isPhone = false;
                } else {
                    tv_forget_password.setText("手机号注册");
                    ll_register.setVisibility(View.GONE);
                    tv_register.setVisibility(View.GONE);
                    et_input_number.setHint("请输入邮箱号");
                    isPhone = true;
                }
                break;
            case R.id.tv_get_password:
                sendMessage(et_input_number.getText().toString().trim());
                break;
            case R.id.login:
                registerPresenter.numberRegister();
//                if (checkCode != null) {
//                    boolean check = checkPassword(checkCode);
//                    if (check) {
//                        Intent intent = new Intent(Register2Activity.this, LoginActivity.class);
//                        startActivity(intent);
//                    } else {
//                        showToast("验证码不正确或者已经失效");
//                    }
//                }
//                //记录当前密码
//                SharepreferenceUtils.setSharepreference(context, SpUtils.DAILY_LIFE, 0, SpUtils.USER_PASSWORD, et_input_password.getText().toString().trim());

                break;
        }
    }

    private void sendMessage(String phoneNumber) {
        SMSSDK.getVerificationCode(this, phoneNumber, this.getString(R.string.check_code), new IGetVerificationCodeCallBack() {


            @Override
            public void onResultSuccess(String s) {
                //发送成功
                showToast("发送成功");
                Register2Activity.checkCode = s;
            }

            @Override
            public void onResultFail(int i, String s) {
                //发送失败
                showToast("发送失败");
            }
        });
    }

    private boolean checkPassword(String code) {
        SMSSDK.checkVerificationCode(this, code, new ICheckVerificationCodeCallBack() {
            @Override
            public void onVerificationCodeCorrect() {
                //验证码正确
                showToast("验证码正确");
                Register2Activity.checkResult = true;
            }

            @Override
            public void onVerificationCodeError(int i, String s) {
                //验证码错误
                Register2Activity.checkResult = false;
                showToast("验证码错误,请重新发送");
            }
        });
        return Register2Activity.checkResult;
    }

    @Override
    public String getNumber() {
        return et_input_number.getText().toString();
    }

    @Override
    public String getPassword1() {
        return et_input_code.getText().toString();
    }

    @Override
    public String getPassword2() {
        return et_input_password.getText().toString();
    }

//    @Override
//    public void onSuccessResult() {
//        //注册成功跳转至登录页面
//        IntentUtils.startActivity(Login2Activity.class);
//    }
//
//    @Override
//    public void onFailedReason() {
//        //注册失败
//        showToast("注册失败，请重新注册！");
//    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void RefreshView(List list) {
        //注册成功跳转至登录页面
        IntentUtils.startActivity(Login2Activity.class);
    }

    @Override
    public void showFailedError() {
        showToast("注册失败，请重新注册！");
    }

    @Override
    public void clear() {
        et_input_number.setText("");
        et_input_code.setText("");
        et_input_password.setText("");
    }
}
