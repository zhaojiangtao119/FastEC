package com.labelwall.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.labelwall.latte.delegates.LatteDelegate;
import com.labelwall.latte.ec.R;
import com.labelwall.latte.ec.R2;
import com.labelwall.latte.ec.main.sort.SortDelegate;
import com.labelwall.latte.net.RestClient;
import com.labelwall.latte.net.callback.ISuccess;
import com.labelwall.latte.ui.recycler.MultipleItemEntity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-11-30. 分类的列表
 */

public class VerticalListDelegate extends LatteDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    //初始化RecyclerView
    public void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽点击动画
        mRecyclerView.setItemAnimator(null);
    }

    //懒加载分类数据
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //TODO 请求服务端分类数据
        RestClient.builder()
                .url("")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //获取服务端响应的数据
                        final List<MultipleItemEntity> data =
                                new VerticalListDataConverter().setJsonData(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data,delegate);
                        mRecyclerView.setAdapter(adapter);//将数据适配器设置到RecyclerView中
                    }
                })
                .build()
                .get();
    }
}
