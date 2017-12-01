package com.labelwall.fastec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.labelwall.fastec.R;
import com.labelwall.latte.app.Latte;
import com.labelwall.fastec.example.event.TestEvent;
import com.labelwall.latte.ec.database.DatabaseManager;
import com.labelwall.latte.ec.icon.FontEcModule;
import com.labelwall.latte.net.interceptors.DebugInterceptor;
import com.labelwall.latte.net.rx.AddCookieInterceptor;

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
                .withJavascriptInterface("latte")
                .withWebEvent("test123",new TestEvent())
                //添加cookie同步拦截器
                .withInterceptor(new AddCookieInterceptor())
                //
                .withWebHost("https://www.baidu.com/")
                .configure();
        //初始化greenDao
        DatabaseManager.getInstance().init(this);

        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
                        );
    }
}
