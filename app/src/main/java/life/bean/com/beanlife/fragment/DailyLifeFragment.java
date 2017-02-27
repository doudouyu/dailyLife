package life.bean.com.beanlife.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.ShortEditorActivity;
import life.bean.com.beanlife.activity.TextEditorActivity;
import life.bean.com.beanlife.activity.VoiceEditorActivity;
import life.bean.com.beanlife.adapter.MyRecordAdapter;
import life.bean.com.beanlife.bean.RecordDetail;
import life.bean.com.beanlife.fragment.BaseFragment;


/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class DailyLifeFragment extends BaseFragment {

    private LinearLayout llPay;
    private LinearLayout llInCome;
    private LinearLayout llGap;
    private ArrayList<RecordDetail> list = new ArrayList();
    private ListView lvRecord;
    private ImageView ivPen;
    private ImageView ivVoice;
    private ImageView ivBook;
    private Intent intent;
    @Override
    public int getLayoutId() {
        return R.layout.layout_daily_life;
    }

    @Override
    public void initView(View view) {
        llPay = (LinearLayout) view.findViewById(R.id.ll_contain1);
        llInCome = (LinearLayout) view.findViewById(R.id.ll_contain2);
        llGap = (LinearLayout) view.findViewById(R.id.ll_contain3);
        lvRecord = (ListView) view.findViewById(R.id.lv_recode);
        ivPen = (ImageView) view.findViewById(R.id.iv_pen);
        ivVoice = (ImageView) view.findViewById(R.id.iv_voice);
        ivBook = (ImageView) view.findViewById(R.id.iv_book);
        llPay.setOnClickListener(this);
        llInCome.setOnClickListener(this);
        llGap.setOnClickListener(this);
        ivPen.setOnClickListener(this);
        ivVoice.setOnClickListener(this);
        ivBook.setOnClickListener(this);
    }


    @Override
    public void initData() {
        lvRecord.setAdapter(new MyRecordAdapter(context, list));
    }

    @Override
    public void dealCommon() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // TODO 更新listView数据源，刷新界面
            case R.id.ll_contain1:

                break;
            case R.id.ll_contain2:

                break;
            case R.id.ll_contain3:

                break;
            case R.id.iv_pen:
                intent = new Intent(context,ShortEditorActivity.class);
                context.startActivity(intent);
                break;
            case R.id.iv_voice:
                intent = new Intent(context,VoiceEditorActivity.class);
                context.startActivity(intent);
                break;
            case R.id.iv_book:
                intent = new Intent(context,TextEditorActivity.class);
                context.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
