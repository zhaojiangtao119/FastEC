package com.labelwall.latte.delegates.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.labelwall.latte.app.ConfigKeys;
import com.labelwall.latte.app.Latte;
import com.labelwall.latte.delegates.web.IPageLoadListener;
import com.labelwall.latte.delegates.web.WebDelegate;
import com.labelwall.latte.delegates.web.route.Router;
import com.labelwall.latte.ui.loader.LatteLoader;
import com.labelwall.latte.util.log.LatteLogger;
import com.labelwall.latte.util.storage.LattePreference;

/**
 * Created by Administrator on 2017-11-30.
 */

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Latte.getHandler();

    public void setIPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading", url);

        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.stopLoading();
            }
        }, 1000);
    }

    //获取浏览器的cookie
    private void syncCookie() {
        final CookieManager manager = CookieManager.getInstance();
        /*
            这里的Cookie与API请求的Cookie是不一样的，这个在网页里不可见
        */
        final String webHost = (String) Latte.getConfigurations().get(ConfigKeys.WEB_HOST);
        if (webHost != null) {
            if (manager.hasCookies()) {
                final String cookieStr = manager.getCookie(webHost);
                if (cookieStr != null && !cookieStr.equals("")) {
                    LattePreference.addCustomAppProfile("cookie", cookieStr);//将获取的cookie存储起来
                }
            }
        }
    }
}
