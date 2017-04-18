package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/28/0028.
 * 注释 :
 */
public class AboutUsActivity extends BaseActivity {

    private LinearLayout common_problem;
    private LinearLayout check_update;
    private LinearLayout recommend_friend;
    private LinearLayout about_us;

    @Override
    public int getLayoutId() {
        return R.layout.layout_about_us;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        common_problem = (LinearLayout) findViewById(R.id.common_problem);
        check_update = (LinearLayout) findViewById(R.id.check_update);
        recommend_friend = (LinearLayout) findViewById(R.id.recommend_friend);
        about_us = (LinearLayout) findViewById(R.id.about_us);
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        common_problem.setOnClickListener(this);
        check_update.setOnClickListener(this);
        recommend_friend.setOnClickListener(this);
        about_us.setOnClickListener(this);
    }

    @Override
    public void initData() {
        setTitleText("关于我们");
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        Intent intent = new Intent(AboutUsActivity.this, AboutUsDetailActivity.class);
        switch (v.getId()) {

            case R.id.common_problem:
                intent.putExtra("titleName", "常见问题");
                startActivity(intent);
                break;

            case R.id.about_us:
                intent.putExtra("titleName", "关于微记账");
                startActivity(intent);
                break;
            case R.id.check_update:
                // Todo 调用服务器，判断是否是最新版本
                showToast("已经是最新版本了");
                break;
            case R.id.recommend_friend:
                showShare();
                break;
            default:
                break;
        }

    }



    private void showShare() {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("玉树临风美少年，揽镜自顾夜不眠");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://tieba.baidu.com/p/4320072936");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是美女小豆豆");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://tieba.baidu.com/p/4320072936");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("这是我开发的app");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://tieba.baidu.com/p/4320072936");

// 启动分享GUI
        oks.show(context);
    }


}
