package life.bean.com.beanlife.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.Collection;
import java.util.List;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.bean.RecordDetail;

/**
 * 作者 : bean on 2017/4/26/0026.
 * 注释 :
 */
public class MyRecyclerAdapter extends BaseRecyclerAdapter<String> {
    public MyRecyclerAdapter(RecyclerView v, Collection<String> datas, int itemLayoutId) {
        super(v, datas, itemLayoutId);
    }

    @Override
    public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
        holder.setText(R.id.tv_text,item);

    }

}
