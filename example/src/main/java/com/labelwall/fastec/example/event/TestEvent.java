package com.labelwall.fastec.example.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.labelwall.latte.delegates.web.event.Event;

/**
 * Created by Administrator on 2017-11-30.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        if(getAction().equals("test123")){
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();",null);
                }
            });
        }
        return null;
    }
}
