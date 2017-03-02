package life.bean.com.beanlife.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public Context context;
    public ImageView titleIcon;
    public TextView titleText;
    public ImageView titleSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        context = this;
        initView();
        dealCommon();
        initData();
    }

    public void dealCommon() {
        View view = findViewById(R.id.title_view);
        if (view != null) {
            titleIcon = (ImageView) view.findViewById(R.id.selected);
            titleText = (TextView) view.findViewById(R.id.left);
            titleSearch = (ImageView) view.findViewById(R.id.iv_search);
            if (Common.iconId == 1) {
                titleIcon.setImageResource(R.mipmap.category);
            } else if (Common.iconId == 2){
                titleIcon.setImageResource(R.mipmap.back);
            }
            if (Common.titleSearchId == 1) {
                titleSearch.setVisibility(View.GONE);
            } else if (Common.titleSearchId == 2){
                titleSearch.setVisibility(View.VISIBLE);
                titleSearch.setOnClickListener(this);
            }
            titleIcon.setOnClickListener(this);
        }
    }

    public void setTitleText(String text) {
        if (titleText!=null){
            titleText.setText(text);
        }

    }

    public abstract int getLayoutId();


    public abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selected:
                if (Common.iconId == 1) {
                    onInnerClick(v);
                } else if (Common.iconId == 2) {
                    finish();
                }
                break;
            case R.id.iv_search:
                if (Common.titleSearchId == 2){
                    Intent intent = new Intent(this, SearchActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                onInnerClick(v);
                break;
        }
    }

    public void onInnerClick(View v) {

    }


}
