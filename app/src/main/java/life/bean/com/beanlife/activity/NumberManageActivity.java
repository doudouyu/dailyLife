package life.bean.com.beanlife.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;
import life.bean.com.beanlife.utils.SharepreferenceUtils;
import life.bean.com.beanlife.utils.SpUtils;

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
    private TextView tvSure;
    private TextView tvNumber;
    private TextView tv_name;
    private String userName;
    private TextView tvNumber1;

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
        tv_name = (TextView) findViewById(R.id.tv_name);
        userName = SharepreferenceUtils.getSharepreference(this, SpUtils.DAILY_LIFE, 0, SpUtils.USER_NAME_STRING);

    }

    @Override
    public void initData() {
        setTitleText("账号管理");
        tv_name.setText(userName);
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
                showMyDialog("请输入昵称");
                break;
            case R.id.rl_phone:
                intent = new Intent(context, NumberBindActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.rl_email:
                intent = new Intent(context, NumberBindActivity.class);
                intent.putExtra("type", "2");
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
//            case R.id.tv_sure:
//                break;

        }
    }

    private void showMyDialog(String name) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(NumberManageActivity.this);
        final View viewDia = LayoutInflater.from(NumberManageActivity.this).inflate(R.layout.layout_dialog, null);
        builder.setTitle("请输入昵称");
        builder.setView(viewDia);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        TextView tvSure = (TextView) viewDia.findViewById(R.id.tv_sure);
        tvNumber1 = (TextView) viewDia.findViewById(R.id.tv_number);
        final EditText diaInput = (EditText) viewDia.findViewById(R.id.et_input_name);
        diaInput.addTextChangedListener(new OnMyTextChangeListener());
        if (diaInput.getText().toString().trim().length()<=10){
            tvSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String inputString = diaInput.getText().toString().trim();
                    if (inputString.length() <= 0){
                        Toast.makeText(context, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    }else if (inputString.length() <= 10) {
                        tv_name.setText(inputString);
                        SharepreferenceUtils.setSharepreference(context,SpUtils.DAILY_LIFE,0,SpUtils.USER_NAME_STRING,inputString);
                        dialog.dismiss();
                    } else if (inputString.length() > 10){
                        diaInput.setText("");
                        Toast.makeText(context, "不能超过十个字符", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        dialog.show();
    }

    private class OnMyTextChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length()<=10){
                tvNumber1.setText(s.length()+"");
            }else {
                tvNumber1.setText("10");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


}
