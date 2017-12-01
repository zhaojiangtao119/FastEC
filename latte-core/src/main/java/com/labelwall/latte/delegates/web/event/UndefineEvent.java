package com.labelwall.latte.delegates.web.event;

import com.labelwall.latte.util.log.LatteLogger;

/**
 * Created by Administrator on 2017-11-30.
 */

public class UndefineEvent extends Event {

    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent",params);
        return null;
    }
}
