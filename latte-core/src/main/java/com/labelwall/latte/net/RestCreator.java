package com.labelwall.latte.net;

import com.labelwall.latte.app.ConfigType;
import com.labelwall.latte.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017-11-27.
 */

public class RestCreator {

    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    //将restService返回出去
    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    //初始化retrofit
    private static final class RetrofitHandler {
        //获取初始化application是传入的base_url
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())//转换器
                .build();
    }

    //初始化okHttp
    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    //初始化RestService
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHandler.RETROFIT_CLIENT.create(RestService.class);
    }
}
