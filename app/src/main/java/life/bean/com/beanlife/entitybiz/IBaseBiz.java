package life.bean.com.beanlife.entitybiz;

import life.bean.com.beanlife.myInterface.OnGetDateListener;

/**
 * 作者 : bean on 2017/4/25/0025.
 * 注释 :
 */
public interface IBaseBiz {
    /**
     *
     * @param what  区别是哪一个请求
     * @param onGetDateListener 请求结果的处理
     */
     void setData(int what ,OnGetDateListener onGetDateListener);
}
