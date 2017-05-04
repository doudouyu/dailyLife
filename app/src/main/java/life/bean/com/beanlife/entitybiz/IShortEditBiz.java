package life.bean.com.beanlife.entitybiz;

import java.util.ArrayList;

import life.bean.com.beanlife.myInterface.OnGetDateListener;
import life.bean.com.beanlife.utils.RequestFlag;

/**
 * 作者 : bean on 2017/4/28/0028.
 * 注释 :
 */
public class IShortEditBiz implements IBaseBiz{
    @Override
    public void setData(int what, OnGetDateListener onGetDateListener) {
        if (what == RequestFlag.CHOOSE_MEMBER){
            //如果请求是由请求成员发起的，就做一下处理
            ArrayList list1 = new ArrayList();
            list1.add("爱人");
            list1.add("孩子");
            list1.add("父母");
            list1.add("朋友");
            if (list1.size()>0){
                onGetDateListener.onSuccessGetData(list1);

            }else {
                onGetDateListener.onFailedGetData();
            }
        }else if (what == RequestFlag.CHOOSE_PAY_MODE){
            //如果请求是由请求成员发起的，就做以下处理
            ArrayList list2 = new ArrayList();
            list2.add("现金");
            list2.add("支付宝");
            list2.add("微信支付");
            list2.add("储蓄卡");
            list2.add("信用卡");
            if (list2.size()>0){
                onGetDateListener.onSuccessGetData(list2);

            }else {
                onGetDateListener.onFailedGetData();
            }
        }
    }
}
