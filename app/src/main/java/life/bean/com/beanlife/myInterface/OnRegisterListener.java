package life.bean.com.beanlife.myInterface;

import life.bean.com.beanlife.entitybiz.RegisterBean;
import life.bean.com.beanlife.entitybiz.UserBean;

/**
 * 作者 : bean on 2017/4/18/0018.
 * 注释 :
 */
public interface OnRegisterListener {
    void onSuccess(RegisterBean bean);
    void onFailed();
}
