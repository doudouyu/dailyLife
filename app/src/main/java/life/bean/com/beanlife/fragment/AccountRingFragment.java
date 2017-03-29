package life.bean.com.beanlife.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.AddRecordRingActivity;
import life.bean.com.beanlife.adapter.MyRecordAdapter;
import life.bean.com.beanlife.adapter.MyRecordRingDetailAdapter;
import life.bean.com.beanlife.bean.RingDetailBean;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class AccountRingFragment extends BaseFragment {

    private LinearLayout ll_add_ring;
    private LinearLayout ll_everyday_ring;
    private ListView tally_detail;
    private ArrayList<String> listTime = new ArrayList();
    private String textTime;
    private TextView tvTime;
    private ArrayList<RingDetailBean> list = new ArrayList<>();
    private MyRecordRingDetailAdapter adapter;
    private String recordTime;
    private String recordCycle;
    private String recordName;
    private String recordDate;

    @Override
    public int getLayoutId() {
        return R.layout.layout_account_fragment;
    }

    @Override
    public void initView(View view) {
        ll_add_ring = (LinearLayout) view.findViewById(R.id.ll_add_ring);
        ll_everyday_ring = (LinearLayout) view.findViewById(R.id.ll_everyday_ring);
        tally_detail = (ListView) view.findViewById(R.id.tally_detail);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
    }

    @Override
    public void initData() {
        adapter = new MyRecordRingDetailAdapter(context,list);
        tally_detail.setAdapter(adapter);
    }

    @Override
    public void dealCommon() {
        ll_add_ring.setOnClickListener(this);
        ll_everyday_ring.setOnClickListener(this);
        tally_detail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转一个界面
                Intent intent = new Intent(context, AddRecordRingActivity.class);
                intent.putExtra("remind_name",recordName);
                intent.putExtra("remind_time",recordTime);
                intent.putExtra("remind_cycle",recordCycle);
                intent.putExtra("remind_date",recordDate);
                intent.putExtra("position",position+"");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.ll_everyday_ring:
                showSelectedDialog();
                break;
            case R.id.ll_add_ring:
                Intent intent = new Intent(context, AddRecordRingActivity.class);
                startActivityForResult(intent, 0);
                break;

            default:
                break;
        }
    }

    /**
     * 弹出来一个时间选择器
     */
    private void showSelectedDialog() {
        initTimeData();
        String[] array = new String[listTime.size()];
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请选择提醒时间：");
        builder.setSingleChoiceItems(listTime.toArray(array), 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textTime = listTime.get(which);
                dialog.dismiss();
                tvTime.setText(textTime);
            }
        });
        builder.create().show();
    }


    private void initTimeData() {
        for (int i = 0; i <= 24; i++) {
            if (i < 10) {
                listTime.add("0" + i + ":00");
            } else {
                listTime.add(i + ":00");
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2){
            if (requestCode == 0) {
                recordName = data.getStringExtra("remind_name");
                recordTime = data.getStringExtra("remind_time");
                recordCycle = data.getStringExtra("remind_cycle");
                recordDate = data.getStringExtra("remind_date");
                list.add(new RingDetailBean(recordName, recordDate));
                adapter.notifyDataSetChanged();
            }else if (requestCode == 1){
                recordName = data.getStringExtra("remind_name");
                recordTime = data.getStringExtra("remind_time");
                recordCycle = data.getStringExtra("remind_cycle");
                recordDate = data.getStringExtra("remind_date");
                String recordPosition = data.getStringExtra("position");
                list.remove(list.get(Integer.parseInt(recordPosition)));
                list.add(Integer.parseInt(recordPosition),new RingDetailBean(recordName, recordDate));
                adapter.notifyDataSetChanged();
            }
        }

    }
}
