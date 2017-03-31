package life.bean.com.beanlife.activity;

import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import life.bean.com.beanlife.R;
import life.bean.com.beanlife.utils.Common;

/**
 * 作者 : bean on 2017/2/27/0027.
 * 注释 :
 */
public class NoticeActivity extends BaseActivity{

    private WebView styleWebView;
    private MyWebViewClient webViewClient;

    @Override
    public int getLayoutId() {
        return R.layout.activity_notice;
    }

    @Override
    public void initView() {
        webViewClient = new MyWebViewClient();
        Common.iconId = 2;
        Common.titleSearchId = 1;
        styleWebView = (WebView) findViewById(R.id.notice_web_view);
        styleWebView.getSettings().setJavaScriptEnabled(true);
        styleWebView.getSettings().setSupportZoom(true); // 支持缩放(适配到当前屏幕)
        styleWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 支持内容重新布局,一共有四种方式
        styleWebView.loadUrl("https://github.com/doudouyu/dailyLife/commits/master");
        styleWebView.setWebViewClient(webViewClient);
    }

    @Override
    public void initData() {
        setTitleText("官方通知");


    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            styleWebView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            styleWebView.scrollTo(0, 1000);
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            styleWebView.scrollTo(0, 1000);
            super.onPageStarted(view, url, favicon);
        }
    }

}
