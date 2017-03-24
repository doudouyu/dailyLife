package life.bean.com.beanlife.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.MainActivity;
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

    @Override
    public int getLayoutId() {
        return R.layout.layout_suggestion;
    }

    @Override
    public void initView(View view) {
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
                //调用相册
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
    private void showImage(String imagePath){
        int childCount = ll_pictures.getChildCount();

        if (childCount<=3){
            Bitmap bm = BitmapFactory.decodeFile(imagePath);
            final ImageView imageView = new ImageView(context);
            //给图片添加一个间隔
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100,100);
            params.rightMargin = 10;
            params.leftMargin = 10;
            imageView.setLayoutParams(params);
            imageView.setImageBitmap(bm);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageView.setVisibility(View.GONE);
                    ll_pictures.removeView(imageView);
                    if (ll_pictures.getChildCount()<=3){
                        addPictures.setVisibility(View.VISIBLE);
                    }
                }
            });
            ll_pictures.addView(imageView,childCount-1);
            if (ll_pictures.getChildCount()>3){
                addPictures.setVisibility(View.GONE);
                showToast("最多只能三张哦！");
            }

        }
    }


}
