package life.bean.com.beanlife.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.bean.MenuInfo;

/**
 * 作者 : bean on 2017/2/21/0021.
 * 注释 :
 */
public class MyMenuAdapter extends BaseAdapter {
    private  Context context;
    private  ArrayList<MenuInfo> list ;

    public MyMenuAdapter(ArrayList<MenuInfo> list, Context context) {
        this .list = list;
        this.context = context;
        Log.i("list",list.size()+"");
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuHolder holder;
        if (convertView == null){
            convertView = View.inflate(context, R.layout.iten_record,null);
            holder = new MenuHolder();
            holder.tvTitle = (TextView) convertView.findViewById(R.id.record_title);
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.record_icon);
            convertView.setTag(holder);
        }else {
            holder = (MenuHolder) convertView.getTag();
        }
        holder.tvTitle.setText(list.get(position).getTitle()+"");
        holder.ivIcon.setImageResource(list.get(position).getIcon());
        return convertView;
    }


    private class MenuHolder {
        public TextView tvTitle;
        public ImageView ivIcon;
    }
}
