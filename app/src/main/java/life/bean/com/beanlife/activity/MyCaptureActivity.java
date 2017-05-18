package life.bean.com.beanlife.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;
import life.bean.com.beanlife.utils.ImageUtil;

/**
 * 作者 : bean on 2017/5/17/0017.
 * 注释 :
 */
public class MyCaptureActivity extends BaseActivity {

    private static final int REQUEST_IMAGE = 2;
    private TextView tv_picture;

    @Override
    public int getLayoutId() {
        return R.layout.my_scanner;
    }

    @Override
    public void initView() {
        Common.titleSearchId = 1;
        Common.iconId = 2;
        CaptureFragment captureFragment = new CaptureFragment();
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.my_fl_zxing_container, captureFragment).commit();
        tv_picture = (TextView) findViewById(R.id.tv_picture);
    }

    @Override
    public void initData() {
        setTitleText("扫一扫");
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        tv_picture.setOnClickListener(this);
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        if(v.getId() == R.id.tv_picture){
            //开启相册界面
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE);
        }

    }

    /**
     * 二维码解析回调函数
     */
    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            MyCaptureActivity.this.setResult(RESULT_OK, resultIntent);
            MyCaptureActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            MyCaptureActivity.this.setResult(RESULT_OK, resultIntent);
            MyCaptureActivity.this.finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri uri = data.getData();
            try {
                CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        Toast.makeText(MyCaptureActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        MyCaptureActivity.this.finish();
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        Toast.makeText(MyCaptureActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}