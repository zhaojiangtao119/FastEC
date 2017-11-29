package com.labelwall.latte.ec.sign;

/**
 * Created by Administrator on 2017-11-28.
 */

public interface ISignListener {
    //登陆成功，注册成功监听
    void onSignInSuccess();

    void onSignUpSuccess();
}
