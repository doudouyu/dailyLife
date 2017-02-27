package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/27/0027.
 * 注释 :
 */
public class NumberBindActivity extends BaseActivity{

    private EditText etNumber;
    private EditText etCode;
    private TextView tvCode;
    private Intent intent;

    @Override
    public int getLayoutId() {
        return R.layout.layout_number_bind;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        etNumber = (EditText) findViewById(R.id.et_number);
        etCode = (EditText) findViewById(R.id.et_identifying_code);
        tvCode = (TextView) findViewById(R.id.tv_send_identifying_code);
        intent = getIntent();

    }

    @Override
    protected void initData() {
        if ("1".equals(intent.getStringExtra("type"))){
            etNumber.setHint("请输入手机号码");
            setTitleText("手机绑定");
        }else {
            etNumber.setHint("请输入邮箱号码");
            setTitleText("邮箱绑定");
        }
        etCode.setHint("请输入验证码");
    }
}
