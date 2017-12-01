package com.labelwall.latte.ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.labelwall.latte.delegates.bottom.BottomItemDelegate;
import com.labelwall.latte.ec.R;
import com.labelwall.latte.ec.R2;
import com.labelwall.latte.net.RestClient;
import com.labelwall.latte.net.callback.ISuccess;
import com.labelwall.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017-12-01.
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess {

    private ShopCartAdapter mAdapter = null;
    //购物中item位置的标记，从0开始
    private int mCurrentCount = 0;
    //需要移除item的总数
    private int mTotalCount = 0;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;
    //全选单击事件监听
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIconSelectAll = null;
    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectAll(){
        final int tag = (int) mIconSelectAll.getTag();
        if(tag == 0){
            mIconSelectAll.setTextColor(ContextCompat.getColor(getContext(),R.color.app_main));
            mIconSelectAll.setTag(1);
            mAdapter.setIsSelectedAll(true);
            //跟新RecyclerView的显示状态
            mAdapter.notifyItemRangeChanged(0,mAdapter.getItemCount());
        }else{
            mIconSelectAll.setTextColor(Color.GRAY);
            mIconSelectAll.setTag(0);
            mAdapter.setIsSelectedAll(false);
            mAdapter.notifyItemRangeChanged(0,mAdapter.getItemCount());
        }
    }

    //右上角的删除
    @OnClick(R2.id.tc_top_shop_cart_remove_selected)
    void onClickRemoveSelectedItem(){
        final List<MultipleItemEntity> data = mAdapter.getData();
        //要删除的数据
        final List<MultipleItemEntity> deleteEntities = new ArrayList<>();
        for(MultipleItemEntity entity : data){
            final boolean isSelected = entity.getField(ShopCartFields.IS_SELECTED);
            if(isSelected){
                deleteEntities.add(entity);
            }
        }
        for(MultipleItemEntity entity : deleteEntities){
            int removePosition;
            final int entityPosition = entity.getField(ShopCartFields.POSITION);
            if(entityPosition > mCurrentCount - 1){
                removePosition = entityPosition - (mTotalCount - mCurrentCount);
            }else{
                removePosition = entityPosition;
            }
            if(removePosition <= mAdapter.getItemCount()){
                mAdapter.remove(removePosition);
                mCurrentCount = mAdapter.getItemCount();
                //更新数据
                mAdapter.notifyItemRangeChanged(removePosition,mAdapter.getItemCount());
            }
        }
    }

    //清空
    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear(){
        mAdapter.getData().clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIconSelectAll.setTag(0);//设置初始的全选状态
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                //TODO 请求服务端的购物车数据
                .url("")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        //获取的服务端响应的购物车数据，进行数据的转化
        List<MultipleItemEntity> data =
                new ShopCartDataConverter().setJsonData(response).convert();
        //初始化RecycleView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ShopCartAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
    }
}
