package life.bean.com.beanlife.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/28/0028.
 * 注释 :
 */
public class SuggestionActivity extends BaseActivity {

    private ImageView ivBack;
    private TextView tvLeft;

    @Override
    public int getLayoutId() {
        return R.layout.layout_suggestion;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        View view = View.inflate(context, R.layout.layout_title, null);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,60);
        linearLayout.addView(view,0,params);
        ivBack = (ImageView) view.findViewById(R.id.selected);
        tvLeft = (TextView) view.findViewById(R.id.left);
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        ivBack.setImageResource(R.mipmap.back);
        tvLeft.setText("意见反馈");
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()){
            case R.id.selected:
                finish();
                break;
        }
    }
}
