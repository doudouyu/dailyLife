package life.bean.com.beanlife.fragment;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.adapter.MyBankCardAdapter;
import life.bean.com.beanlife.bean.BankCardBean;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class BankCardFragment extends BaseFragment {
    private ListView lvBankCard;
    private ArrayList<BankCardBean> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.layout_band_card;
    }

    @Override
    public void initView(View view) {
        lvBankCard = (ListView) view.findViewById(R.id.lv_bank_card);

    }

    @Override
    public void initData() {
        lvBankCard.setAdapter(new MyBankCardAdapter(context,list));
    }

    @Override
    public void dealCommon() {

    }
}
