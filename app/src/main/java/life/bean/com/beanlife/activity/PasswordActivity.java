package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.bean.PasswordBean;
import life.bean.com.beanlife.gesture.GestureEditActivity;
import life.bean.com.beanlife.gesture.GestureVerifyActivity;
import life.bean.com.beanlife.utils.Common;
import life.bean.com.beanlife.utils.IntentUtils;
import life.bean.com.beanlife.utils.SharepreferenceUtils;
import life.bean.com.beanlife.utils.SpUtils;
import life.bean.com.beanlife.view.SwitchButton;

/**
 * 作者 : bean on 2017/2/28/0028.
 * 注释 :
 */
public class PasswordActivity extends BaseActivity{

    private SwitchButton button;
    private RelativeLayout layout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_password;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        button = (SwitchButton) findViewById(R.id.password_state);
        layout = (RelativeLayout) findViewById(R.id.password_update);

    }

    @Override
    public void initData() {
        setTitleText("手势密码");
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,GestureVerifyActivity.class);
                intent.putExtra("update","1");
                startActivity(intent);
            }
        });
        button.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked){
                    SharepreferenceUtils.setBooleanSharepreference(context, SpUtils.DAILY_LIFE,0,SpUtils.SET_PASSWORD,true);
                }else {
                    SharepreferenceUtils.setBooleanSharepreference(context,SpUtils.DAILY_LIFE,0,SpUtils.SET_PASSWORD,false);
                    layout.setVisibility(View.GONE);
                }
                IntentUtils.startActivity(GestureVerifyActivity.class);
            }
        });
    }

}
