package com.labelwall.latte.ec.main;

import android.graphics.Color;

import com.labelwall.latte.delegates.bottom.BaseBottomDelegate;
import com.labelwall.latte.delegates.bottom.BottomItemDelegate;
import com.labelwall.latte.delegates.bottom.BottomTabBean;
import com.labelwall.latte.delegates.bottom.ItemBuilder;
import com.labelwall.latte.ec.main.cart.ShopCartDelegate;
import com.labelwall.latte.ec.main.discover.DiscoverDelegate;
import com.labelwall.latte.ec.main.index.IndexDelegate;
import com.labelwall.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017-11-29.
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder itemBuilder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return itemBuilder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
