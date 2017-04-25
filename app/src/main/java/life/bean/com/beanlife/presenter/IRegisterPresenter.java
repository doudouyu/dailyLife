package life.bean.com.beanlife.presenter;

import java.util.ArrayList;
import java.util.List;

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
                List<RegisterBean> list = new ArrayList();
                list.add(bean);
                iRegisterView.RefreshView(list);
            }

            @Override
            public void onFailed() {
                iRegisterView.showFailedError();
            }
        });
    }
    public void numberRegister(){
        registerBiz.emailRegister(iRegisterView.getNumber(), iRegisterView.getPassword1(), iRegisterView.getPassword2(), new OnRegisterListener() {
            @Override
            public void onSuccess(RegisterBean bean) {
                List<RegisterBean> list = new ArrayList();
                list.add(bean);
                iRegisterView.RefreshView(list);
            }

            @Override
            public void onFailed() {
                iRegisterView.clear();
                iRegisterView.showFailedError();
            }
        });
    }
}
