package life.bean.com.beanlife.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.MainActivity;
import life.bean.com.beanlife.activity.MyCaptureActivity;
import life.bean.com.beanlife.activity.NoticeActivity;
import life.bean.com.beanlife.activity.NumberManageActivity;
import life.bean.com.beanlife.activity.Setting2Activity;
import life.bean.com.beanlife.adapter.MyMenuAdapter;
import life.bean.com.beanlife.bean.MenuInfo;

/**
 * 作者 : bean on 2017/2/22/0022.
 * 注释 :
 */
public class MenuFragment extends BaseFragment {
    private static final int REQUEST_CODE = 1;
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
    private String titleText;

    @Override
    public int getLayoutId() {

        return R.layout.layout_menu;
    }

    @Override
    public void initView(View view) {

        initList();
        Log.i("list", list.size() + "");
        menu = (ListView) view.findViewById(R.id.menu_detail);
        ivIcon = (ImageView) view.findViewById(R.id.icon);
        ivFresh1 = (ImageView) view.findViewById(R.id.fresh);
        ivRing = (ImageView) view.findViewById(R.id.ring);
        ivScan = (ImageView) view.findViewById(R.id.scan);
        ivSetting = (ImageView) view.findViewById(R.id.setting);
    }

    private void initList() {
        list.add(new MenuInfo("日常生活", R.mipmap.book));
        list.add(new MenuInfo("统计分析", R.mipmap.count));
        list.add(new MenuInfo("联手记账", R.mipmap.red_contact));
        list.add(new MenuInfo("记账提醒", R.mipmap.ring1));
        list.add(new MenuInfo("银行卡包", R.mipmap.card));
        list.add(new MenuInfo("金融服务", R.mipmap.finance));
        list.add(new MenuInfo("意见反馈", R.mipmap.email));
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

    public String getTitleText(int position) {
        if (list.size() <= 0) {
            initList();
            if (titleText == null) {
                titleText = list.get(position).getTitle();
            }
        }

        return titleText;
    }

    private class MyMenuItemOnClickListener implements android.widget.AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //关闭侧滑菜单
            MainActivity activity = (MainActivity) getActivity();
            activity.getDrawerLayout().closeDrawer(activity.getMenuLayout());
            //更改右侧的布局
            titleText = list.get(position).getTitle();
            currentPosition = position;
            activity.setCurrentPosition(position);
            activity.setTitleText(titleText);
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

                break;
            case R.id.ring:
                intent = new Intent(context, NoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.scan:
                openScan();
                break;
            case R.id.setting:
                intent = new Intent(context, Setting2Activity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void openScan() {
        super.openScan();
        intent = new Intent(context, MyCaptureActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if(data ==null){
                return;
            }
            Bundle bundle = data.getExtras();
            if (bundle == null){
                return;
            }
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS){
                String result = CodeUtils.RESULT_STRING;
                showToast("二维码解析结果："+result);
            }else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED){
                showToast("二维码解析失败");
            }
        }
    }
}
