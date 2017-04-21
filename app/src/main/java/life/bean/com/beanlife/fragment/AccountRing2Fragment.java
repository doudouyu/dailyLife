package life.bean.com.beanlife.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.AddRecordRing2Activity;
import life.bean.com.beanlife.adapter.MyRecordRingDetailAdapter;
import life.bean.com.beanlife.bean.AccountRingEvent;
import life.bean.com.beanlife.bean.RingDetailBean;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :记账提醒页面数据传递用EventBus实现
 */
public class AccountRing2Fragment extends BaseFragment {

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
        EventBus.getDefault().register(this);
        ll_add_ring = (LinearLayout) view.findViewById(R.id.ll_add_ring);
        ll_everyday_ring = (LinearLayout) view.findViewById(R.id.ll_everyday_ring);
        tally_detail = (ListView) view.findViewById(R.id.tally_detail);
        tvTime = (TextView) view.findViewById(R.id.tv_time);

    }

    @Override
    public void initData() {
        adapter = new MyRecordRingDetailAdapter(context, list);
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
                Intent intent = new Intent(context, AddRecordRing2Activity.class);
                startActivity(intent);
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
                Intent intent = new Intent(context, AddRecordRing2Activity.class);
                startActivity(intent);
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
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello(AccountRingEvent event){
        Toast.makeText(context,"event"+event.getItemDetail(),Toast.LENGTH_SHORT).show();
        list.add(new RingDetailBean(event.getItemName(), event.getItemDetail(), event.getItemTime(), event.getItemCycle()));
        adapter.notifyDataSetChanged();
    }

}
