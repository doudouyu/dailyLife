package life.bean.com.beanlife.view;

/**
 * 作者 : bean on 2017/4/18/0018.
 * 注释 :
 */
public interface IRegisterView extends IBaseView{
    String getNumber();
    String getPassword1();
    String getPassword2();
//    void onSuccessResult();
//    void onFailedReason();

    void clear();
}
