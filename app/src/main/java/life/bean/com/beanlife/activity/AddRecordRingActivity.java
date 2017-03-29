package life.bean.com.beanlife.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;
import life.bean.com.beanlife.utils.DateUtils;
import life.bean.com.beanlife.utils.SharepreferenceUtils;
import life.bean.com.beanlife.utils.SpUtils;


/**
 * 作者 : bean on 2017/3/27/0027.
 * 注释 :
 */
public class AddRecordRingActivity extends BaseActivity {

    private LinearLayout ll_remind_name;
    private LinearLayout ll_remind_date;
    private LinearLayout ll_remind_cycle;
    private LinearLayout ll_remind_died_line;
    private TextView tv_remind_name;
    private TextView tv_remind_date;
    private TextView tv_remind_cycle;
    private TextView tv_remind_died_line;
    private CharSequence[] itemCycle = {"提醒一次", "每天", "每周", "每月", "每季度", "每半年", "每年"};
    private TextView tvNumber;
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    private TextView tv_save;
    private String position;

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
        tv_save  = (TextView) findViewById(R.id.tv_save);
    }

    @Override
    public void initData() {
        setTitleText("添加记账提醒");
        Intent intent = getIntent();
        tv_remind_name.setText(intent.getStringExtra("remind_name"));
        tv_remind_date.setText(intent.getStringExtra("remind_time"));
        tv_remind_cycle.setText(intent.getStringExtra("remind_cycle"));
        tv_remind_died_line.setText(intent.getStringExtra("remind_date"));
        position = intent.getStringExtra("position");
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        ll_remind_name.setOnClickListener(this);
        ll_remind_date.setOnClickListener(this);
        ll_remind_cycle.setOnClickListener(this);
        ll_remind_died_line.setOnClickListener(this);
        tv_save.setOnClickListener(this);
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.ll_remind_name:
                showRenameDialog();
                break;
            case R.id.ll_remind_date:
                showDateDialog(1);
                break;
            case R.id.ll_remind_cycle:
                showCycleDialog();
                break;
            case R.id.ll_remind_died_line:
                showDateDialog(2);
                break;
            case R.id.tv_save:
                checkState();
                break;
            default:
                break;

        }
    }

    private void checkState() {
        String string_remind_name =   tv_remind_name.getText().toString().trim();
        String string_remind_date =   tv_remind_date.getText().toString().trim();
        String string_remind_cycle =   tv_remind_cycle.getText().toString().trim();
        String string_remind_died_line =   tv_remind_died_line.getText().toString().trim();
        if (TextUtils.isEmpty(string_remind_name)){
            showToast("提醒名称不能为空！");
            return;
        }
        if (TextUtils.isEmpty(string_remind_date)){
            showToast("提醒时间不能为空！");
            return;
        }
        if (TextUtils.isEmpty(string_remind_cycle)){
            showToast("提醒周期不能为空！");
            return;
        }
        if (TextUtils.isEmpty(string_remind_died_line)){
            showToast("到期时间不能为空！");
            return;
        }
        //todo 上传信息到服务器
        Intent intent = new Intent();
        intent.putExtra("remind_name",string_remind_name);
        intent.putExtra("remind_date",string_remind_died_line);
        intent.putExtra("remind_cycle",string_remind_cycle);
        intent.putExtra("remind_time",string_remind_date);
        if (!TextUtils.isEmpty(position)){
            intent.putExtra("position",position);
        }
        this.setResult(2,intent);
        finish();
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
        View view = View.inflate(context, R.layout.layout_dialog, null);
        builder.setView(view);
//        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        final EditText editText = (EditText) view.findViewById(R.id.et_input_name);
        TextView tvSure = (TextView) view.findViewById(R.id.tv_sure);
        tvNumber = (TextView) view.findViewById(R.id.tv_number);
        editText.addTextChangedListener(new MyTextWatcher());
        if (editText.getText().toString().trim().length() <= 10) {
            tvSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String inputString = editText.getText().toString().trim();
                    if (inputString.length() <= 0) {
                        Toast.makeText(context, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    } else if (inputString.length() <= 10) {
                        tv_remind_name.setText(inputString);
                        SharepreferenceUtils.setSharepreference(context, SpUtils.DAILY_LIFE, 0, SpUtils.USER_NAME_STRING, inputString);
                        dialog.dismiss();
                    } else if (inputString.length() > 10) {
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
     * @param i
     */
    private void showDateDialog(final int i) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View view = View.inflate(context, R.layout.layout_dialog_canlend, null);
        final TextView day = (TextView) view.findViewById(R.id.tv_day);
        final TextView month = (TextView) view.findViewById(R.id.tv_month);
        final TextView year = (TextView) view.findViewById(R.id.tv_year);
        final TextView week = (TextView) view.findViewById(R.id.tv_week);
        final TextView cancel = (TextView) view.findViewById(R.id.tv_cancel);
        final TextView sure = (TextView) view.findViewById(R.id.tv_sure);
        final MaterialCalendarView calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        String dayString = DateUtils.getTime();
        String[] time = dayString.split("-");
        day.setText(time[2]);
        month.setText(time[1]+"月");
        year.setText(time[0]);
        week.setText(time[4]);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String dayString = getSelectedDatesString(widget).substring(7, 9);
                if (dayString.contains("日")) {
                    day.setText(dayString.substring(0, dayString.length() - 1));
                } else {
                    day.setText(dayString);
                }
                week.setText("星期"+DateUtils.getWeek(FORMATTER.format(date.getDate())));
            }
        });
        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                String monthString = FORMATTER.format(date.getDate()).substring(5, 7);
                if (monthString.contains("月")) {
                    month.setText(monthString);
                } else {
                    month.setText(monthString + "月");
                }

                String yearString = FORMATTER.format(date.getDate()).substring(0, 4);
                year.setText(yearString);
            }
        });
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateInfo = year.getText().toString()+"年"+month.getText().toString()+day.getText().toString()+"日";

                if (i == 1){
                    tv_remind_date.setText(dateInfo);
                }else if (i == 2){
                    tv_remind_died_line.setText(dateInfo);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private String getSelectedDatesString(MaterialCalendarView widget) {
        CalendarDay date = widget.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    private class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() <= 10) {
                tvNumber.setText(s.length() + "");
            } else {
                tvNumber.setText("10");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
