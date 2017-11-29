package com.labelwall.latte.app;

import com.labelwall.latte.util.storage.LattePreference;

/**
 * Created by Administrator on 2017-11-28. 保存用户的登录状态，判断用户的登录状态
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG //登录标志
    }

    //保存用户的登录状态，登录后调用
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    //已经登录
    public static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    //检查用户是否登录
    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();//回调已经登录
        } else {
            checker.onNotSignIn();//回调已经未登录
        }
    }

}
