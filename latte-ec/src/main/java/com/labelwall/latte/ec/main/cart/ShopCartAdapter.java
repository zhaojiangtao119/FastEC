package com.labelwall.latte.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.joanzapata.iconify.widget.IconTextView;
import com.labelwall.latte.app.Latte;
import com.labelwall.latte.ec.R;
import com.labelwall.latte.ui.recycler.MultipleFields;
import com.labelwall.latte.ui.recycler.MultipleItemEntity;
import com.labelwall.latte.ui.recycler.MultipleRecyclerAdapter;
import com.labelwall.latte.ui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017-12-01.
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {


    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //添加垂直item布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    private boolean mIsSelectedAll = false;
    public void setIsSelectedAll(boolean isSelectedAll){
        this.mIsSelectedAll = isSelectedAll;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                //取出设置到adapter中数据（MultipleItemEntity（ShopCart））
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartFields.TITLE);
                final String desc = entity.getField(ShopCartFields.DESC);
                final int count = entity.getField(ShopCartFields.COUNT);
                final double price = entity.getField(ShopCartFields.PRICE);

                //获取购物车Item的控件，进行数据与控件的绑定
                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);

                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);

                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(count);
                Glide.with(mContext)
                        .load(thumb)
                        .into(imgThumb);
                //在改变左侧的选择框之前，改变全选与否的状态

                entity.setField(ShopCartFields.IS_SELECTED,mIsSelectedAll);
                final boolean isSelected = entity.getField(ShopCartFields.IS_SELECTED);
                //根据数据状态渲染左侧的选择栏
                if(isSelected){
                    iconIsSelected.setTextColor
                            (ContextCompat.getColor(Latte.getApplication(),R.color.app_main));
                }else{
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加左侧的选中点击事件(不能使用接口形式实现单击监听)
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currrentSelected = entity.getField(ShopCartFields.IS_SELECTED);
                        if(currrentSelected){
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartFields.IS_SELECTED,false);
                        }else{
                            iconIsSelected.setTextColor
                                    (ContextCompat.getColor(Latte.getApplication(),R.color.app_main));
                            entity.setField(ShopCartFields.IS_SELECTED,true);
                        }
                    }
                });

                break;
            default:
                break;
        }
    }
}
