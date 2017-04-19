package life.bean.com.beanlife.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import life.bean.com.beanlife.R;

/**
 * 作者 : bean on 2017/4/19/0019.
 * 注释 :
 */
public class MyTextView extends LinearLayout{
    public MyTextView(Context context) {
        super(context);
        initView(context, null);
    }


    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
       View view = LayoutInflater.from(context).inflate(R.layout.my_textview, this, true);
       TextView tvDetail = (TextView) view.findViewById(R.id.text);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.myView);//TypedArray是一个数组容器
        String text = a.getString(R.styleable.myView_beanText);
        if (!TextUtils.isEmpty(text)){
            tvDetail.setText(text);
        }
        a.recycle();
    }
}
