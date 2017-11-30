package com.labelwall.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.labelwall.latte.delegates.bottom.BottomItemDelegate;
import com.labelwall.latte.ec.R;
import com.labelwall.latte.ec.main.sort.content.ContentDelegate;
import com.labelwall.latte.ec.main.sort.list.VerticalListDelegate;

/**
 * Created by Administrator on 2017-11-29.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container,listDelegate);
        //默认加载第一个分类的内容
        loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
