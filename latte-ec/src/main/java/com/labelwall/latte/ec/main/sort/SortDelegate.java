package com.labelwall.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.labelwall.latte.delegates.bottom.BottomItemDelegate;
import com.labelwall.latte.ec.R;

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
}
