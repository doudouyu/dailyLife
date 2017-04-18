package life.bean.com.beanlife.presenter;

import life.bean.com.beanlife.activity.Register2Activity;
import life.bean.com.beanlife.activity.RegisterActivity;
import life.bean.com.beanlife.activity.UpdatePasswordActivity;
import life.bean.com.beanlife.myInterface.OnLoginListener;
import life.bean.com.beanlife.entitybiz.UserBean;
import life.bean.com.beanlife.entitybiz.UserBiz;
import life.bean.com.beanlife.utils.IntentUtils;
import life.bean.com.beanlife.view.ILoginView;

/**
 * 作者 : bean on 2017/4/18/0018.
 * 注释 :
 */
public class ILoginPresenter {
    private ILoginView iMainView;
    private UserBiz biz ;

    public ILoginPresenter(ILoginView iMainView) {
        this.iMainView = iMainView;
        biz = new UserBiz();
    }



    public void login() {
        biz.login(iMainView.getName(), iMainView.getPassword(), new OnLoginListener() {
            @Override
            public void onSuccess(UserBean bean) {
                iMainView.toMain(bean);
            }

            @Override
            public void onFailed() {
                iMainView.loginFailed();
            }
        });
    }
    public void register(){
        IntentUtils.startActivity(Register2Activity.class);
    }




    public void updatePassword() {
        IntentUtils.startActivity(UpdatePasswordActivity.class);
    }
}
