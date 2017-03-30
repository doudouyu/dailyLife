package life.bean.com.beanlife.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * 作者 : bean on 2017/3/30/0030.
 * 注释 :
 */
public class MyDialogAdapter extends BaseAdapter {
    private  Context context;
    private  ArrayList<String> list;

    public MyDialogAdapter(Context context, ArrayList<String> list2) {
        this.context = context;
        this.list = list2;
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
        return null;
    }
}
