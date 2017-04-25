package life.bean.com.beanlife.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.ShortEditorActivity;
import life.bean.com.beanlife.activity.TextEditorActivity;
import life.bean.com.beanlife.activity.VoiceEditorActivity;
import life.bean.com.beanlife.adapter.MyRecordAdapter;
import life.bean.com.beanlife.bean.RecordDetail;
import life.bean.com.beanlife.fragment.BaseFragment;
import life.bean.com.beanlife.presenter.IDailyLifePresenter;
import life.bean.com.beanlife.view.IDailyLifeView;


/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class DailyLifeFragment extends BaseFragment implements IDailyLifeView{

    private LinearLayout llPay;
    private LinearLayout llInCome;
    private LinearLayout llGap;
    private ArrayList<RecordDetail> list = new ArrayList();
    private ListView lvRecord;
    private ImageView ivPen;
    private ImageView ivVoice;
    private ImageView ivBook;
    private Intent intent;
    private IDailyLifePresenter iDailyLifePresenter = new IDailyLifePresenter(this);
    @Override
    public int getLayoutId() {
        return R.layout.layout_daily_life;
    }

    @Override
    public void initView(View view) {
        llPay = (LinearLayout) view.findViewById(R.id.ll_pay);
        llInCome = (LinearLayout) view.findViewById(R.id.ll_income);
        llGap = (LinearLayout) view.findViewById(R.id.ll_distance);
        lvRecord = (ListView) view.findViewById(R.id.lv_recode);
        ivPen = (ImageView) view.findViewById(R.id.iv_pen);
        ivVoice = (ImageView) view.findViewById(R.id.iv_voice);
        ivBook = (ImageView) view.findViewById(R.id.iv_book);

    }


    @Override
    public void initData() {
        iDailyLifePresenter.setListData();
    }

    @Override
    public void dealCommon() {
        llPay.setOnClickListener(this);
        llInCome.setOnClickListener(this);
        llGap.setOnClickListener(this);
        ivPen.setOnClickListener(this);
        ivVoice.setOnClickListener(this);
        ivBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // TODO 更新listView数据源，刷新界面
            case R.id.ll_pay:
                iDailyLifePresenter.showPayData();
                break;
            case R.id.ll_distance:
                iDailyLifePresenter.showDistanceData();
                break;
            case R.id.ll_income:
                iDailyLifePresenter.showIncomeData();
                break;
            case R.id.iv_pen:
               iDailyLifePresenter.shortEdit();
                break;
            case R.id.iv_voice:
                iDailyLifePresenter.voiceEdit();
                break;
            case R.id.iv_book:
                iDailyLifePresenter.textEdit();
                break;
            default:
                break;
        }
    }

    @Override
    public void RefreshView(List list) {
        lvRecord.setAdapter(new MyRecordAdapter(context, list));
    }

    @Override
    public void DealItemClickEvent() {

    }


    @Override
    public void showFailedError() {
        showToast("数据加载失败，请稍后重试！");
    }
}
