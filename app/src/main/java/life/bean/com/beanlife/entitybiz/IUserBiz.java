package life.bean.com.beanlife.entitybiz;

import life.bean.com.beanlife.myInterface.OnLoginListener;

/**
 * 作者 : bean on 2017/4/18/0018.
 * 注释 :
 */
public interface IUserBiz {
   void login(String name, String password, OnLoginListener onLoginListener);
}
