package life.bean.com.beanlife.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.BaseActivity;
import life.bean.com.beanlife.bean.CategoryInfo;

/**
 * 作者 : bean on 2017/3/30/0030.
 * 注释 :
 */
public class MyGridViewAdapter extends BaseAdapter {
    private ArrayList<CategoryInfo> list;
    private Context context;

    public MyGridViewAdapter(Context context, ArrayList<CategoryInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.gv_item_category, null);
            holder = new ViewHolder();
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_item_icon);
            holder.tvDetail = (TextView) convertView.findViewById(R.id.tv_item_detail);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivIcon.setImageResource(list.get(position).getIcon());
        holder.tvDetail.setText(list.get(position).getName());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    private class ViewHolder {
        private ImageView ivIcon;
        private TextView tvDetail;
    }
}
