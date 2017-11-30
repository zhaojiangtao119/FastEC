package com.labelwall.latte.ec.main.index;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.labelwall.latte.ec.R;

/**
 * Created by Administrator on 2017-11-30.  沉浸式状态栏的实现，改变toolbar颜色
 */

@SuppressWarnings("unused")
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    //顶部的距离
    private int mDistanceY = 0;
    //颜色变化的速度
    private static final int SHOW_SPEED = 3;
    //设置颜色


    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        //增加滑动距离
        mDistanceY += dy;
        //toolbar的高度
        final int targetHeight = child.getBottom();

        //当滑动时，并且距离小于toolbar，高度的时候，调整渐变色
        if (mDistanceY > 0 && mDistanceY < targetHeight) {
            final float scale = (float) mDistanceY / targetHeight;
            final float alpha = scale * 255;
            //设置child的颜色
            //child.setBackgroundColor(Color.argb((int)alpha,1,1,1));
        }else if(mDistanceY > targetHeight){
            //child.setBackgroundColor(Color.rgb(1,1,1));
        }
    }
}
