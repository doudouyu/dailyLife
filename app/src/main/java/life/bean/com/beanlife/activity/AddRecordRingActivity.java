package life.bean.com.beanlife.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;
import life.bean.com.beanlife.utils.SharepreferenceUtils;
import life.bean.com.beanlife.utils.SpUtils;


/**
 * 作者 : bean on 2017/3/27/0027.
 * 注释 :
 */
public class AddRecordRingActivity extends BaseActivity{

    private LinearLayout ll_remind_name;
    private LinearLayout ll_remind_date;
    private LinearLayout ll_remind_cycle;
    private LinearLayout ll_remind_died_line;
    private TextView tv_remind_name;
    private TextView tv_remind_date;
    private TextView tv_remind_cycle;
    private TextView tv_remind_died_line;
    private CharSequence[] itemCycle = {"提醒一次","每天","每周","每月","每季度","每半年","每年"};
    private TextView tvNumber;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_record_ring;
    }

    @Override
    public void initView() {
        Common.iconId = 2;
        Common.titleSearchId = 1;
        ll_remind_name = (LinearLayout) findViewById(R.id.ll_remind_name);
        ll_remind_date = (LinearLayout) findViewById(R.id.ll_remind_date);
        ll_remind_cycle = (LinearLayout) findViewById(R.id.ll_remind_cycle);
        ll_remind_died_line = (LinearLayout) findViewById(R.id.ll_remind_died_line);
        tv_remind_name = (TextView) findViewById(R.id.tv_remind_name);
        tv_remind_date = (TextView) findViewById(R.id.tv_remind_date);
        tv_remind_cycle = (TextView) findViewById(R.id.tv_remind_cycle);
        tv_remind_died_line = (TextView) findViewById(R.id.tv_remind_died_line);

    }
    @Override
    public void initData() {
        setTitleText("添加记账提醒");
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        ll_remind_name.setOnClickListener(this);
        ll_remind_date.setOnClickListener(this);
        ll_remind_cycle.setOnClickListener(this);
        ll_remind_died_line.setOnClickListener(this);
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()){
            case R.id.ll_remind_name:
                showRenameDialog();
                break;
            case R.id.ll_remind_date:
                showDateDialog();
                break;
            case R.id.ll_remind_cycle:
                showCycleDialog();
                break;
            case R.id.ll_remind_died_line:
                showDateDialog();
                break;
            default:
                break;

        }
    }
    /**
     * 周期选择对话框
     */
    private void showCycleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请选择周期");
        builder.setSingleChoiceItems(itemCycle, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_remind_cycle.setText(itemCycle[which]);
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    /**
     * 更名对话框
     */
    private void showRenameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请输入提醒名称");
        View view = View.inflate(context,R.layout.layout_dialog,null);
        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        final EditText editText = (EditText) view.findViewById(R.id.et_input_name);
        TextView tvSure = (TextView) view.findViewById(R.id.tv_sure);
        tvNumber = (TextView) view.findViewById(R.id.tv_number);
        editText.addTextChangedListener(new MyTextWatcher());
        if (editText.getText().toString().trim().length()<=10){
            tvSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String inputString = editText.getText().toString().trim();
                    if (inputString.length() <= 0){
                        Toast.makeText(context, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    }else if (inputString.length() <= 10) {
                        tv_remind_name.setText(inputString);
                        SharepreferenceUtils.setSharepreference(context, SpUtils.DAILY_LIFE,0,SpUtils.USER_NAME_STRING,inputString);
                        dialog.dismiss();
                    } else if (inputString.length() > 10){
                        editText.setText("");
                        Toast.makeText(context, "不能超过十个字符", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        dialog.show();
    }

    /**
     * 弹出日历选择对话框
     */
    private void showDateDialog() {

    }

    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length()<=10){
                tvNumber.setText(s.length()+"");
            }else {
                tvNumber.setText("10");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
