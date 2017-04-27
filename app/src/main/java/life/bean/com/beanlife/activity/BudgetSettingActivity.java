package life.bean.com.beanlife.activity;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.bean.GatChangeToBudgetBean;
import life.bean.com.beanlife.utils.Common;
import life.bean.com.beanlife.view.MySettingItemView;
import life.bean.com.beanlife.view.SwitchButton;

/**
 * 作者 : bean on 2017/4/25/0025.
 * 注释 :预算设置的界面
 */
public class BudgetSettingActivity extends BaseActivity {

    private MySettingItemView mySettingItemView;
    private SwitchButton sbOpenBudget;

    @Override
    public int getLayoutId() {
        return R.layout.activity_budget_setting;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        //找到sb
        sbOpenBudget = (SwitchButton) findViewById(R.id.sb_open_budget);
        mySettingItemView = (MySettingItemView) findViewById(R.id.show_budget_dialog);
        sbOpenBudget.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    //展开打开下面的布局
                    mySettingItemView.setVisibility(View.VISIBLE);
                    mySettingItemView.setOnClickListener(BudgetSettingActivity.this);
                } else {
                    mySettingItemView.setVisibility(View.GONE);
                }

            }
        });
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        if (mySettingItemView.getVisibility() == View.VISIBLE) {
            mySettingItemView.setOnClickListener(this);
        }
    }

    @Override
    public void initData() {
        setTitleText("预算设置");
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.show_budget_dialog:
                showSetBudgetDialog();
                break;
        }
    }

    private void showSetBudgetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(context,R.layout.dialog_budget_setting,null);
        //找到布局中的控件
        final EditText editText = (EditText) view.findViewById(R.id.et_money);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvSure = (TextView) view.findViewById(R.id.tv_sure);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = editText.getText().toString();
                if (TextUtils.isEmpty(money)){
                    showToast("金额不能为空");
                }else {
                    mySettingItemView.setCenterText(money);
                    EventBus.getDefault().post(new GatChangeToBudgetBean(money));
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
}
