package life.bean.com.beanlife.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.adapter.MyPagerAdapter;
import life.bean.com.beanlife.fragment.BaseFragment;
import life.bean.com.beanlife.fragment.InComeFragment;
import life.bean.com.beanlife.fragment.PayFragment;
import life.bean.com.beanlife.presenter.IShortEditPresenter;
import life.bean.com.beanlife.utils.RequestFlag;
import life.bean.com.beanlife.view.IShortEditView;

/**
 * 作者 : bean on 2017/2/23/0023.
 * 注释 :
 */
public class ShortEditorActivity extends BaseActivity implements IShortEditView{

    private static final int IMAGE = 1;
    private static final int CAMERA = 2;
    private ImageView iv_healthy;
    private TextView tvChooseMoneyCategory;
    private ViewPager vp_category;
    private ArrayList<BaseFragment> list = new ArrayList<>();
    private TextView tv_healthy;
    private TextView tv_calendar;
    private LinearLayout ll_add_member;
    private RelativeLayout rl_choose_pay_mode;
    private LinearLayout ll_camera;
    private RelativeLayout rl_location;
    private TextView tv_add_member;
    private TextView tv_choose_pay_mode;
    private int flag = 1;//用来判断是哪个条目弹出来的对话框
    private ArrayList<String> list2;
    private ArrayList<String> list1;
    private ArrayAdapter adapter;
    private WindowManager windowManager;
    private Display display;
    private ImageView ivBack;
    private TextView expend;
    private TextView inCome;
    private TextView tv_save;
    private IShortEditPresenter presenter = new IShortEditPresenter(this);
    @Override
    public int getLayoutId() {
        return R.layout.layout_short_editor;
    }

    @Override
    public void initView() {
        iv_healthy = (ImageView) findViewById(R.id.iv_healthy);
        tv_healthy = (TextView) findViewById(R.id.tv_healthy);
        tvChooseMoneyCategory = (TextView) findViewById(R.id.tv_choose_money_category);
        vp_category = (ViewPager) findViewById(R.id.vp_category);
        tv_calendar = (TextView) findViewById(R.id.tv_calendar);
        rl_location = (RelativeLayout) findViewById(R.id.rl_location);
        ll_add_member = (LinearLayout) findViewById(R.id.ll_add_member);
        rl_choose_pay_mode = (RelativeLayout) findViewById(R.id.rl_choose_pay_mode);
        ll_camera = (LinearLayout) findViewById(R.id.ll_camera);
        tv_add_member = (TextView) findViewById(R.id.tv_add_member);
        tv_choose_pay_mode = (TextView) findViewById(R.id.tv_pay_mode);
        windowManager = getWindowManager();
        display = windowManager.getDefaultDisplay();
        //顶部标题栏
        ivBack = (ImageView) findViewById(R.id.iv_back);
        expend = (TextView) findViewById(R.id.expend);
        inCome = (TextView) findViewById(R.id.income);
        //底部
        tv_save = (TextView) findViewById(R.id.tv_save);
    }

