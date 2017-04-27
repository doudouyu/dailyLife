package life.bean.com.beanlife.presenter;

import java.util.List;

import life.bean.com.beanlife.entitybiz.QueryResultBiz;
import life.bean.com.beanlife.myInterface.OnGetDateListener;
import life.bean.com.beanlife.view.IQueryResultView;

/**
 * 作者 : bean on 2017/4/27/0027.
 * 注释 :
 */
public class IQueryResultPresenter {
    private IQueryResultView queryResultView;
    private QueryResultBiz biz;
    public IQueryResultPresenter(IQueryResultView queryResultView) {
        this.queryResultView = queryResultView;
        biz = new QueryResultBiz();
    }

    public void setData(int style) {

            biz.setData(style,new OnGetDateListener() {
                @Override
                public void onSuccessGetData(List list) {
                    queryResultView.RefreshView(list);
                }

                @Override
                public void onFailedGetData() {
                    queryResultView.showFailedError();
                }
            });

    }
}
