package life.bean.com.beanlife.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.adapter.MyPagerAdapter;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :
 */
public class MoneyServiceFragment extends BaseFragment {

    private ViewPager pager;
    private TextView makeMoney;
    private TextView borrowMoney;
    private TextView creditCard;
    private ArrayList<BaseFragment> fragments = new ArrayList<>();
    private int width;
    private View indicatorLine;
    private int LineWidth;

    @Override
    public int getLayoutId() {
        return R.layout.activity_money_service;
    }

    @Override
    public void initView(View view) {
        //获取屏幕的宽度
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();

        pager = (ViewPager) view.findViewById(R.id.money_service_item);
        makeMoney = (TextView) view.findViewById(R.id.make_money);
        borrowMoney = (TextView) view.findViewById(R.id.borrow_money);
        creditCard = (TextView) view.findViewById(R.id.credit_card);
        indicatorLine = view.findViewById(R.id.indicator_line);
    }

    private void initRightAnim(int toX) {
        Animation translateIn = new TranslateAnimation(0, toX, 0, 0);
        translateIn.setDuration(100);
        translateIn.setFillAfter(true);
        indicatorLine.startAnimation(translateIn);
    }

    @Override
    public void initData() {
        if (fragments.size() <= 0) {
            initFragments();
        }
        initLine();
        pager.setAdapter(new MyPagerAdapter(getFragmentManager(), fragments));
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initRightAnim(width / (fragments.size()) * position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFragments() {
        fragments.add(new MakeMoneyFragment());
        fragments.add(new BorrowMoneyFragment());
        fragments.add(new CreditCardFragment());
    }

    private void initLine() {
        LineWidth = width / fragments.size();
        indicatorLine.setLayoutParams(new LinearLayout.LayoutParams(LineWidth, 5));
    }

    @Override
    public void dealCommon() {
        makeMoney.setOnClickListener(this);
        borrowMoney.setOnClickListener(this);
        creditCard.setOnClickListener(this);
    }

    @Override
    public void onInnerClick(View v) {
        super.onInnerClick(v);
        switch (v.getId()) {
            case R.id.make_money:
                pager.setCurrentItem(0);
                initRightAnim(0);
                break;
            case R.id.borrow_money:
                pager.setCurrentItem(1);
                initRightAnim(width / (fragments.size()));
                break;
            case R.id.credit_card:
                initRightAnim(width / (fragments.size()) * 2);
                pager.setCurrentItem(2);


                break;
            default:
                break;
        }
    }
}
