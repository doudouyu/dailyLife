package life.bean.com.beanlife;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.cl253.smssdk.lib.SMSSDK;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * 作者 : bean on 2017/3/21/0021.
 * 注释 :
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        SMSSDK.initSDK(this,"df16c280304d46f58ae43e6b48734b","20847173102bcd8d11dfad36a3f4b1");
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=12345678");
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base); MultiDex.install(this);
    }


}
