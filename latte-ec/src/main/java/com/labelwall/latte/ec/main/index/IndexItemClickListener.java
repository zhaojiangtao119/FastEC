package com.labelwall.latte.ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.labelwall.latte.delegates.LatteDelegate;
import com.labelwall.latte.ec.detail.GoodsDetailDelegate;

/**
 * Created by Administrator on 2017-11-30.通用的点击事件
 */

public class IndexItemClickListener extends SimpleClickListener{


    private final LatteDelegate DELEGATE;

    private IndexItemClickListener(LatteDelegate delegate) {
        this.DELEGATE = delegate;
    }
    public static IndexItemClickListener create(LatteDelegate delegate){
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //处理点击事件
        final  GoodsDetailDelegate goodsDetailDelegate = GoodsDetailDelegate.create();
        DELEGATE.start(goodsDetailDelegate);
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
