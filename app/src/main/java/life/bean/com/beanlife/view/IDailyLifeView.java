package life.bean.com.beanlife.view;

import java.util.List;

/**
 * 作者 : bean on 2017/4/25/0025.
 * 注释 :
 */
public interface IDailyLifeView extends IBaseView{
    //处理条目的点击事件
    void DealItemClickEvent();
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void RefreshView(List list);

    @Override
    void showFailedError();
}
