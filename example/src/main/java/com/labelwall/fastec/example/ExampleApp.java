package com.labelwall.fastec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.labelwall.fastec.R;
import com.labelwall.latte.app.Latte;
import com.labelwall.latte.ec.FontEcModule;
import com.labelwall.latte.net.interceptors.DebugInterceptor;

/**
 * Created by Administrator on 2017-11-27.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }
}
