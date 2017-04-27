package life.bean.com.beanlife.activity;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.adapter.MyRecyclerAdapter;
import life.bean.com.beanlife.bean.RecordDetail;
import life.bean.com.beanlife.presenter.IQueryResultPresenter;
import life.bean.com.beanlife.utils.Common;
import life.bean.com.beanlife.utils.TranslateAnimationUtils;
import life.bean.com.beanlife.view.IQueryResultView;

/**
 * 作者 : bean on 2017/4/25/0025.
 * 注释 :账单查询结果界面
 */
public class RecordQueryResultActivity extends BaseActivity implements IQueryResultView{

    private LinearLayout llSelected;
    private LinearLayout llContent;
    private TextView tvByWeek;
    private TextView tvByMonth;
    private TextView tvByYear;
    private TextView tvByCustom;
    private TextView tvYear;
    private RecyclerView recyclerView;
    private ImageView ivArrowState;
    private int numColumns = 3;
    private GridLayoutManager glManger;
    private List<RecordDetail> list = new ArrayList();

    private IQueryResultPresenter presenter = new IQueryResultPresenter(this);
    public final int WEEK = 1;
    public final int MONTH = 2;
    public final int YEAR = 3;
    private int width;
    private View indicatorLine;

    @Override
    public int getLayoutId() {
        return R.layout.activity_record_result;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        llSelected = (LinearLayout) findViewById(R.id.ll_date);
        llContent = (LinearLayout) findViewById(R.id.ll_content);
        ivArrowState = (ImageView) findViewById(R.id.image_arrow_state);
        tvByWeek = (TextView) findViewById(R.id.tv_by_week);
        tvByMonth = (TextView) findViewById(R.id.tv_by_month);
        tvByYear = (TextView) findViewById(R.id.tv_by_year);
        tvByCustom = (TextView) findViewById(R.id.tv_custom);
        tvYear = (TextView) findViewById(R.id.tv_year);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        indicatorLine = findViewById(R.id.indicator_line);
    }
    private void initLine() {
        indicatorLine.setLayoutParams(new LinearLayout.LayoutParams(width/4, 5));
    }
    @Override
    public void dealCommon() {
        super.dealCommon();
        llSelected.setOnClickListener(this);
        tvByWeek.setOnClickListener(this);
        tvByMonth.setOnClickListener(this);
        tvByYear.setOnClickListener(this);
        tvByCustom.setOnClickListener(this);

    }


    @Override
    public void initData() {
        setTitleText("账单查询结果");
        initLine();
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.ll_date:
                //点击这个弹出下面的布局
                if (llContent.getVisibility() == View.GONE) {
                    llContent.setVisibility(View.VISIBLE);
                    ivArrowState.setImageResource(R.mipmap.arrow_up);

                } else {
                    llContent.setVisibility(View.GONE);
                    ivArrowState.setImageResource(R.mipmap.arrow_down);
                }
                break;
            case R.id.tv_by_week:
                //给Recycle设置数据源以及管理器
                numColumns = 3;
                glManger = new GridLayoutManager(this, numColumns);
                recyclerView.setLayoutManager(glManger);
                presenter.setData(WEEK);
                TranslateAnimationUtils.initRightAnim(0,0,indicatorLine);
                break;
            case R.id.tv_by_month:
                TranslateAnimationUtils.initRightAnim(0,width/4,indicatorLine);
                numColumns = 4;
                glManger = new GridLayoutManager(this, numColumns);
                recyclerView.setLayoutManager(glManger);
                presenter.setData(MONTH);
                break;
            case R.id.tv_by_year:
                TranslateAnimationUtils.initRightAnim(width/4,width/2,indicatorLine);
                numColumns = 4;
                glManger = new GridLayoutManager(this, numColumns);
                recyclerView.setLayoutManager(glManger);
                presenter.setData(YEAR);
                 break;
            case R.id.tv_custom:
                TranslateAnimationUtils.initRightAnim(width/2,width/4*3,indicatorLine);
                break;
            default:
                break;
        }
    }

    @Override
    public void RefreshView(List list) {
        super.RefreshView(list);
        recyclerView.setAdapter(new MyRecyclerAdapter(recyclerView, list, R.layout.item_text_style));
    }

    @Override
    public void showFailedError() {
        super.showFailedError();
    }
}
