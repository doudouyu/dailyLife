package life.bean.com.beanlife.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.activity.BaseActivity;
import life.bean.com.beanlife.activity.SearchActivity;

/**
 * 作者 : bean on 2017/2/23/0023.
 * 注释 :
 */
public class MySearchAdapter extends BaseAdapter {
    //
    private Context context;
    private String[] titles = {"时间周期", "查询类型", "原纪录备注"};
    private String[] detail1 = {"所有", "本月", "本周", "上周", "上月", "本年", "自定义"};
    private String[] detail2 = {"所有", "类别", "成员", "支付方式", "待报销", "已报销"};
    private String[] detail3 = {"输入查询关键字"};

    public MySearchAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder holder;
        if (convertView == null) {
            holder = new ListViewHolder();
            convertView = View.inflate(context, R.layout.item_search, null);
            holder.tvTile = (TextView) convertView.findViewById(R.id.item_search_title);
            holder.gvType = (GridView) convertView.findViewById(R.id.gv_type);
            convertView.setTag(holder);
        } else {
            holder = (ListViewHolder) convertView.getTag();
        }
        holder.tvTile.setText(titles[position]);
        if (position == 0) {
            holder.gvType.setAdapter(new GridViewAdapter(detail1));
        } else if (position == 1) {
            holder.gvType.setAdapter(new GridViewAdapter(detail2));
        } else if (position == 2) {
            holder.gvType.setAdapter(new GridViewAdapter(detail3));
        }else {
            holder.gvType.setAdapter(new GridViewAdapter(detail1));
        }
        return convertView;
    }

    private class ListViewHolder {
        private TextView tvTile;
        private GridView gvType;
    }

    private class GridViewAdapter extends BaseAdapter {
        private String[] detail;

        public GridViewAdapter(String[] detail1) {
            this.detail = detail1;
        }

        @Override
        public int getCount() {
            return detail.length;
        }

        @Override
        public Object getItem(int position) {
            return detail[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GridViewHolder holder;
            if (convertView == null) {
                holder = new GridViewHolder();
                convertView = View.inflate(context, R.layout.item_search_gridview, null);
                holder.tvType = (TextView) convertView.findViewById(R.id.search_condition);
                convertView.setTag(holder);
            } else {
                holder = (GridViewHolder) convertView.getTag();
            }
            holder.tvType.setText(detail[position]);
            return convertView;
        }
    }

    private class GridViewHolder {
        private TextView tvType;
    }
}
