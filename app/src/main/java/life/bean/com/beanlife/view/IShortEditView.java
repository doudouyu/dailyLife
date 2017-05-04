package life.bean.com.beanlife.view;

import android.content.Intent;

import life.bean.com.beanlife.utils.RequestFlag;

/**
 * 作者 : bean on 2017/4/28/0028.
 * 注释 :
 */
public interface IShortEditView extends IBaseView {


    void setAddMemberText(String title_name);

    void setPayModeText(String title_name);

    void startMyActivity(Intent intent, int what);
}
