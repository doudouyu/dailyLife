package life.bean.com.beanlife.myInterface;

import java.util.ArrayList;

import life.bean.com.beanlife.bean.RecordDetail;

/**
 * 作者 : bean on 2017/4/25/0025.
 * 注释 :
 */
public interface OnGetDateListener {
    void  onSuccessGetData(ArrayList<RecordDetail> list);
    void  onFailedGetData();
}
