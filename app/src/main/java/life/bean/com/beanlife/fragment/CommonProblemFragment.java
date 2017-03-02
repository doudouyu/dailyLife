package life.bean.com.beanlife.fragment;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.adapter.MyExpandableListView;
import life.bean.com.beanlife.bean.CommonProblemBean;

/**
 * 作者 : bean on 2017/2/28/0028.
 * 注释 :
 */
public class CommonProblemFragment extends BaseFragment {
    private ExpandableListView expandableListViewRecord;
    private ExpandableListView expandableListViewFriend;
    private Map<CommonProblemBean, String> map = new LinkedHashMap();
    private Map<CommonProblemBean, String> map2 = new LinkedHashMap();
    private List<CommonProblemBean> parentList = new ArrayList<>();
    private List<CommonProblemBean> parentList1 = new ArrayList<>();
    private MyExpandableListView myExpandableListView;
    private MyExpandableListView myExpandableListView1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_common_problem;
    }

    @Override
    public void initView(View view) {
        initMapData();
        expandableListViewRecord = (ExpandableListView) view.findViewById(R.id.el_problem_record);
        expandableListViewFriend = (ExpandableListView) view.findViewById(R.id.el_problem_friend);
    }

    private void initMapData() {
        parentList.add(new CommonProblemBean("微记账能为我提供哪些服务", 1));
        parentList.add(new CommonProblemBean("如何在微记账记一笔账", 1));
        parentList.add(new CommonProblemBean("如何在首页删除流水", 1));
        parentList.add(new CommonProblemBean("无网络状态下如何记账", 1));
        parentList.add(new CommonProblemBean("如何管理分类", 1));
        map.put(parentList.get(0), "你猜");
        map.put(parentList.get(1), "你猜");
        map.put(parentList.get(2), "你猜");
        map.put(parentList.get(3), "你猜");
        map.put(parentList.get(4), "你猜");
        parentList1.add(new CommonProblemBean("什么是联手记账", 1));
        parentList1.add(new CommonProblemBean("如何开启联手记账", 1));
        parentList1.add(new CommonProblemBean("如何解除联手记账", 1));
        parentList1.add(new CommonProblemBean("扫描二维码功能如何使用", 1));
        map2.put(parentList1.get(0), "你猜，猜对了我就告诉你");
        map2.put(parentList1.get(1), "你猜，猜对了我就告诉你");
        map2.put(parentList1.get(2), "你猜，猜对了我就告诉你");
        map2.put(parentList1.get(3), "你猜，猜对了我就告诉你");

    }

    @Override
    public void initData() {
        myExpandableListView = new MyExpandableListView(context, map);
        expandableListViewRecord.setAdapter(myExpandableListView);
        myExpandableListView1 = new MyExpandableListView(context, map2);
        expandableListViewFriend.setAdapter(myExpandableListView1);
        expandableListViewRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if (parentList.get(position).arrowState == 2)
                parentList.get(position).setArrowState(1);
                else
                   parentList.get(position).setArrowState(2);
                myExpandableListView.notifyDataSetChanged();
            }
        });
        expandableListViewFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parentList1.get(position).arrowState == 2)
                    parentList1.get(position).setArrowState(1);
                else
                    parentList1.get(position).setArrowState(2);
                myExpandableListView1.notifyDataSetChanged();
            }
        });
    }
}
