package life.bean.com.beanlife.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.myInterface.PermissionRequestListener;
import life.bean.com.beanlife.utils.ActivityManger;
import life.bean.com.beanlife.utils.Common;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public static Context context;
    public ImageView titleIcon;
    public TextView titleText;
    public ImageView titleSearch;
    private static PermissionRequestListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        ActivityManger.add(this);
        context = this;
        initView();
        dealCommon();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManger.remove(this);
    }

    public void dealCommon() {
        View view = findViewById(R.id.title_view);
        if (view != null) {
            titleIcon = (ImageView) view.findViewById(R.id.selected);
            titleText = (TextView) view.findViewById(R.id.left);
            titleSearch = (ImageView) view.findViewById(R.id.iv_search);
            if (Common.iconId == 1) {
                titleIcon.setImageResource(R.mipmap.category);
            } else if (Common.iconId == 2) {
                titleIcon.setImageResource(R.mipmap.back);
            }
            if (Common.titleSearchId == 1) {
                titleSearch.setVisibility(View.GONE);
            } else if (Common.titleSearchId == 2) {
                titleSearch.setVisibility(View.VISIBLE);
                titleSearch.setOnClickListener(this);
            }
            titleIcon.setOnClickListener(this);
        }
    }

    public void setTitleText(String text) {
        if (titleText != null) {
            titleText.setText(text);
        }

    }

    public abstract int getLayoutId();


    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selected:
                if (Common.iconId == 1) {
                    onInnerClick(v);
                } else if (Common.iconId == 2) {
                    finish();
                }
                break;
            case R.id.iv_search:
                if (Common.titleSearchId == 2) {
                    Intent intent = new Intent(this, SearchActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                onInnerClick(v);
                break;
        }
    }

    public void onInnerClick(View v) {

    }

    public void showToast(String toastDetail) {
        Toast.makeText(this, toastDetail, Toast.LENGTH_SHORT).show();
    }

    public static void requestRuntimePermission(String[] permissions, PermissionRequestListener listener) {
        //获取当前的栈顶activity
        Activity topActivity = ActivityManger.getTopActivity();
        if (topActivity == null) {
            return;
        }
        mListener = listener;
        //创建一个集合用来放置未申请到的权限
        List<String> permissionList = new ArrayList();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
                //说明权限被拒绝,判断用户是否拒绝再次提醒
                if (ActivityCompat.shouldShowRequestPermissionRationale(topActivity, permission)) {
                    Toast.makeText(context, "您已经关闭权限" + permission + "请您更改设置！", Toast.LENGTH_SHORT).show();
                } else {
                    // 重新申请
                    topActivity.requestPermissions(new String[]{permission}, 1);
                }

            }
        }
        if (permissionList.isEmpty()) {
            //说明权限已经全部获取
            listener.onGrantedPermission();
        } else {
            topActivity.requestPermissions(permissionList.toArray(new String[permissionList.size()]), 1);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                List<String> permissionList = new ArrayList<>();
                if (grantResults != null && grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        int result = grantResults[i];
                        String permission = permissions[i];
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            permissionList.add(permission);
                        }
                    }
                    if (permissionList.isEmpty()) {
                        mListener.onGrantedPermission();
                    } else {
                        mListener.onDeniedPermission(permissionList);
                    }
                }

                break;
            default:
                break;
        }
    }
}
