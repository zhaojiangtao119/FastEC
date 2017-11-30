package com.labelwall.latte.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.labelwall.latte.app.Latte;
import com.labelwall.latte.net.RestClient;
import com.labelwall.latte.net.callback.ISuccess;
import com.labelwall.latte.ui.recycler.DataConverter;
import com.labelwall.latte.ui.recycler.MultipleRecyclerAdapter;

/**
 * Created by Administrator on 2017-11-29.
 */

public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;


    private RefreshHandler(SwipeRefreshLayout refreshLayout,
                          RecyclerView recyclerView,
                          DataConverter dataConverter,
                          PagingBean pagingBean) {
        this.REFRESH_LAYOUT = refreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = dataConverter;
        this.BEAN = pagingBean;
        //监听滑动事件
        REFRESH_LAYOUT.setOnRefreshListener(this);

    }

    public static RefreshHandler create(SwipeRefreshLayout refreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter dataConverter) {
        return new RefreshHandler(refreshLayout, recyclerView, dataConverter, new PagingBean());
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);//开始加载

        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO 测试加载的动画两秒消失
                //TODO 进行网络请求，请求成功后将REFRESH_LAYOUT.setRefreshing(false)放在请求回调的success中
                REFRESH_LAYOUT.setRefreshing(false);//不再进行滚动，圆圈滚动
            }
        }, 2000);
    }

    //请求第一页数据
    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        RestClient.builder()
                //TODO 请求第一页数据
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setTotleNums(object.getInteger("total"))
                                .setPageSize(object.getInteger("page_size"));
                        //设置Adapter,初始化Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();//下拉监听的执行方法
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
