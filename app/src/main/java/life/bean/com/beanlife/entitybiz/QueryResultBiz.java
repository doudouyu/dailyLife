package life.bean.com.beanlife.entitybiz;

import java.util.ArrayList;

import life.bean.com.beanlife.myInterface.OnGetDateListener;

/**
 * 作者 : bean on 2017/4/27/0027.
 * 注释 :
 */
public class QueryResultBiz implements IBaseBiz {
    ArrayList<String> list = new ArrayList<>();

    @Override
    public void setData(int what ,OnGetDateListener onGetDateListener) {
        //该方法是用来请求网络数据的
        if (list.size() > 0) {
            list.clear();
        }
        if (what == 1){
            //星期的数据
            for (int i = 1; i <= 100; i=i+7) {
                list.add(i + "");
            }
        } else if (what == 2){
            for (int i = 1; i <= 12; i++) {
                list.add(i + "");
            }
        }else if (what == 3){
            for (int i = 2006; i <= 2017; i++) {
                list.add(i + "");
            }
        }


        if (list.size() > 0) {
            onGetDateListener.onSuccessGetData(list);
        } else {
            onGetDateListener.onFailedGetData();
        }
    }
}
