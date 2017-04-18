package life.bean.com.beanlife.entitybiz;

import life.bean.com.beanlife.myInterface.OnRegisterListener;

/**
 * 作者 : bean on 2017/4/18/0018.
 * 注释 :
 */
public class RegisterBiz implements IRegisterBiz {
    @Override
    public void numberRegister(String name,String password1,String password2,OnRegisterListener onRegisterListener) {
            if (name.equals("1")&&password1.equals("1")&&password2.equals("1")){
                //注册成功
                RegisterBean registerBean = new RegisterBean();
                registerBean.setName(name);
                registerBean.setPassword1(password1);
                registerBean.setPassword(password2);
                onRegisterListener.onSuccess(registerBean);
            }else {
                onRegisterListener.onFailed();
            }
    }

    @Override
    public void emailRegister(String name,String password1,String password2,OnRegisterListener onRegisterListener) {
        if (name.equals("1")&&password1.equals("1")&&password2.equals("1")){
            //注册成功
            RegisterBean registerBean = new RegisterBean();
            registerBean.setName(name);
            registerBean.setPassword1(password1);
            registerBean.setPassword(password2);
            onRegisterListener.onSuccess(registerBean);
        }else {
            onRegisterListener.onFailed();
        }
    }
}
