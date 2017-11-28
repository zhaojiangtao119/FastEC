package com.labelwall.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Administrator on 2017-11-27.
 */

public class Latte {

    public static Configurator init(Context context){//初始化配置项
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<Object,Object> getConfigurations(){//获取配置项
        return Configurator.getInstance().getLatteConfig();
    }


    public static Context getApplication(){//获取applicationContext
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT);
    }

}
