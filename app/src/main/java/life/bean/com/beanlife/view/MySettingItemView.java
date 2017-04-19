package life.bean.com.beanlife.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import life.bean.com.beanlife.R;

/**
 * 作者 : bean on 2017/4/19/0019.
 * 注释 :设置界面的子条目
 */
public class MySettingItemView extends LinearLayout{
    public MySettingItemView(Context context) {
        super(context);
        initView(context,null);
    }

    public MySettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    public MySettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_setting_item, this, true);
        TextView tvLeft = (TextView) view.findViewById(R.id.tv_setting_title);
        TextView tvCenter = (TextView) view.findViewById(R.id.tv_setting_text);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_setting_icon);
        ImageView ivBack = (ImageView) view.findViewById(R.id.iv_setting_back);
        SwitchButton button = (SwitchButton) view.findViewById(R.id.btn_setting_button);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.settingItem);
        if (array==null){
            return;
        }
        boolean isicon = array.getBoolean(R.styleable.settingItem_isIcon,false);
        boolean isButton =  array.getBoolean(R.styleable.settingItem_isButton,false);
        if (isicon){
            //说明是图片，就隐藏TextView
            ivIcon.setVisibility(View.VISIBLE);
            tvCenter.setVisibility(View.INVISIBLE);
        }else {
            ivIcon.setVisibility(View.INVISIBLE);
            tvCenter.setVisibility(View.VISIBLE);
        }
        if (isButton){
            button.setVisibility(View.VISIBLE);
            ivBack.setVisibility(View.INVISIBLE);
        }else {
            button.setVisibility(View.INVISIBLE);
            ivBack.setVisibility(View.VISIBLE);
        }
        String leftText = array.getString(R.styleable.settingItem_leftText);
        if (!TextUtils.isEmpty(leftText)){
            tvLeft.setText(leftText);
        }
        String centerText = array.getString(R.styleable.settingItem_centerText);
        if (!isicon){
            if (!TextUtils.isEmpty(centerText)){
                tvCenter.setText(centerText);
            }
        }

    }
}
