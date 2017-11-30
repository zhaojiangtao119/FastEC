package com.labelwall.latte.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Administrator on 2017-11-29.
 */

public class MultipleViewHolder extends BaseViewHolder {
    private MultipleViewHolder(View view) {
        super(view);
    }

    //简单的工厂模式获取该对象
    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
