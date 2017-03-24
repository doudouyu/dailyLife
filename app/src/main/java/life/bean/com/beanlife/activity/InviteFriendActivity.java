package life.bean.com.beanlife.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cl253.smssdk.lib.SMSSDK;
import com.cl253.smssdk.listener.ICheckVerificationCodeCallBack;
import com.cl253.smssdk.listener.IGetVerificationCodeCallBack;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class InviteFriendActivity extends BaseActivity {

    private TextView tvShow;
    private TextView tvSendMessage;
    private EditText et_number;

    @Override
    public int getLayoutId() {
        return R.layout.layout_invite_friend;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        tvShow = (TextView) findViewById(R.id.tv_scan_show);
        tvSendMessage = (TextView) findViewById(R.id.tv_send_message);
        et_number = (EditText) findViewById(R.id.et_number);
    }

    @Override
    public void initData() {
        setTitleText("联手记邀请");
        tvShow.setOnClickListener(this);
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        tvSendMessage.setOnClickListener(this);
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.tv_scan_show:
                findViewById(R.id.ll_invite_by_phone).setVisibility(View.VISIBLE);
                break;
            case R.id.tv_send_message:
                sendMessage(et_number.getText().toString().trim());
                break;
        }
    }
    private void sendMessage(String phoneNumber) {
        SMSSDK.getVerificationCode(this, phoneNumber, this.getString(R.string.message_content), new IGetVerificationCodeCallBack() {
            @Override
            public void onResultSuccess(String s) {
                //发送成功
                showToast("发送成功");
            }

            @Override
            public void onResultFail(int i, String s) {
                //发送失败
                showToast("发送失败");
            }
        });
    }

}
