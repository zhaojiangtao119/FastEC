package com.labelwall.latte.delegates.web.chromclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Administrator on 2017-11-30.
 */

public class WebChromeClientImpl  extends WebChromeClient{

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
