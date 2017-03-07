package life.bean.com.beanlife.fragment;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.MainActivity;
import life.bean.com.beanlife.activity.NoticeActivity;
import life.bean.com.beanlife.activity.NumberManageActivity;
import life.bean.com.beanlife.activity.SettingActivity;
import life.bean.com.beanlife.adapter.MyMenuAdapter;
import life.bean.com.beanlife.bean.MenuInfo;

/**
 * 作者 : bean on 2017/2/22/0022.
 * 注释 :
 */
public class MenuFragment extends BaseFragment {
    private ArrayList<MenuInfo> list = new ArrayList<>();
    private ListView menu;
    private int currentPosition;
    private ImageView ivIcon;
    private ImageView ivFresh;
    private ImageView ivFresh1;
    private ImageView ivRing;
    private ImageView ivScan;
    private ImageView ivSetting;
    private Intent intent;

    @Override
    public int getLayoutId() {
        return R.layout.layout_menu;
    }

    @Override
    public void initView(View view) {
        list.add(new MenuInfo("日常生活", R.mipmap.book));
        list.add(new MenuInfo("统计分析", R.mipmap.count));
        list.add(new MenuInfo("联手记账", R.mipmap.red_contact));
        list.add(new MenuInfo("记账提醒", R.mipmap.ring1));
        list.add(new MenuInfo("银行卡包", R.mipmap.card));
        list.add(new MenuInfo("金融服务", R.mipmap.finance));
        list.add(new MenuInfo("意见反馈", R.mipmap.email));
        Log.i("list", list.size() + "");
        menu = (ListView) view.findViewById(R.id.menu_detail);
        ivIcon = (ImageView) view.findViewById(R.id.icon);
        ivFresh1 = (ImageView) view.findViewById(R.id.fresh);
        ivRing = (ImageView) view.findViewById(R.id.ring);
        ivScan = (ImageView) view.findViewById(R.id.scan);
        ivSetting = (ImageView) view.findViewById(R.id.setting);
    }

    @Override
    public void initData() {
        menu.setAdapter(new MyMenuAdapter(list, context));
    }

    @Override
    public void dealCommon() {
        menu.setOnItemClickListener(new MyMenuItemOnClickListener());
        ivIcon.setOnClickListener(this);
        ivFresh1.setOnClickListener(this);
        ivRing.setOnClickListener(this);
        ivScan.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
    }

    private class MyMenuItemOnClickListener implements android.widget.AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //关闭侧滑菜单
            MainActivity activity = (MainActivity) getActivity();
            activity.getDrawerLayout().closeDrawer(activity.getMenuLayout());
            //更改右侧的布局
            currentPosition = position;
            activity.setCurrentPosition(position);
            activity.setTitleText(list.get(position).getTitle());
            activity.setCurrentPagerItem(position);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        //关闭侧滑菜单
        MainActivity activity = (MainActivity) getActivity();
        activity.getDrawerLayout().closeDrawer(activity.getMenuLayout());
        switch (v.getId()) {
            case R.id.icon:
                intent = new Intent(context, NumberManageActivity.class);
                startActivity(intent);
                break;
            case R.id.fresh:
                showShare();
                break;
            case R.id.ring:
                intent = new Intent(context, NoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.scan:
                intent = new Intent(context, NoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.setting:
                intent = new Intent(context, SettingActivity.class);
                startActivity(intent);
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
