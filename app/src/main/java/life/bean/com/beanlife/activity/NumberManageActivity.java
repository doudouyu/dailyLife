package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/27/0027.
 * 注释 :
 */
public class NumberManageActivity extends BaseActivity {

    private TextView tvOutLogin;
    private RelativeLayout rlName;
    private RelativeLayout rlPhone;
    private RelativeLayout rlEmail;
    private RelativeLayout rlEmail1;
    private RelativeLayout rlQQ;
    private RelativeLayout rlWeChat;
    private Intent intent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_number_manage;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        tvOutLogin = (TextView) findViewById(R.id.tv_out_login);
        rlName = (RelativeLayout) findViewById(R.id.rl_name);
        rlPhone = (RelativeLayout) findViewById(R.id.rl_phone);
        rlEmail1 = (RelativeLayout) findViewById(R.id.rl_email);
        rlQQ = (RelativeLayout) findViewById(R.id.rl_qq);
        rlWeChat = (RelativeLayout) findViewById(R.id.rl_we_chat);

    }

    @Override
    public void initData() {
        setTitleText("账号管理");
        tvOutLogin.setOnClickListener(this);
        rlName.setOnClickListener(this);
        rlPhone.setOnClickListener(this);
        rlEmail1.setOnClickListener(this);
        rlQQ.setOnClickListener(this);
        rlWeChat.setOnClickListener(this);


    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.rl_name:
                Toast.makeText(context, "名字", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_phone:
                intent = new Intent(context,NumberBindActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
            case R.id.rl_email:
                intent = new Intent(context,NumberBindActivity.class);
                intent.putExtra("type","2");
                startActivity(intent);
                break;
            case R.id.rl_qq:
                Toast.makeText(context, "qq", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_we_chat:
                Toast.makeText(context, "微信", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_out_login:
                Toast.makeText(context, "退出登录", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
