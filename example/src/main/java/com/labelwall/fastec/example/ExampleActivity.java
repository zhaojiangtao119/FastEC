package com.labelwall.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.labelwall.latte.activitys.ProxyActivity;
import com.labelwall.latte.delegates.LatteDelegate;
import com.labelwall.latte.ec.launcher.LauncherDelegate;
import com.labelwall.latte.ec.main.EcBottomDelegate;
import com.labelwall.latte.ec.sign.ISignListener;
import com.labelwall.latte.ui.launcher.ILauncherListener;
import com.labelwall.latte.ui.launcher.OnLauncherFinishTag;

public class ExampleActivity extends ProxyActivity implements
        ISignListener, ILauncherListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {

    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        //launcher结束通过监听判断是否登陆，执行对应的操作
        switch (tag) {
            //已经登录
            case SIGNED:
                Toast.makeText(this, "登录", Toast.LENGTH_LONG).show();
                break;
            //未登录
            case NOT_SIGNED:
                Toast.makeText(this, "未登录", Toast.LENGTH_LONG).show();
                //start的同时将栈中上一个元素清除
                //startWithPop(new SignInDelegate());//跳转到登录页面
                startWithPop(new EcBottomDelegate());
                break;
            default:
                break;
        }
    }
}
