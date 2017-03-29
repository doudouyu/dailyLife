package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mob.tools.utils.UIHandler;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import life.bean.com.beanlife.R;

import android.os.Handler.Callback;
import android.widget.Toast;

import java.util.HashMap;

/**
 * 作者 : bean on 2017/3/3/0003.
 * 注释 :
 */
public class LoginActivity extends BaseActivity implements Callback,
        PlatformActionListener {
    private static final int MSG_USERID_FOUND = 1;
    private static final int MSG_LOGIN = 2;
    private static final int MSG_AUTH_CANCEL = 3;
    private static final int MSG_AUTH_ERROR = 4;
    private static final int MSG_AUTH_COMPLETE = 5;
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
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_register:
                intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forget_password:
                intent = new Intent(context, UpdatePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_wechat:
                authorize(new Wechat(this));
                break;
            case R.id.iv_qq:
                authorize(new QZone(this));
                break;
            case R.id.login:
                String number= etNumber.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if ("".equals(number)){
                    showToast("账号不能为空");
                    etNumber.setText("");
                    return;
                }
                if ("".equals(password)){
                    showToast("密码不能为空");
                    etNumber.setText("");
                    return;
                }
                //TODO 改成注册时存储的号码信息
                if ("13608676132".equals(number)&&!"123".equals(password)){
                    showToast("密码错误");
                    etPassword.setText("");
                    return;
                }
                if (!"13608676132".equals(number)&&"123".equals(password)){
                    showToast("账号错误");
                    etNumber.setText("");
                    return;
                }
                if (!"13608676132".equals(number)&&!"123".equals(password)){
                    showToast("账号密码错误");
                    etNumber.setText("");
                    return;
                }
                if ("13608676132".equals(number)&&"123".equals(password)){
                    Intent intentMain = new Intent(context,MainActivity.class);
                    startActivity(intentMain);
                }
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
                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(context,MainActivity.class);
                startActivity(intent);
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

}
