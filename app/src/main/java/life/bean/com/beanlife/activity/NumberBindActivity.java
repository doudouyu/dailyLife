package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/27/0027.
 * 注释 :
 */
public class NumberBindActivity extends BaseActivity {

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
        if ("1".equals(intent.getStringExtra("type"))) {
            etNumber.setHint("请输入手机号码");
            setTitleText("手机绑定");
        } else {
            etNumber.setHint("请输入邮箱号码");
            setTitleText("邮箱绑定");
        }
        etNumber.addTextChangedListener(new MyTextWatcher());
        etCode.setHint("请输入验证码");

    }

    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            Log.i("s",s+"");
//            Log.i("start",start+"");
//            Log.i("count",count+"");
//            Log.i("after",after+"");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            Log.i("s1",s+"");
//            Log.i("start1",start+"");
//            Log.i("count1",count+"");
//            Log.i("after1",before+"");

            if ("1".equals(intent.getStringExtra("type"))) {
                if (s.length() > 0) {
                    tvCode.setVisibility(View.VISIBLE);
                    tvCode.setOnClickListener(NumberBindActivity.this);
                }
                etNumber.setMaxLines(1);
                etNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
                if (s.length() > 11) {
                    etNumber.setText(s.subSequence(0, 11));
                    etNumber.setSelection(11);
                }

            } else if ("2".equals(intent.getStringExtra("type"))) {
                if (s.length() > 0) {
                    tvCode.setVisibility(View.VISIBLE);

                }
                if (s.length() > 19) {
                    etNumber.setText(s.subSequence(0, 19));
                    etNumber.setSelection(19);
                }
                Pattern pattern = Pattern.compile("[//w//.//-]+@([//w//-]+//.)+[//w//-]+", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(s);
                if (matcher.matches() == true) {
                    // TODO 发送验证码给邮箱
                    tvCode.setOnClickListener(NumberBindActivity.this);
                    Toast.makeText(context, "发送验证码给邮箱！", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(context,"请输入正确的邮箱账号！",Toast.LENGTH_SHORT).show();
                }

            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.tv_send_identifying_code:
                if ("1".equals(intent.getStringExtra("type"))) {
                    sentMessageToPhone();
                } else {
                    sentMessageToEmail();
                }
                break;
        }
    }

    /**
     * 发送邮件
     */
    private void sentMessageToEmail() {

    }

    /**
     * 发送短信
     */
    private void sentMessageToPhone() {


    }
}
