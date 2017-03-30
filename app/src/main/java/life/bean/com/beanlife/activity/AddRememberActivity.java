package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import life.bean.com.beanlife.R;

/**
 * 作者 : bean on 2017/3/30/0030.
 * 注释 :
 */
public class AddRememberActivity extends BaseActivity{

    private String hint;
    private TextView tv_title;
    private TextView tv_sure;
    private ImageView iv_back;
    private EditText et_please_input_member_name;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_remember;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        hint = intent.getStringExtra("hint");
        tv_title = (TextView) findViewById(R.id.tv_title_name);
        tv_sure = (TextView) findViewById(R.id.tv_sure);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_please_input_member_name = (EditText) findViewById(R.id.et_please_input_member_name);
    }

    @Override
    public void initData() {
        if ("1".equals(hint)){
            tv_title.setText("新增成员");
            et_please_input_member_name.setHint("请输入成员姓名");
        }else {
            tv_title.setText("新增支付渠道");
            et_please_input_member_name.setHint("请输入支付渠道");
        }
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        tv_sure.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_sure:
                //携带当前页面谈的信息返回
                String name = et_please_input_member_name.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    showToast("名称不能为空");
                }else {
                    Intent intent = new Intent();
                    intent.putExtra("name",name);
                    setResult(2,intent);
                    finish();
                }

                break;
            default:
                break;
        }
    }
}
