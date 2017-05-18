package life.bean.com.beanlife.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;
import life.bean.com.beanlife.utils.SharepreferenceUtils;
import life.bean.com.beanlife.utils.SpUtils;

/**
 * 作者 : bean on 2017/2/27/0027.
 * 注释 :
 */
public class NumberManageActivity extends BaseActivity {


    private TextView tvOutLogin;
    private RelativeLayout rlName;
    private RelativeLayout rlPhone;
    private RelativeLayout rlEmail;
    private RelativeLayout rlEmail1;
    private RelativeLayout rlQQ;
    private RelativeLayout rlWeChat;
    private Intent intent;
    private TextView tvSure;
    private TextView tvNumber;
    private TextView tv_name;
    private String userName;
    private TextView tvNumber1;
    private ImageView iv_icon_change;
    private WindowManager windowManager;
    public static final int IMAGE = 3;
    private static final int CAMERA = 4;
    @Override
    public int getLayoutId() {
        return R.layout.activity_number_manage;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        tvOutLogin = (TextView) findViewById(R.id.tv_out_login);
        rlName = (RelativeLayout) findViewById(R.id.rl_name);
        rlPhone = (RelativeLayout) findViewById(R.id.rl_phone);
        rlEmail1 = (RelativeLayout) findViewById(R.id.rl_email);
        rlQQ = (RelativeLayout) findViewById(R.id.rl_qq);
        rlWeChat = (RelativeLayout) findViewById(R.id.rl_we_chat);
        tv_name = (TextView) findViewById(R.id.tv_name);
        userName = SharepreferenceUtils.getSharepreference(this, SpUtils.DAILY_LIFE, 0, SpUtils.USER_NAME_STRING);
        iv_icon_change = (ImageView) findViewById(R.id.iv_icon_change);
    }

    @Override
    public void initData() {
        setTitleText("账号管理");
        tv_name.setText(userName);
        tvOutLogin.setOnClickListener(this);
        rlName.setOnClickListener(this);
        rlPhone.setOnClickListener(this);
        rlEmail1.setOnClickListener(this);
        rlQQ.setOnClickListener(this);
        rlWeChat.setOnClickListener(this);
        iv_icon_change.setOnClickListener(this);
        String address = SharepreferenceUtils.getSharepreference(context,"pictureAddress",0,SpUtils.PICTUREADDRESS);
        if (address!= null&&address.length()>1){
            showImage(address);
        }

    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.rl_name:
                Toast.makeText(context, "名字", Toast.LENGTH_SHORT).show();
                showMyDialog("请输入昵称");
                break;
            case R.id.rl_phone:
                intent = new Intent(context, NumberBindActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.rl_email:
                intent = new Intent(context, NumberBindActivity.class);
                intent.putExtra("type", "2");
                startActivity(intent);
                break;
            case R.id.rl_qq:
                Toast.makeText(context, "qq", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_we_chat:
                Toast.makeText(context, "微信", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_out_login:
                Toast.makeText(context, "退出登录", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.tv_sure:
//                break;
            case R.id.iv_icon_change:
                showChooseDialog();
                break;
        }
    }

    //选择照片途径
    private void showChooseDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(NumberManageActivity.this);
        final View viewDia = LayoutInflater.from(NumberManageActivity.this).inflate(R.layout.layout_choose_dialog, null);
        builder.setTitle("请选择");
        builder.setView(viewDia);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        TextView tvPicture = (TextView) viewDia.findViewById(R.id.tv_picture);
        TextView tvCamera = (TextView) viewDia.findViewById(R.id.tv_camera);
        tvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //跳转至相机界面
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA);
                dialog.dismiss();
            }
        });
        tvPicture.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //跳转至相册界面
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
                dialog.dismiss();
            }
        });
        dialog.show();
        windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();

        lp.width = (int) (display.getWidth() * 0.7);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    private void showMyDialog(String name) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(NumberManageActivity.this);
        final View viewDia = LayoutInflater.from(NumberManageActivity.this).inflate(R.layout.layout_dialog, null);
        builder.setTitle("请输入昵称");
        builder.setView(viewDia);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        TextView tvSure = (TextView) viewDia.findViewById(R.id.tv_sure);
        tvNumber1 = (TextView) viewDia.findViewById(R.id.tv_number);
        final EditText diaInput = (EditText) viewDia.findViewById(R.id.et_input_name);
        diaInput.addTextChangedListener(new OnMyTextChangeListener());
        if (diaInput.getText().toString().trim().length() <= 10) {
            tvSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String inputString = diaInput.getText().toString().trim();
                    if (inputString.length() <= 0) {
                        Toast.makeText(context, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    } else if (inputString.length() <= 10) {
                        tv_name.setText(inputString);
                        SharepreferenceUtils.setSharepreference(context, SpUtils.DAILY_LIFE, 0, SpUtils.USER_NAME_STRING, inputString);
                        dialog.dismiss();
                    } else if (inputString.length() > 10) {
                        diaInput.setText("");
                        Toast.makeText(context, "不能超过十个字符", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        dialog.show();
    }

    private class OnMyTextChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() <= 10) {
                tvNumber1.setText(s.length() + "");
            } else {
                tvNumber1.setText("10");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //如果是相机返回就保存拍摄的照片
        if (requestCode == CAMERA&&resultCode == Activity.RESULT_OK&&data!=null){
            //检测sd卡是否挂在成功
            String sdStates = Environment.getExternalStorageState();
            if ( !sdStates.equals(Environment.MEDIA_MOUNTED) ){
                showToast("找不到sd卡");
                return;
            }
            //给图片命名
            String name = DateFormat.format("yyyyMMdd_hhmmss",System.currentTimeMillis())+".jpg";
            //创建一个文件夹
            File file = new File("/sdcard/Image/");
            file.mkdirs();
            String fileName = "/sdcard/Image/" + name;
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                SharepreferenceUtils.setSharepreference(context,"pictureAddress",0,SpUtils.PICTUREADDRESS,fileName);
                showImage(fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    outputStream.flush();
                    outputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }else if (requestCode == IMAGE&&resultCode == Activity.RESULT_OK&&data!=null){
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = this.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            SharepreferenceUtils.setSharepreference(context,"pictureAddress",0,SpUtils.PICTUREADDRESS,imagePath);
            showImage(imagePath);
            c.close();
        }
    }
    private void showImage(String imagePath) {
        //首先把路径转换为bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        iv_icon_change.setImageBitmap(bitmap);
    }
}
