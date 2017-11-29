package com.labelwall.latte.delegates.bottom;

import android.widget.Toast;

import com.labelwall.latte.R;
import com.labelwall.latte.app.Latte;
import com.labelwall.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.ISupportFragment;



public abstract class BottomItemDelegate1 extends LatteDelegate implements ISupportFragment {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latte.getApplication().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
