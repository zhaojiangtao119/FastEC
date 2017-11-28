package com.labelwall.latte.net.rx;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017-11-28. Call<String>返回值得类型  RxJava与Retrofit2的整合
 */

public interface RxRestService {
    //Observable可观察者对象
    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String,Object> params);

    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    Observable<String> postRow(@Url String url, @Body ResponseBody responseBody);

    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap Map<String,Object> params);

    @FormUrlEncoded
    @PUT
    Observable<String> putRow(@Url String url, @Body ResponseBody responseBody);


    @DELETE
    Observable<String> delete(@Url String url,@QueryMap Map<String,Object> params);
    //@Streaming一边下载一边写入文件，使用异步
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url,@QueryMap Map<String,Object> params);

    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part MultipartBody.Part file);
}
