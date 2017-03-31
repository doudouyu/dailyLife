package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import life.bean.com.beanlife.R;

/**
 * 作者 : bean on 2017/2/23/0023.
 * 注释 :
 */
public class VoiceEditorActivity extends BaseActivity{
    private TextView ivCommit;
    private ImageView iv_back;
    @Override
    public int getLayoutId() {
        return R.layout.activity_voice_edit;
    }
    @Override
    public void initView() {
        ivCommit = (TextView) findViewById(R.id.tv_commit);
        iv_back = (ImageView) findViewById(R.id.iv_back);
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
                intent = new Intent(context, VoiceEditorActivity.class);
                context.startActivity(intent);
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
