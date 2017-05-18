package life.bean.com.beanlife.activity;

import android.os.Handler.Callback;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.tools.utils.UIHandler;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import life.bean.com.beanlife.R;
import life.bean.com.beanlife.presenter.ILoginPresenter;
import life.bean.com.beanlife.entitybiz.UserBean;
import life.bean.com.beanlife.utils.IntentUtils;
import life.bean.com.beanlife.view.ILoginView;

/**
 * 作者 : bean on 2017/3/3/0003.
 * 注释 :
 */
public class Login2Activity extends BaseActivity implements Callback,
        PlatformActionListener, ILoginView {
    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR = 4;
    private static final int MSG_AUTH_COMPLETE = 5;
    private ILoginPresenter iLoginPresenter = new ILoginPresenter(this);
    private EditText etNumber;
    private EditText etPassword;
    private TextView tvRegister;
    private TextView tvForgetPasword;
    private ImageView ivWechat;
    private ImageView ivQq;
    private TextView login;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        ShareSDK.initSDK(this);
        etNumber = (EditText) findViewById(R.id.et_input_number);
        etPassword = (EditText) findViewById(R.id.et_input_password);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvForgetPasword = (TextView) findViewById(R.id.tv_forget_password);
        ivWechat = (ImageView) findViewById(R.id.iv_wechat);
        ivQq = (ImageView) findViewById(R.id.iv_qq);
        login = (TextView) findViewById(R.id.login);
        TextView tv_register = (TextView) findViewById(R.id.tv_register);
        String str2=String.format("提示：未注册云验证的手机号，登录时将自动注册云验证账号，且代表您已同意\n ：<font color=\"#0000ff\">%s", "《云验证用户协议》");
        tv_register.setText(Html.fromHtml(str2));

    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        tvRegister.setOnClickListener(this);
        tvForgetPasword.setOnClickListener(this);
        ivWechat.setOnClickListener(this);
        ivQq.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.tv_register:
                iLoginPresenter.register();
                break;
            case R.id.tv_forget_password:
                iLoginPresenter.updatePassword();

                break;
            case R.id.iv_wechat:
                authorize(new Wechat(this));
                break;
            case R.id.iv_qq:
                authorize(new QZone(this));
                break;
            case R.id.login:
                if ("".equals(getName())){
                    showToast("账号不能为空");
                    etNumber.setText("");
                    return;
                }
                if ("".equals(getPassword())){
                    showToast("密码不能为空");
                    etNumber.setText("");
                    return;
                }
                //TODO 改成注册时存储的号码信息
//                if ("13608676132".equals(getName())&&!"123".equals(getPassword())){
//                    showToast("密码错误");
//                    etPassword.setText("");
//                    return;
//                }
//                if (!"13608676132".equals(getName())&&"123".equals(getPassword())){
//                    showToast("账号错误");
//                    etNumber.setText("");
//                    return;
//                }
//                if (!"13608676132".equals(getName())&&!"123".equals(getPassword())){
//                    showToast("账号密码错误");
//                    etNumber.setText("");
//                    return;
//                }
//                if ("13608676132".equals(getName())&&"123".equals(getPassword())){
//                    Intent intentMain = new Intent(context,MainActivity.class);
//                    startActivity(intentMain);
//                }
                iLoginPresenter.login();
                break;
            default:
                break;
        }
    }

    public void authorize(Platform plat) {
        if (plat.isValid()) {
            String userId = plat.getDb().getUserId();
            if (!TextUtils.isEmpty(userId)) {
                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
                login(plat.getName(), userId, null);
                return;
            }
        }
        plat.setPlatformActionListener(this);
        plat.SSOSetting(true);
        plat.showUser(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(this);
    }

    private void login(String plat, String userId, HashMap<String, Object> userInfo) {
        Message msg = new Message();
        msg.what = MSG_LOGIN;
        msg.obj = plat;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_USERID_FOUND: {
                Toast.makeText(this, R.string.userid_found, Toast.LENGTH_SHORT).show();
            }
            break;
            case MSG_LOGIN: {

                String text = getString(R.string.logining, msg.obj);
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                System.out.println("---------------");
                iLoginPresenter.login();
//				Builder builder = new Builder(this);
//				builder.setTitle(R.string.if_register_needed);
//				builder.setMessage(R.string.after_auth);
//				builder.setPositiveButton(R.string.ok, null);
//				builder.create().show();
            }
            break;
            case MSG_AUTH_CANCEL: {
                Toast.makeText(this, R.string.auth_cancel, Toast.LENGTH_SHORT).show();
                System.out.println("-------MSG_AUTH_CANCEL--------");
            }
            break;
            case MSG_AUTH_ERROR: {
                Toast.makeText(this, R.string.auth_error, Toast.LENGTH_SHORT).show();
                System.out.println("-------MSG_AUTH_ERROR--------");
            }
            break;
            case MSG_AUTH_COMPLETE: {
                Toast.makeText(this, R.string.auth_complete, Toast.LENGTH_SHORT).show();
                System.out.println("--------MSG_AUTH_COMPLETE-------");
                iLoginPresenter.login();
            }
            break;
        }
        return false;
    }

    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> hashMap) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE, this);
            login(platform.getName(), platform.getDb().getUserId(), hashMap);
        }
    }

    @Override
    public void onError(Platform platform, int action, Throwable throwable) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_ERROR, this);
        }
        throwable.printStackTrace();
    }

    @Override
    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL, this);
        }
    }

    @Override
    public String getName() {
        return etNumber.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString().trim();
    }
//
//    @Override
//    public void toMain(UserBean bean) {
//        showToast("success"+"姓名"+bean.getUsername()+"密码"+bean.getPassWord());
//        IntentUtils.startActivity(MainActivity.class);
//    }
//
//    @Override
//    public void loginFailed() {
//        showToast("failed");
//    }

    @Override
    public void RefreshView(List list) {
        UserBean bean = (UserBean) list.get(0);
        showToast("success"+"姓名"+bean.getUsername()+"密码"+bean.getPassWord());
        IntentUtils.startActivity(MainActivity.class);
    }

    @Override
    public void showFailedError() {
        showToast("failed");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
