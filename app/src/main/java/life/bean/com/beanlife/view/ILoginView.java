package life.bean.com.beanlife.view;

import java.util.List;

import life.bean.com.beanlife.entitybiz.UserBean;

/**
 * 作者 : bean on 2017/4/18/0018.
 * 注释 :主界面的View
 */
public interface ILoginView extends IBaseView{
    String getName();

    String getPassword();

//    //登录成功
//    void toMain(UserBean bean);
//
//    //登陆失败
//    void loginFailed();
}
