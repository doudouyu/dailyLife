package life.bean.com.beanlife.view;

import java.util.List;

/**
 * 作者 : bean on 2017/4/25/0025.
 * 注释 :
 */
public interface  IBaseView {
    //开始加载的时候弹框提醒
     void showLoading();

    //加载完毕的时候弹框消失
     void hideLoading();

    //数据获取成功，刷新界面
    void RefreshView(List list);

    //数据获取失败，提示用户
     void showFailedError();

}