    @Override
    public void initData() {
        list.add(new PayFragment());
        list.add(new InComeFragment());
        vp_category.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), list));
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        iv_healthy.setOnClickListener(this);
        tv_healthy.setOnClickListener(this);
        tvChooseMoneyCategory.setOnClickListener(this);
        tv_calendar.setOnClickListener(this);
        ll_add_member.setOnClickListener(this);
        rl_choose_pay_mode.setOnClickListener(this);
        ll_camera.setOnClickListener(this);
        rl_location.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        expend.setOnClickListener(this);
        inCome.setOnClickListener(this);
        vp_category.addOnPageChangeListener(new OnMyPageChangeListener());
        tv_save.setOnClickListener(this);
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.tv_save:
                //// TODO: 2017/3/31/0031 提交数据至服务器
                showToast("提交成功");
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.expend:
                vp_category.setCurrentItem(0);
                setPageOne();
                break;
            case R.id.income:
                vp_category.setCurrentItem(1);
                setPageTwo();
                break;
            case R.id.iv_healthy:
            case R.id.tv_healthy:
                if (vp_category.getVisibility() == View.GONE) {
                    vp_category.setVisibility(View.VISIBLE);
                } else if (vp_category.getVisibility() == View.VISIBLE) {
                    vp_category.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_choose_money_category:
                presenter.showKeyBord();
                break;
            case R.id.tv_calendar:
                presenter.ToCalendarActivity("1");

                break;
            case R.id.rl_location:
                presenter.ToCalendarActivity("2");

                break;
            case R.id.ll_add_member:
                flag = 1;
                presenter.showBottomDialog(RequestFlag.CHOOSE_MEMBER);

                break;
            case R.id.rl_choose_pay_mode:
                flag = 2;
                presenter.showBottomDialog(RequestFlag.CHOOSE_PAY_MODE);

                break;
            case R.id.ll_camera:
                showCamera();
                break;
            default:
                break;
        }
    }

    /**
     * 相册选择弹出框
     */
    private void showCamera() {
        final Dialog dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        View view = View.inflate(context, R.layout.layout_dialog_camera, null);
        view.findViewById(R.id.tv_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_pictures).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();

        lp.width = (int) (display.getWidth() * 0.8);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

//    /**
//     * 底部弹出框
//     *
//     * @param list2
//     */
//    private void showBottomDialog(final ArrayList<String> list2) {
//        final Dialog dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
//        View view = View.inflate(context, R.layout.layout_bottom_dialog, null);
//        dialog.setContentView(view);
//        final TextView dialog_title_name = (TextView) view.findViewById(R.id.dialog_title_name);
//        TextView tv_add = (TextView) view.findViewById(R.id.tv_add);
//        ListView lv_dialog_list_view = (ListView) view.findViewById(R.id.lv_dialog_list_view);
//        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list2);
//        lv_dialog_list_view.setAdapter(adapter);
//        if (flag == 1) {
//            dialog_title_name.setText("选择成员");
//        } else {
//            dialog_title_name.setText("选择支付方式");
//        }
//        tv_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(context, AddRememberActivity.class);
//                if (flag == 1) {
//                    intent.putExtra("hint", "1");
//                } else {
//                    intent.putExtra("hint", "2");
//                }
//                startActivityForResult(intent, 1);
//            }
//        });
//        lv_dialog_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String title_name = list2.get(position);
//                if (flag == 1) {
//                    tv_add_member.setText(title_name);
//
//                } else {
//                    tv_choose_pay_mode.setText(title_name);
//                }
//                dialog.dismiss();
//            }
//        });
//        dialog.show();//显示对话框
//
//        Window window = dialog.getWindow();
////        window.getDecorView().setPadding(0,0,0,0);
//        window.setGravity(Gravity.BOTTOM);
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//        lp.width = WindowManager.LayoutParams.FILL_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setAttributes(lp);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            String name = data.getStringExtra("name");
            if (flag == 1) {
                list1.add(name);
            } else {
                list2.add(name);
            }
            adapter.notifyDataSetChanged();
        }
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = this.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 2) {
            //判断sd卡是否挂载成功
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                Log.i("TestFile", "SD 卡不能用");
                //如果sd卡不能用，退出，不做任何操作，提示用户sd卡不能用
                showToast("设备SD卡不能使用，请检查");
                return;
            }
            new DateFormat();
            String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
            //创建一个输出流把图片写入sd卡中
            FileOutputStream b = null;
            File file = new File("/sdcard/Image/");
            file.mkdirs();// 创建文件夹
            String fileName = "/sdcard/Image/" + name;

            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    b.flush();
                    b.close();
                    showImage(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void showImage(String imagePath) {

        //首先把路径转换为bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        //创建一个imageView
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(display.getWidth() / 3, display.getWidth() / 3);
        params.leftMargin = 10;
        params.topMargin = 10;
        imageView.setLayoutParams(params);
        imageView.setImageBitmap(bitmap);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent()
                showToast("点我");
            }
        });
        ll_camera.addView(imageView, 1);
        ll_camera.getChildAt(2).setVisibility(View.GONE);

    }

    /**
     * 弹出来自定义键盘
     */
    private void showKeyBord() {
    }

    public void setIvIcon(Integer icon) {
        iv_healthy.setImageResource(icon);
    }

    public void setIvText(String name) {
        tv_healthy.setText(name);
    }

    private class OnMyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0){
                setPageOne();

            }else {
                setPageTwo();

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void setPageTwo() {
        inCome.setBackgroundColor(getResources().getColor(R.color.pink));
        inCome.setTextColor(getResources().getColor(R.color.white));
        expend.setBackgroundColor(getResources().getColor(R.color.white));
        expend.setTextColor(getResources().getColor(R.color.pink));
    }

    private void setPageOne() {
        expend.setBackgroundColor(getResources().getColor(R.color.pink));
        expend.setTextColor(getResources().getColor(R.color.white));
        inCome.setBackgroundColor(getResources().getColor(R.color.white));
        inCome.setTextColor(getResources().getColor(R.color.pink));
    }

    @Override
    public void setAddMemberText(String title_name) {
        tv_add_member.setText(title_name);
    }

    @Override
    public void setPayModeText(String title_name) {
        tv_choose_pay_mode.setText(title_name);
    }

    @Override
    public void startMyActivity(Intent intent, int what) {
        startActivityForResult(intent,what);
    }
}
