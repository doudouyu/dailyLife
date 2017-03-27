package life.bean.com.beanlife.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.bean.RingDetailBean;

/**
 * 作者 : bean on 2017/3/27/0027.
 * 注释 :
 */
public class MyRecordRingDetailAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<RingDetailBean> list = new ArrayList<>();

    public MyRecordRingDetailAdapter(Context context, ArrayList<RingDetailBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.layout_text_text_image, null);
            holder.tv_item_name = (TextView) convertView.findViewById(R.id.tv_item_name);
            holder.tv_item_detail = (TextView) convertView.findViewById(R.id.tv_item_detail);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_item_name.setText(list.get(position).getItemName());
        if (TextUtils.isEmpty(list.get(position).getItemDetail())){
            holder.tv_item_detail.setText("");
        }else {
            holder.tv_item_detail.setText(""+list.get(position).getItemDetail());
        }
        return convertView;
    }

    private class ViewHolder {
        public TextView tv_item_name;
        public TextView tv_item_detail;
    }
}
