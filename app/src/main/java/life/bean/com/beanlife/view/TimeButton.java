package life.bean.com.beanlife.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * 作者 : bean on 2017/3/21/0021.
 * 注释 :
 */
public class TimeButton extends Button implements View.OnClickListener {
    private TimeButton timeButon;
    private String beforeText;
    private boolean isFirstClick = true;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:

                    break;
            }
        }
    };
    private String afterText;
    private long disTime;
    private long time;

    public TimeButton(Context context) {
        super(context);
        initView();
    }

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TimeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.timeButon = this;
        timeButon.setText(beforeText);
        timeButon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (isFirstClick) {
            //如果是第一次点击，就更改显示文本
            timeButon.setText(afterText);
            isFirstClick = false;
            time -= disTime;
            handler.sendEmptyMessage(0);
        } else {
            if (disTime <= 60 * 1000) {
                handler.sendEmptyMessageDelayed(0, disTime);
            }

        }
    }
}
