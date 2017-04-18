package life.bean.com.beanlife.presenter;

import life.bean.com.beanlife.activity.Login2Activity;
import life.bean.com.beanlife.entitybiz.RegisterBean;
import life.bean.com.beanlife.entitybiz.RegisterBiz;
import life.bean.com.beanlife.myInterface.OnRegisterListener;
import life.bean.com.beanlife.utils.IntentUtils;
import life.bean.com.beanlife.view.IRegisterView;

/**
 * 作者 : bean on 2017/4/18/0018.
 * 注释 :
 */
public class IRegisterPresenter {
    private IRegisterView iRegisterView;
    private RegisterBiz registerBiz;
    public IRegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
        registerBiz = new RegisterBiz();
    }

    public void login(){
        IntentUtils.startActivity(Login2Activity.class);
    }
    public void emailRegister(){
        registerBiz.emailRegister(iRegisterView.getNumber(), iRegisterView.getPassword1(), iRegisterView.getPassword2(), new OnRegisterListener() {
            @Override
            public void onSuccess(RegisterBean bean) {
                iRegisterView.onSuccessResult();
            }

            @Override
            public void onFailed() {
                iRegisterView.onFailedReason();
            }
        });
    }
    public void numberRegister(){
        registerBiz.emailRegister(iRegisterView.getNumber(), iRegisterView.getPassword1(), iRegisterView.getPassword2(), new OnRegisterListener() {
            @Override
            public void onSuccess(RegisterBean bean) {
                iRegisterView.onSuccessResult();
            }

            @Override
            public void onFailed() {
                iRegisterView.clear();
                iRegisterView.onFailedReason();
            }
        });
    }
}
