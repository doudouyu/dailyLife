package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.view.View;
//import android.widget.ImageView;
import android.widget.TextView;

import life.bean.com.beanlife.R;

/**
 * 作者 : bean on 2017/2/23/0023.
 * 注释 :
 */
public class TextEditorActivity extends BaseActivity {

    private TextView ivCommit;

    @Override
    public int getLayoutId() {
        return R.layout.layout_text_edit;
    }

    @Override
    public void initView() {
        ivCommit = (TextView) findViewById(R.id.tv_commit);

    }

    @Override
    public void initData() {
        ivCommit.setOnClickListener(this);
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
        }
    }
}
