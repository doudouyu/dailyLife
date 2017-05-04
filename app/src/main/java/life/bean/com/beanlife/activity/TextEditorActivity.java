package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
//import android.widget.ImageView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import life.bean.com.beanlife.R;

/**
 * 作者 : bean on 2017/2/23/0023.
 * 注释 :
 */
public class TextEditorActivity extends BaseActivity {

    private TextView ivCommit;
    private ImageView iv_back;
    private EditText et_input_detail;

    @Override
    public int getLayoutId() {
        return R.layout.layout_text_edit;
    }

    @Override
    public void initView() {
        ivCommit = (TextView) findViewById(R.id.tv_commit);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_input_detail = (EditText) findViewById(R.id.et_input_detail);
    }

    @Override
    public void initData() {

    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        ivCommit.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent;
        switch (v.getId()) {

            case R.id.tv_commit:
                if (!TextUtils.isEmpty(et_input_detail.getText().toString().trim())){
                    intent = new Intent(context, RecordActivity.class);
                    context.startActivity(intent);
                }else {
                    showToast("请输入内容");
                }

                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
