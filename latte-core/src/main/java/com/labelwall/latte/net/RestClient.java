package com.labelwall.latte.net;

import android.content.Context;

import com.labelwall.latte.net.callback.IError;
import com.labelwall.latte.net.callback.IFailure;
import com.labelwall.latte.net.callback.IRequest;
import com.labelwall.latte.net.callback.ISuccess;
import com.labelwall.latte.net.callback.RequestCallbacks;
import com.labelwall.latte.ui.LatteLoader;
import com.labelwall.latte.ui.LoaderStyle;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017-11-27.
 */
//建造者模式创建RestClient对象
public class RestClient {

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final ResponseBody BODY;

    private final LoaderStyle LOADER_STYLE;//load的创建
    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      ResponseBody body,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;

        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if(REQUEST != null){
            REQUEST.onRequestStart();
        }
        if(LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);//展示加载
        }
        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            default:
                break;
        }
        if(call != null){
            //执行网络请求，在子线中执行
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(REQUEST,SUCCESS,FAILURE,ERROR,LOADER_STYLE);
    }

    public final void get(){
        request(HttpMethod.GET);
    }
    public final void post(){
        request(HttpMethod.POST);
    }
    public final void put(){
        request(HttpMethod.PUT);
    }
    public final void delete(){
        request(HttpMethod.DELETE);
    }

}
