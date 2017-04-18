package life.bean.com.beanlife.entitybiz;

import life.bean.com.beanlife.myInterface.OnLoginListener;

/**
 * 作者 : bean on 2017/4/18/0018.
 * 注释 :
 */
public class UserBiz implements IUserBiz {
    @Override
    public void login(final String name, final String password, final OnLoginListener onLoginListener) {
        //进行网络请求，获取数据返回给主界面
        //登陆成功
        if (name.equals("ydd") && password.equals("123")) {
            //把返回的数据返给主界面（在这里是模拟的）
            UserBean bean = new UserBean();
            bean.setUsername(name);
            bean.setPassWord(password);
            onLoginListener.onSuccess(bean);
        } else {
            //登陆失败
            onLoginListener.onFailed();
        }

    }
}
