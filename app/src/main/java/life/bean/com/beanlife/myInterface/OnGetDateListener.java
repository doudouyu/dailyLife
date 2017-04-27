package life.bean.com.beanlife.myInterface;

import com.iflytek.cloud.thirdparty.T;

import java.util.ArrayList;
import java.util.List;

import life.bean.com.beanlife.bean.RecordDetail;

/**
 * 作者 : bean on 2017/4/25/0025.
 * 注释 :
 */
public interface OnGetDateListener {
    void  onSuccessGetData(List list);
    void  onFailedGetData();
}
