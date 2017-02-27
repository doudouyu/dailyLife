package life.bean.com.beanlife.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.bean.RecordDetail;

/**
 * 作者 : bean on 2017/2/23/0023.
 * 注释 :
 */
public class MyRecordAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<RecordDetail> list;
    public MyRecordAdapter(Context context, ArrayList<RecordDetail> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 8;
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
        if (convertView == null){
            convertView = View.inflate(context, R.layout.item_record,null);
        }
        return convertView;
    }
}
