package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.gesture.GestureEditActivity;
import life.bean.com.beanlife.gesture.GestureVerifyActivity;
import life.bean.com.beanlife.utils.Common;
import life.bean.com.beanlife.utils.SharepreferenceUtils;
import life.bean.com.beanlife.utils.SpUtils;
import life.bean.com.beanlife.view.MySettingItemView;

/**
 * 作者 : bean on 2017/2/27/0027.
 * 注释 :
 */
public class Setting2Activity extends BaseActivity {

    private MySettingItemView numberManage;
    private MySettingItemView password;
    private MySettingItemView out_record;
    private MySettingItemView show_style;
    private MySettingItemView location;
    private MySettingItemView shake;
    private MySettingItemView voice_toggle;
    private MySettingItemView suggestion;
    private MySettingItemView about_us;
    private Intent intent;
    private boolean isFist = true;
    private boolean passwordState;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting2;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        numberManage = (MySettingItemView) findViewById(R.id.number_manage);
        password = (MySettingItemView) findViewById(R.id.password);
        out_record = (MySettingItemView) findViewById(R.id.out_record);
        show_style = (MySettingItemView) findViewById(R.id.show_style);
        location = (MySettingItemView) findViewById(R.id.location);
        shake = (MySettingItemView) findViewById(R.id.shake);
        voice_toggle = (MySettingItemView) findViewById(R.id.voice_toggle);
        suggestion = (MySettingItemView) findViewById(R.id.suggestion);
        about_us = (MySettingItemView) findViewById(R.id.about_us);
        passwordState = SharepreferenceUtils.getBooleanSharepreference(context, SpUtils.DAILY_LIFE,0,SpUtils.SET_PASSWORD);
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
    protected void onStart() {
        super.onStart();
        if (passwordState == false){
            password.setCenterText("开启");
        }else {
            password.setCenterText("关闭");
        }
    }

    @Override
    public void initData() {
        setTitleText("设置");
        if (passwordState == false){
            password.setCenterText("开启");
        }else {
            password.setCenterText("关闭");
        }
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
                //判断当前开关是否打开

                if (!passwordState){
                    intent = new Intent(context, GestureEditActivity.class);
                    startActivity(intent);
                }else {
                    intent = new Intent(context, PasswordActivity.class);
                    startActivity(intent);
                }

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
