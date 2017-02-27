package life.bean.com.beanlife.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import life.bean.com.beanlife.R;

/**
 * 作者 : bean on 2017/2/23/0023.
 * 注释 :
 */
public class EditView extends RelativeLayout {
    private Context context;
    private ImageView ivIcon;
    private TextView tvDecribe;
    private CheckBox cbChecked;
    private ImageView ivDelete;

    public EditView(Context context) {
        super(context);
        this.context = context;
        initView();

    }


    public EditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public EditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.edit_setting, this);
        ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        tvDecribe = (TextView) view.findViewById(R.id.tv_describe);
        cbChecked = (CheckBox) view.findViewById(R.id.cb_checked);
        ivDelete = (ImageView) view.findViewById(R.id.iv_delete);
    }

    /**
     * 设置图标
     */
    private void setIcon(Integer iconId) {
        ivIcon.setImageResource(iconId);
    }

    /**
     * 设置描述
     */
    private void setDescribe(String describe) {
        tvDecribe.setText(describe);
    }

    /**
     * 设置是否被选中
     */
    private void setChecked(boolean isChecked) {
        if (isChecked) {
            cbChecked.setChecked(true);
        } else {
            cbChecked.setChecked(false);
        }
    }

    /**
     * 设置删除图标  预留后期完善
     */
    private void setDeleteIcon() {

    }

}
