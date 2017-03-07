package life.bean.com.beanlife.fragment;

import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

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

//    private BarData getBarData(int count, float range) {
//        ArrayList<String> xValues = new ArrayList<String>();
//        for (int i = 0; i < count; i++) {
//            xValues.add(i + "");
//        }
//
//        ArrayList<BarEntry> yValues = new ArrayList<>();
//
//        for (int i = 0; i < count; i++) {
//            float value = (float) (Math.random() * range/*100以内的随机数*/) + 3;
//            yValues.add(new BarEntry(value, i));
//        }
//
//        // y轴的数据集合
//        BarDataSet barDataSet = new BarDataSet(yValues, "collection");
//
////        barDataSet.setBarSpacePercent(80);
//        barDataSet.setVisible(true);//是否显示柱状图柱子
//        barDataSet.setColor(Color.GREEN);//设置柱子颜色
//        barDataSet.setDrawValues(true);//是否显示柱子上面的数值
//
//        ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
//        barDataSets.add(barDataSet); // add the datasets
//
//        BarData barData = new BarData(barDataSet);
//
//        return barData;
//    }
//    private void  showBarChat (BarChart barChart,BarData barData){
//// 如果没有数据的时候，会显示这个，类似ListView的EmptyView
////        barChart.setNoDataTextDescription("You need to provide data for the chart.");
//
//        barChart.setData(barData); // 设置数据
//
//        barChart.setDrawBorders(true); //是否在折线图上添加边框
//
//
//        barChart.setDrawGridBackground(true); // 是否显示表格颜色
//        barChart.setGridBackgroundColor(Color.RED); // 表格的的颜色
//        //barChart.setBackgroundColor(Color.BLACK);// 设置整个图标控件的背景
//        barChart.setDrawBarShadow(true);//柱状图没有数据的部分是否显示阴影效果
//
//        barChart.setTouchEnabled(false); // 设置是否可以触摸
//        barChart.setDragEnabled(false);// 是否可以拖拽
//        barChart.setScaleEnabled(false);// 是否可以缩放
//        barChart.setPinchZoom(true);//y轴的值是否跟随图表变换缩放;如果禁止，y轴的值会跟随图表变换缩放
//
//        barChart.setDrawValueAboveBar(true);//柱状图上面的数值显示在柱子上面还是柱子里面
//
//        barChart.getXAxis().setDrawGridLines(false);//是否显示竖直标尺线
//        barChart.getXAxis().setDrawLabels(true);//是否显示X轴数值
//        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置 默认在上方
//
//        barChart.getAxisRight().setDrawLabels(false);//右侧是否显示Y轴数值
//        barChart.getAxisRight().setEnabled(false);//是否显示最右侧竖线
//        barChart.getAxisRight().setDrawAxisLine(true);
//        barChart.getAxisLeft().setDrawAxisLine(false);
//        barChart.getXAxis().setDrawAxisLine(true);
//        barChart.getLegend().setPosition(Legend.LegendPosition.RIGHT_OF_CHART);//设置比例图标的位置
//        barChart.getLegend().setDirection(Legend.LegendDirection.RIGHT_TO_LEFT);//设置比例图标和文字之间的位置方向
//        barChart.getLegend().setTextColor(Color.RED);
//
//        barChart.animateXY(2000,3000);
//    }

    @Override
    public void dealCommon() {

    }
}
