package com.labelwall.latte.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.labelwall.latte.R;
import com.labelwall.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Administrator on 2017-11-27.
 */

public abstract class ProxyActivity extends SupportActivity{

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if(savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
