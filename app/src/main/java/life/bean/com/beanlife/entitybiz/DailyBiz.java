package life.bean.com.beanlife.entitybiz;

import android.content.Context;

import java.util.ArrayList;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.bean.RecordDetail;
import life.bean.com.beanlife.myInterface.OnGetDateListener;

/**
 * 作者 : bean on 2017/4/25/0025.
 * 注释 :
 */
public class DailyBiz implements IDailyBiz {

    @Override
    public void setData(OnGetDateListener onGetDateListener) {
        ArrayList< RecordDetail> list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add(new RecordDetail(R.id.iv_icon, "饮食", "买衣服" + i, 3));
        }
        //如果联网成功而且获取数据成功
        if (list.size() > 0) {
            onGetDateListener.onSuccessGetData(list);
        } else {
            onGetDateListener.onFailedGetData();
        }

    }

}


