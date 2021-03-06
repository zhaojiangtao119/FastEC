package com.labelwall.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.labelwall.latte.delegates.bottom.BottomItemDelegate;
import com.labelwall.latte.ec.R;
import com.labelwall.latte.ec.R2;
import com.labelwall.latte.ec.main.EcBottomDelegate;
import com.labelwall.latte.ui.recycler.BaseDecoration;
import com.labelwall.latte.ui.refresh.RefreshHandler;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-11-29.
 */

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //RefreshLayout， mRecyclerView，数据转换器IndexDataConverter
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
    }

    //初始化RecyclerView
    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        //设置RecyclerView分割线
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), 5));
        //设置单击事件监听
        final EcBottomDelegate exBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(exBottomDelegate));
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        // true,改变loader形状，120起始的高度，300终止的高度
        mRefreshLayout.setProgressViewOffset(true, 120, 300);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();//初始化SwipeRefreshLayout
        initRecyclerView();//初始化RecyclerView
        //TODO 请求数据，使用懒加载
        //mRefreshHandler.firstPage("请求数据的url");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }
}
