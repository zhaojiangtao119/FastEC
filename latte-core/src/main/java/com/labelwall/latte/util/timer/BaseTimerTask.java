package com.labelwall.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by Administrator on 2017-11-28.
 */

public class BaseTimerTask extends TimerTask{

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if(mITimerListener != null){
            mITimerListener.OnTimer();
        }
    }
}
