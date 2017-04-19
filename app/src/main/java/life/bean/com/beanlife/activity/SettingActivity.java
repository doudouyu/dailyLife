package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/27/0027.
 * 注释 :
 */
public class SettingActivity extends BaseActivity {

    private LinearLayout numberManage;
    private ImageView ivIcon;
    private ImageView ivNumberBack;
    private LinearLayout password;
    private LinearLayout out_record;
    private LinearLayout show_style;
    private LinearLayout location;
    private LinearLayout shake;
    private LinearLayout voice_toggle;
    private LinearLayout suggestion;
    private LinearLayout about_us;
    private TextView tvNumber;
    private ImageView ivTo;
    private TextView tvPassword;
    private TextView tvPasswordState;
    private TextView tvOutRecord;
    private TextView tvShowStyle;
    private TextView tvShowStyles;
    private TextView tvLocation;
    private TextView tvShake;
    private TextView tvVoiceToggle;
    private TextView tvSuggestion;
    private TextView tvAboutUs;
    private Intent intent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        numberManage = (LinearLayout) findViewById(R.id.number_manage);
        tvNumber = (TextView) numberManage.getChildAt(0);
        ivIcon = (ImageView) numberManage.getChildAt(1);
        ivTo = (ImageView) numberManage.getChildAt(2);

        password = (LinearLayout) findViewById(R.id.password);
        tvPassword = (TextView) password.getChildAt(0);
        tvPasswordState = (TextView) password.getChildAt(1);

        out_record = (LinearLayout) findViewById(R.id.out_record);
        tvOutRecord = (TextView) out_record.getChildAt(0);
        show_style = (LinearLayout) findViewById(R.id.show_style);
        tvShowStyle = (TextView) show_style.getChildAt(0);
        tvShowStyles = (TextView) show_style.getChildAt(1);
        location = (LinearLayout) findViewById(R.id.location);
        tvLocation = (TextView) location.getChildAt(0);
        shake = (LinearLayout) findViewById(R.id.shake);
        tvShake = (TextView) shake.getChildAt(0);
        voice_toggle = (LinearLayout) findViewById(R.id.voice_toggle);
        tvVoiceToggle = (TextView) voice_toggle.getChildAt(0);
        suggestion = (LinearLayout) findViewById(R.id.suggestion);
        tvSuggestion = (TextView) suggestion.getChildAt(0);
        about_us = (LinearLayout) findViewById(R.id.about_us);
        tvAboutUs = (TextView) about_us.getChildAt(0);
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        numberManage.setOnClickListener(this);
        out_record.setOnClickListener(this);
        show_style.setOnClickListener(this);
        password.setOnClickListener(this);
        suggestion.setOnClickListener(this);
        about_us.setOnClickListener(this);


    }

    @Override
    public void initData() {
        setTitleText("设置");
        tvNumber.setText("账号管理");
        tvPassword.setText("手势密码");
        tvPasswordState.setText("关闭");
        tvOutRecord.setText("导出账单");
        tvShowStyle.setText("明细显示方式");
        tvShowStyles.setText("全部展开");
        tvLocation.setText("地理位置信息展示");
        tvShake.setText("摇一摇反馈");
        tvVoiceToggle.setText("音效开关");
        tvSuggestion.setText("意见反馈");
        tvAboutUs.setText("关于我们");
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.number_manage:
                intent = new Intent(context, NumberManageActivity.class);
                startActivity(intent);
                break;
            case R.id.password:
                intent = new Intent(context, PasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.out_record:
                Toast.makeText(context, "out_record", Toast.LENGTH_SHORT).show();
                break;
            case R.id.show_style:
                Toast.makeText(context, "show_style", Toast.LENGTH_SHORT).show();
                break;
            case R.id.suggestion:
                Toast.makeText(context, "suggestion", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, SuggestionActivity.class);
                startActivity(intent);
                break;
            case R.id.about_us:
                Toast.makeText(context, "about_us", Toast.LENGTH_SHORT).show();
                intent = new Intent(context, AboutUs2Activity.class);
                startActivity(intent);
                break;
        }
    }


}
