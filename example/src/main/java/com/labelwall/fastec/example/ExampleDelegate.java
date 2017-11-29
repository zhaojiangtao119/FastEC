package com.labelwall.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.labelwall.fastec.R;
import com.labelwall.latte.delegates.LatteDelegate;
import com.labelwall.latte.net.RestClient;
import com.labelwall.latte.net.RestCreator;
import com.labelwall.latte.net.callback.IError;
import com.labelwall.latte.net.callback.IFailure;
import com.labelwall.latte.net.callback.ISuccess;
import com.labelwall.latte.net.rx.RxRestClient;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017-11-27.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //testRestClient();
        //testCallRxGet()
        //testRxRestClient();
    }

    //TODO 测试ReatClient
    private void testRestClient() {
        RestClient.builder()
                .url("https://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFail() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();


    }

    //TODO 测试RxGet耗时操作不允许在UI线程操作，强制在非UI线程操作
    private void testCallRxGet() {
        final String url = "https://www.baidu.com";
        final WeakHashMap<String, Object> params = new WeakHashMap<>();
        //get请求
        final Observable<String> observable = RestCreator.getRxRestService().get(url, params);
        observable.subscribeOn(Schedulers.io())//在io线程中处理网络请求
                .observeOn(AndroidSchedulers.mainThread())//RxAndroid,在android的主线程中处理请求结果
                .subscribe(new Observer<String>() {//请求的生命周期，
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //TODO 测试RxReatClient,不需要创建Callback,异步处理
    private void testRxRestClient() {
        final String url = "https://www.baidu.com";
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())//在io线程中处理网络请求
                .observeOn(AndroidSchedulers.mainThread())//RxAndroid,在android的主线程中处理请求结果
                .subscribe(new Observer<String>() {//请求的生命周期，
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
