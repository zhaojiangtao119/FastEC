package com.labelwall.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;

/**
 * Created by Administrator on 2017-11-27.
 */

public class Configurator {

    private static final HashMap<Object,Object> LATTE_CONFIGS = new HashMap<>();//配置项
    private static final List<IconFontDescriptor> ICONS = new ArrayList<>();//字体
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();//网络拦截器

    private Configurator(){
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY,false);//配置开始
    }

    public HashMap<Object,Object> getLatteConfig() {
        return LATTE_CONFIGS;
    }

    //线程安全的懒汉模式的单例
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    public final void configure(){
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY,true);//设置application配置结束
    }

    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }

    public void initIcons(){
        if(ICONS.size() > 0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for(int i = 1; i<ICONS.size();i++){
                initializer.with(ICONS.get(1));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if(!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigKeys> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);
    }



}
