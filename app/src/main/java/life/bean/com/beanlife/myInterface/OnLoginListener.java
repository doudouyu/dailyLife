package life.bean.com.beanlife.myInterface;

import java.util.List;

import life.bean.com.beanlife.entitybiz.UserBean;

/**
 * 作者 : bean on 2017/4/18/0018.
 * 注释 :
 */
public interface OnLoginListener {
    void onSuccess(List<UserBean> bean);
    void onFailed();
}
