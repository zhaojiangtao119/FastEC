package com.labelwall.fastec.example;

import com.labelwall.latte.activitys.ProxyActivity;
import com.labelwall.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
