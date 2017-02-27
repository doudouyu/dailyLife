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
import life.bean.com.beanlife.bean.BankCardBean;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class MyBankCardAdapter extends BaseAdapter {
    private  Context context;
    private  ArrayList<BankCardBean> list;

    public MyBankCardAdapter(Context context, ArrayList<BankCardBean> list) {
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
       BankCardHolder holder = null;
        if (convertView == null){
            holder = new BankCardHolder();
            convertView = View.inflate(context, R.layout.item_card_info,null);
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_bank_icon);
            holder.tvBankName = (TextView) convertView.findViewById(R.id.tv_card_type);
            holder.tvCardNumber = (TextView) convertView.findViewById(R.id.tv_card_number);
            convertView.setTag(holder);
        }else {
            holder = (BankCardHolder) convertView.getTag();
        }
        return convertView;
    }

    private class BankCardHolder {
        private ImageView ivIcon;
        private TextView tvBankName;
        private TextView tvCardNumber;
    }
}
