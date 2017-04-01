package life.bean.com.beanlife.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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
    private TextView depositCard;
    private TextView creditCard;
    private ArrayList<BankCardBean> depositCardList = new ArrayList<>();
    private ArrayList<BankCardBean> creditCardList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.layout_band_card;
    }

    @Override
    public void initView(View view) {
        lvBankCard = (ListView) view.findViewById(R.id.lv_bank_card);
        depositCard = (TextView) view.findViewById(R.id.expend);
        creditCard = (TextView) view.findViewById(R.id.income);
    }

    @Override
    public void initData() {
        initList();
        lvBankCard.setAdapter(new MyBankCardAdapter(context,depositCardList));

    }

    @Override
    public void dealCommon() {
        depositCard.setOnClickListener(this);
        creditCard.setOnClickListener(this);
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()){
            case R.id.expend:
                setPageTwo();
                lvBankCard.setAdapter(new MyBankCardAdapter(context,depositCardList));
                break;
            case R.id.income:
                setPageOne();
                lvBankCard.setAdapter(new MyBankCardAdapter(context,creditCardList));
                break;
            default:
                break;

        }
    }

    private void initList() {
        depositCardList.add(new BankCardBean(R.mipmap.cmb_bank,"2","1"));
        depositCardList.add(new BankCardBean(R.mipmap.cmb_bank,"2","3"));
        depositCardList.add(new BankCardBean(R.mipmap.cmb_bank,"2","4"));
        creditCardList.add(new BankCardBean(R.mipmap.contribute,"1","1"));
        creditCardList.add(new BankCardBean(R.mipmap.contribute,"1","2"));
        creditCardList.add(new BankCardBean(R.mipmap.contribute,"1","3"));
        creditCardList.add(new BankCardBean(R.mipmap.contribute,"1","4"));
        creditCardList.add(new BankCardBean(R.mipmap.contribute,"1","5"));

    }
    private void setPageTwo() {
        creditCard.setBackgroundColor(getResources().getColor(R.color.pink));
        creditCard.setTextColor(getResources().getColor(R.color.white));
        depositCard.setBackgroundColor(getResources().getColor(R.color.white));
        depositCard.setTextColor(getResources().getColor(R.color.pink));
    }

    private void setPageOne() {
        depositCard.setBackgroundColor(getResources().getColor(R.color.pink));
        depositCard.setTextColor(getResources().getColor(R.color.white));
        creditCard.setBackgroundColor(getResources().getColor(R.color.white));
        creditCard.setTextColor(getResources().getColor(R.color.pink));
    }
}
