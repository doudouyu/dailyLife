package life.bean.com.beanlife.activity;

import android.app.AlertDialog;
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
        userName = SharepreferenceUtils.getSharepreference(this, SpUtils.DAILY_LIFE,0, SpUtils.USER_NAME);

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
//            case R.id.tv_sure:
//                break;

        }
    }

    private void showMyDialog(String name) {

        final AlertDialog dialog = new AlertDialog.Builder
                (this).create();
        View view = LayoutInflater.from(NumberManageActivity.this).inflate(R.layout.layout_dialog,null);
        dialog.setView(view,0,0,0,0);
        dialog.setTitle(name);
        final EditText etName = (EditText) view.findViewById(R.id.et_input_name);
//        tvSure = (TextView) view.findViewById(R.id.tv_sure);
//        tvSure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String etNameText = etName.getText().toString().trim();
//                if (!TextUtils.isEmpty(etNameText)&&etNameText.length()<=10){
//                    dialog.dismiss();
//                    SharepreferenceUtils.setSharepreference(context,SpUtils.DAILY_LIFE,0, SpUtils.USER_NAME,etNameText);
//                }
//            }
//        });
        tvNumber = (TextView) view.findViewById(R.id.tv_number);
        etName.addTextChangedListener(new OnMyTextChangeListener());
        dialog.show();
        WindowManager.LayoutParams params =
                dialog.getWindow().getAttributes();
        params.width = 300;
        params.height = 200 ;
        dialog.getWindow().setAttributes(params);

    }

    private class OnMyTextChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (count>0){


                tvSure.setOnClickListener(NumberManageActivity.this);
                if (count<11){
                    tvNumber.setText(count+"");
                }else {
                    tvNumber.setText(10+"");
                }
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count>0){
                tvSure.setOnClickListener(NumberManageActivity.this);
                if (count<11){
                    tvNumber.setText(count+"");
                }else {
                    tvNumber.setText(10+"");
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


}
