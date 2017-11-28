package com.labelwall.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.labelwall.latte.delegates.LatteDelegate;
import com.labelwall.latte.ec.R;
import com.labelwall.latte.ec.R2;
import com.labelwall.latte.ui.launcher.ScrollLauncherTag;
import com.labelwall.latte.util.storage.LattePreference;
import com.labelwall.latte.util.timer.BaseTimerTask;
import com.labelwall.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017-11-28.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener{
    //使用butterknife来初始化组件
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        //task执行的任务，0延迟的时间，任务执行的间隔时间1000
        mTimer.schedule(task,0,1000);
    }

    @Override
    public Object setLayout() {

        return R.layout.delegate_launcher;//设置启动页面View
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    //是否展示滑动启动页面
    private void checkIsShowScroll(){
        if(!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);//启动轮播页面
        }else {
            //todo 检查用户是否登录了App

        }
    }

    @Override
    public void OnTimer() {
        //设置倒计时
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTvTimer != null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if(mCount < 0){
                        if(mTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}