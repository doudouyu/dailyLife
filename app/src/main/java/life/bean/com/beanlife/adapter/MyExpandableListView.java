package life.bean.com.beanlife.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.bean.CommonProblemBean;

/**
 * 作者 : bean on 2017/3/2/0002.
 * 注释 :
 */
public class MyExpandableListView extends BaseExpandableListAdapter {
    private  Context context;
    private  Map<CommonProblemBean, String> map;
    private ArrayList<CommonProblemBean> parenList = new ArrayList<>();

    public MyExpandableListView(Context context, Map<CommonProblemBean, String> map) {
        this.context = context;
        this.map = map;
        for (CommonProblemBean key : map.keySet()) {
            this.parenList.add(key);
        }

    }

    @Override
    public int getGroupCount() {
        return map.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return map.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return map.get(parenList.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder holder = null;
        if (convertView == null){
            holder = new ParentViewHolder();
            convertView = View.inflate(context, R.layout.layout_text_image,null);
            holder.tvProblem = (TextView) convertView.findViewById(R.id.tv_setting_title);
            holder.ivArrow = (ImageView) convertView.findViewById(R.id.iv_setting_back);
            holder.tvProblem.setTextSize(14);
            convertView.setTag(holder);
        }else {
            holder = (ParentViewHolder) convertView.getTag();
        }
        holder.tvProblem.setText(parenList.get(groupPosition).problem);
        if (parenList.get(groupPosition).arrowState == 2){
            holder.ivArrow.setImageResource(R.mipmap.arrow_up);
        }else {
            holder.ivArrow.setImageResource(R.mipmap.arrow_down);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder= null;
        if (convertView == null){
            holder = new ChildViewHolder();
            convertView = View.inflate(context, R.layout.layout_text,null);
            holder.tvAnswer = (TextView) convertView.findViewById(R.id.tv_problem_detail);
            holder.tvAnswer.setTextSize(12);
            convertView.setTag(holder);
        }else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        holder.tvAnswer.setText(map.get(parenList.get(groupPosition)));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class ChildViewHolder {
        public TextView tvAnswer;
    }

    private class ParentViewHolder {
        public TextView tvProblem;
        public ImageView ivArrow;
    }
}
