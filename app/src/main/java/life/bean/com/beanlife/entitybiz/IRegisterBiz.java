package life.bean.com.beanlife.entitybiz;

import life.bean.com.beanlife.myInterface.OnRegisterListener;

/**
 * 作者 : bean on 2017/4/18/0018.
 * 注释 :
 */
public interface IRegisterBiz {
    void numberRegister(String name,String password1,String password2,OnRegisterListener onRegisterListener);
    void emailRegister(String name,String password1,String password2,OnRegisterListener onRegisterListener);
}
