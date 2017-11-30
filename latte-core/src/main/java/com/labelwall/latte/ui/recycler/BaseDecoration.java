package com.labelwall.latte.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by Administrator on 2017-11-30.
 */

public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    //简单工厂模式的单例模式
    public static BaseDecoration create(@ColorInt int color, int size){
        return new BaseDecoration(color,size);
    }
}
