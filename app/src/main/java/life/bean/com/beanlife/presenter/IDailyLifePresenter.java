package life.bean.com.beanlife.presenter;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import life.bean.com.beanlife.activity.BudgetSettingActivity;
import life.bean.com.beanlife.activity.RecordQueryResultActivity;
import life.bean.com.beanlife.activity.ShortEditorActivity;
import life.bean.com.beanlife.activity.TextEditorActivity;
import life.bean.com.beanlife.activity.VoiceEditorActivity;
import life.bean.com.beanlife.bean.RecordDetail;
import life.bean.com.beanlife.entitybiz.DailyBiz;
import life.bean.com.beanlife.myInterface.OnGetDateListener;
import life.bean.com.beanlife.utils.IntentUtils;
import life.bean.com.beanlife.view.IDailyLifeView;

/**
 * 作者 : bean on 2017/4/25/0025.
 * 注释 :主页面的逻辑处理
 */
public class IDailyLifePresenter {
    private IDailyLifeView iDailyLifeView;
    private DailyBiz dailyBiz;

    public IDailyLifePresenter(IDailyLifeView iDailyLifeView) {
        this.iDailyLifeView = iDailyLifeView;
        dailyBiz = new DailyBiz();
    }

    public void shortEdit() {
        IntentUtils.startActivity(ShortEditorActivity.class);
    }

    public void voiceEdit() {
        IntentUtils.startActivity(VoiceEditorActivity.class);
    }

    public void textEdit() {
        IntentUtils.startActivity(TextEditorActivity.class);
    }

    public void showPayData() {
        IntentUtils.startActivity(RecordQueryResultActivity.class);
    }

    public void showDistanceData() {
        IntentUtils.startActivity(BudgetSettingActivity.class);
    }

    public void showIncomeData() {
        IntentUtils.startActivity(RecordQueryResultActivity.class);
    }


    public void setListData() {
        dailyBiz.setData(1000,new OnGetDateListener() {
            @Override
            public void onSuccessGetData(List list) {
                iDailyLifeView.RefreshView(list);
            }

            @Override
            public void onFailedGetData() {
                iDailyLifeView.showFailedError();
            }
        });
    }
}
