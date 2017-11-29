package com.labelwall.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.labelwall.latte.app.AccountManager;
import com.labelwall.latte.ec.database.DatabaseManager;
import com.labelwall.latte.ec.database.UserProfile;

/**
 * Created by Administrator on 2017-11-28.
 */

/**
 * 登录注册响应成功后的回调，改变登录状态
 */
public class SignHandler {

    //注册成功服务端返回之后操作
    public static void onSignUp(String response, ISignListener signListener) {
        //将json字符串转化为json对象
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getUserDao().insert(profile);//将数据插入到数据库

        //保存用户状态，注册并登录成功
        AccountManager.setSignState(true);
        //设置设置注册成功的回调
        signListener.onSignUpSuccess();
    }

    //登录成功服务端返回之后操作
    public static void onSignIn(String response, ISignListener signListener) {
        //将json字符串转化为json对象
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getUserDao().insert(profile);//将数据插入到数据库

        //保存用户状态，登录成功
        AccountManager.setSignState(true);
        //设置登陆成功的回调
        signListener.onSignInSuccess();
    }
}
