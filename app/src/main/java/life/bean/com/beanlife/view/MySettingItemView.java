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

    private TextView tvCenter;
    private TextView tvLeft;
    private ImageView ivIcon;
    private ImageView ivBack;
    private SwitchButton button;
    private View view;

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
        view = LayoutInflater.from(context).inflate(R.layout.my_setting_item, this, true);
        tvLeft = (TextView) view.findViewById(R.id.tv_setting_title);
        tvCenter = (TextView) view.findViewById(R.id.tv_setting_text);
        ivIcon = (ImageView) view.findViewById(R.id.iv_setting_icon);
        ivBack = (ImageView) view.findViewById(R.id.iv_setting_back);
        button = (SwitchButton) view.findViewById(R.id.btn_setting_button);
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
            setLeftText(leftText);
        }
        String centerText = array.getString(R.styleable.settingItem_centerText);
        if (!isicon){
            if (!TextUtils.isEmpty(centerText)){
                setCenterText(centerText);
            }
        }

    }

    public void setCenterText(String centerText) {
        tvCenter.setText(centerText);
    }
    public void setLeftText(String leftText) {
        tvLeft.setText(leftText);
    }
    public boolean getButtonState() {
       boolean state = button.isChecked();
       return state;
    }
}
