package life.bean.com.beanlife.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.adapter.MyDialogAdapter;
import life.bean.com.beanlife.adapter.MyPagerAdapter;
import life.bean.com.beanlife.fragment.BaseFragment;
import life.bean.com.beanlife.fragment.InComeFragment;
import life.bean.com.beanlife.fragment.PayFragment;

/**
 * 作者 : bean on 2017/2/23/0023.
 * 注释 :
 */
public class ShortEditorActivity extends BaseActivity {

    private ImageView iv_healthy;
    private TextView tvChooseMoneyCategory;
    private ViewPager vp_category;
    private ArrayList<BaseFragment> list = new ArrayList<>();
    private TextView tv_healthy;
    private TextView tv_calendar;
    private LinearLayout ll_add_member;
    private RelativeLayout rl_choose_pay_mode;
    private LinearLayout ll_camera;
    private RelativeLayout rl_location;
    private TextView tv_add_member;
    private TextView tv_choose_pay_mode;
    private int flag = 1;//用来判断是哪个条目弹出来的对话框
    private ArrayList<String> list2;
    private ArrayList<String> list1;
    private ArrayAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.layout_short_editor;
    }

    @Override
    public void initView() {
        iv_healthy = (ImageView) findViewById(R.id.iv_healthy);
        tv_healthy = (TextView) findViewById(R.id.tv_healthy);
        tvChooseMoneyCategory = (TextView) findViewById(R.id.tv_choose_money_category);
        vp_category = (ViewPager) findViewById(R.id.vp_category);
        tv_calendar = (TextView) findViewById(R.id.tv_calendar);
        rl_location = (RelativeLayout) findViewById(R.id.rl_location);
        ll_add_member = (LinearLayout) findViewById(R.id.ll_add_member);
        rl_choose_pay_mode = (RelativeLayout) findViewById(R.id.rl_choose_pay_mode);
        ll_camera = (LinearLayout) findViewById(R.id.ll_camera);
        tv_add_member = (TextView) findViewById(R.id.tv_add_member);
        tv_choose_pay_mode = (TextView) findViewById(R.id.tv_pay_mode);

    }

    @Override
    public void initData() {
        list.add(new PayFragment());
        list.add(new InComeFragment());
        vp_category.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), list));
    }

    @Override
    public void dealCommon() {
        super.dealCommon();
        iv_healthy.setOnClickListener(this);
        tv_healthy.setOnClickListener(this);
        tvChooseMoneyCategory.setOnClickListener(this);
        tv_calendar.setOnClickListener(this);
        ll_add_member.setOnClickListener(this);
        rl_choose_pay_mode.setOnClickListener(this);
        ll_camera.setOnClickListener(this);
        rl_location.setOnClickListener(this);
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.iv_healthy:
            case R.id.tv_healthy:
                if (vp_category.getVisibility() == View.GONE) {
                    vp_category.setVisibility(View.VISIBLE);
                } else if (vp_category.getVisibility() == View.VISIBLE) {
                    vp_category.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_choose_money_category:
                showKeyBord();
                break;
            case R.id.tv_calendar:
                Intent intentCalendar = new Intent(context,CalendarActivity.class);
                intentCalendar.putExtra("titleName","1");
                startActivity(intentCalendar);
                break;
            case R.id.rl_location:
                Intent intentLocation = new Intent(context,CalendarActivity.class);
                intentLocation.putExtra("titleName","2");
                startActivity(intentLocation);
                break;
            case R.id.ll_add_member:
                flag = 1;
                list1 = new ArrayList();
                list1.add("爱人");
                list1.add("孩子");
                list1.add("父母");
                list1.add("朋友");

                showBottomDialog(list1);
                break;
            case R.id.rl_choose_pay_mode:
                flag = 2;
                list2 = new ArrayList();
                list2.add("现金");
                list2.add("支付宝");
                list2.add("微信支付");
                list2.add("储蓄卡");
                list2.add("信用卡");
                showBottomDialog(list2);
                break;
            case R.id.ll_camera:
                break;
            default:
                break;
        }
    }

    private void showBottomDialog(final ArrayList<String> list2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = View.inflate(context,R.layout.layout_bottom_dialog,null);
        builder.setView(view);
        final TextView dialog_title_name = (TextView) view.findViewById(R.id.dialog_title_name);
        TextView tv_add = (TextView) view.findViewById(R.id.tv_add);
        ListView lv_dialog_list_view = (ListView) view.findViewById(R.id.lv_dialog_list_view);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list2);
        lv_dialog_list_view.setAdapter(adapter);
        final AlertDialog dialog =builder.create();
        if (flag == 1) {
            dialog_title_name.setText("选择成员");
        }else {
            dialog_title_name.setText("选择支付方式");
        }
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,AddRememberActivity.class);
                if (flag == 1){
                    intent.putExtra("hint","1");
                }else {
                    intent.putExtra("hint","2");
                }
                startActivityForResult(intent,1);
            }
        });
        lv_dialog_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              String title_name = list2.get(position);
                if (flag == 1) {
                    tv_add_member.setText(title_name);

                }else {
                    tv_choose_pay_mode.setText(title_name);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1&&resultCode == 2){
            String name = data.getStringExtra("name");
            if (flag == 1){
                list1.add(name);
            }else {
                list2.add(name);
            }
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 弹出来自定义键盘
     */
    private void showKeyBord() {
    }
}
