package com.labelwall.latte.net;

import android.content.Context;

import com.labelwall.latte.net.callback.IError;
import com.labelwall.latte.net.callback.IFailure;
import com.labelwall.latte.net.callback.IRequest;
import com.labelwall.latte.net.callback.ISuccess;
import com.labelwall.latte.ui.LoaderStyle;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017-11-27.
 */

public class RestClientBuilder {

    private String mUrl = null;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private ResponseBody mBody = null;

    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = ResponseBody.create(MediaType.parse("application/json;chartset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;//默认的loader
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mRequest, mISuccess, mIFailure, mIError, mBody, mLoaderStyle, mContext);
    }
}
