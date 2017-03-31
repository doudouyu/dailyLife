package life.bean.com.beanlife.fragment;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import life.bean.com.beanlife.R;

/**
 * 作者 : bean on 2017/3/24/0024.
 * 注释 :
 */
public class MakeMoneyFragment extends BaseFragment{

    private WebView styleWebView;
    private MyWebViewClient webViewClient;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_make_money;
    }

    @Override
    public void initView(View view) {
        webViewClient = new MyWebViewClient();
        styleWebView = (WebView)view.findViewById(R.id.notice_web_view);
        styleWebView.getSettings().setJavaScriptEnabled(true);
        styleWebView.getSettings().setSupportZoom(true); // 支持缩放(适配到当前屏幕)
        styleWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); // 支持内容重新布局,一共有四种方式
        styleWebView.loadUrl("https://github.com/doudouyu/dailyLife/commits/master");
        styleWebView.setWebViewClient(webViewClient);
    }

    @Override
    public void initData() {

    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            styleWebView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    }
}
