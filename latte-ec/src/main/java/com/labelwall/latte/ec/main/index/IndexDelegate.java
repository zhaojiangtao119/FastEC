package com.labelwall.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.labelwall.latte.delegates.bottom.BottomItemDelegate;
import com.labelwall.latte.ec.R;

/**
 * Created by Administrator on 2017-11-29.
 */

public class IndexDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
