package com.labelwall.latte.net.download;

import android.os.AsyncTask;

import com.labelwall.latte.net.RestCreator;
import com.labelwall.latte.net.callback.IError;
import com.labelwall.latte.net.callback.IFailure;
import com.labelwall.latte.net.callback.IRequest;
import com.labelwall.latte.net.callback.ISuccess;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017-11-27.
 */

public class DownloadHandler {
    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    private final String DOWNLOAD_DIR;//目录
    private final String EXTENSION;//后缀
    private final String NAME;//下载的文件名

    public DownloadHandler(String url,
                           IRequest request,
                           ISuccess success,
                           IFailure failure,
                           IError error,
                           String downloadDir,
                           String extension,
                           String name) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public final void handlerDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();//开始下载了
        }
        RestCreator.getRestService().download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(REQUEST,SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,response,NAME);
                            //判断文件是否下载完全，
                            if(task.isCancelled()){
                                if(REQUEST != null){
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else{
                            if(ERROR != null){
                                ERROR.onError(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if(FAILURE != null){
                            FAILURE.onFail();
                        }
                    }
                });
    }
}
