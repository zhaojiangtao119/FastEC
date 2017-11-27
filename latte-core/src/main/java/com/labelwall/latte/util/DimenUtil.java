package com.labelwall.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.labelwall.latte.app.Latte;

/**
 * Created by Administrator on 2017-11-27.
 */

public class DimenUtil {
    //获取屏幕的宽高
    public static int getScreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

}
