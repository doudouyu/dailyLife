package life.bean.com.beanlife.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import life.bean.com.beanlife.R;

/**
 * 作者 : bean on 2017/3/23/0023.
 * 注释 :
 */
public class MyImageView extends RelativeLayout {
    private Context context;
    private RelativeLayout myImageView;
    private ImageView imageViewPictures;
    private ImageView imageViewDelete;

    public MyImageView(Context context) {
        super(context);
        this.context = context;

        initView();
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        myImageView = (RelativeLayout) View.inflate(context, R.layout.my_imageview,null);
        imageViewPictures = (ImageView) myImageView.findViewById(R.id.pictures);
        imageViewDelete = (ImageView) myImageView.findViewById(R.id.delete_picture);
        imageViewDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除自己
                myImageView.setVisibility(View.GONE);
            }
        });

    }
    public void setImageBitmap(Bitmap bm){
        imageViewPictures.setImageBitmap(bm);
        imageViewPictures.setScaleType(ImageView.ScaleType.FIT_XY);
    }

}
