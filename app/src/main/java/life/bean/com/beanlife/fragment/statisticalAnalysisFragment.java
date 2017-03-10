package life.bean.com.beanlife.fragment;

import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.BarCharts;

/**
 * 作者 : bean on 2017/2/24/0024.
 * 注释 :统计分析
 */
public class statisticalAnalysisFragment extends BaseFragment {

    private BarChart barChartAll;
    private HorizontalBarChart barChartPay;
    private HorizontalBarChart barChartIncome;
    private PieChart pieChartPay;
    private PieChart pieChartIncome;
    private BarData mBarData;
    private BarCharts mBarCharts;
    @Override
    public int getLayoutId() {
        return R.layout.layout_statist_analysis;
    }

    @Override
    public void initView(View view) {
        mBarCharts = new BarCharts();
        barChartAll = (BarChart) view.findViewById(R.id.bar_chat_all);
        barChartPay = (HorizontalBarChart) view.findViewById(R.id.bar_chat_pay);
        barChartIncome = (HorizontalBarChart) view.findViewById(R.id.bar_chat_income);
        pieChartPay = (PieChart) view.findViewById(R.id.pie_chat_pay);
        pieChartIncome = (PieChart) view.findViewById(R.id.pie_chat_income);
        mBarData = mBarCharts.getBarData(14, 500);
        mBarCharts.showBarChart(barChartAll, mBarData);
    }

    @Override
    public void initData() {
    }


    @Override
    public void dealCommon() {

    }
}
