package life.bean.com.beanlife.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.MainActivity;
import life.bean.com.beanlife.activity.NumberManageActivity;
import life.bean.com.beanlife.utils.DisplayUtil;
import life.bean.com.beanlife.view.MyImageView;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class SuggestionFragment extends BaseFragment {

    private ImageView ivVoice;
    private ImageView addPictures;
    private TextView submit_info;
    private EditText etInputInfo;
    private LinearLayout ll_pictures;
    private RecognizerListener mRecoListener = new RecognizerListener() {
        //听写结果回调接口(返回Json格式结果，用户可参见附录12.1)；
        //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
        //关于解析Json的代码可参见MscDemo中JsonParser类；
        //isLast等于true时会话结束。
        //音量值0~30
        @Override
        public void onVolumeChanged(int i, byte[] bytes) {

        }

        //开始录音
        @Override
        public void onBeginOfSpeech() {

        }

        //结束录音
        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {

        }

        //会话发生错误回调接口
        @Override
        public void onError(SpeechError speechError) {

        }

        //扩展用接口
        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };
    private int width;

    @Override
    public int getLayoutId() {
        return R.layout.layout_suggestion;
    }

    @Override
    public void initView(View view) {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        ivVoice = (ImageView) view.findViewById(R.id.voice_toggle);
        addPictures = (ImageView) view.findViewById(R.id.add_pictures);
        submit_info = (TextView) view.findViewById(R.id.submit_info);
        etInputInfo = (EditText) view.findViewById(R.id.et_input_info);
        ll_pictures = (LinearLayout) view.findViewById(R.id.ll_pictures);
    }

    @Override
    public void initData() {

    }

    @Override
    public void dealCommon() {
        ivVoice.setOnClickListener(this);
        submit_info.setOnClickListener(this);
        addPictures.setOnClickListener(this);
    }

    //调用系统相册-选择图片
    private static final int IMAGE = 1;

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.voice_toggle:
                checkVoicePermission();
                break;
            case R.id.submit_info:
                showToast("提交");
                break;
            case R.id.add_pictures:
                //
                //
                //
                // todo 写一个底部菜单来调用相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
                break;
            default:
                break;
        }
    }

    private void checkVoicePermission() {
        //有权限就弹出来一个对话框
        showToast("录音");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_voice,null);
        builder.setView(view);
        builder.setTitle("准备录音");

        builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                startRecord();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        //todo 检查权限，成功获取权限后调用录音功能
    }

    private void startRecord() {
        //1.创建SpeechRecognizer对象，第二个参数：本地听写时传InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(context, null);
//2.设置听写参数，详见《科大讯飞MSC API手册(Android)》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
//3.开始听写
        mIat.startListening(mRecoListener);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            MainActivity activity = (MainActivity) getActivity();
            Cursor c = activity.getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
    }

    //加载图片
    private void showImage(String imagePath) {
        int childCount = ll_pictures.getChildCount();
        if (childCount <= 3) {
            Bitmap bm = BitmapFactory.decodeFile(imagePath);
            final ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //给图片添加一个间隔
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width/3-20,width/3-20);
            params.rightMargin = 10;
            params.leftMargin = 10;
            imageView.setLayoutParams(params);
            imageView.setImageBitmap(bm);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView.setVisibility(View.GONE);
                    ll_pictures.removeView(imageView);
                    if (ll_pictures.getChildCount() <= 3) {
                        addPictures.setVisibility(View.VISIBLE);
                    }
                }
            });
            ll_pictures.addView(imageView, childCount - 1);
            if (ll_pictures.getChildCount() > 3) {
                addPictures.setVisibility(View.GONE);
                showToast("最多只能三张哦！");
            }

        }
    }

//    private RecognizerListener mRecoListener = new RecognizerListener() {
//        //听写结果回调接口(返回Json格式结果，用户可参见附录12.1)；
//        //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
//        //关于解析Json的代码可参见MscDemo中JsonParser类；
//        //isLast等于true时会话结束。
//        public void onResult(RecognizerResult results, boolean isLast) {
//            Log.d("Result:", results.getResultString());
//        }
//
//        //会话发生错误回调接口
//        public void onError(SpeechError error) {
//            error.getPlainDescription(true) {
//                //获取错误码描述
//            }
//        }
//
//        //开始录音
//        public void onBeginOfSpeech() {
//        }
//
//        //音量值0~30
//        public void onVolumeChanged(int volume) {
//        }
//
//        //结束录音
//        public void onEndOfSpeech() {
//        }
//
//        //扩展用接口
//        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
//        }
//    };
}
