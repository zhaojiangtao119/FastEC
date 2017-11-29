package com.labelwall.latte.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.labelwall.latte.R;
import com.labelwall.latte.delegates.LatteDelegate;


public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if(rootView != null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if((System.currentTimeMillis() - TOUCH_TIME) > WAIT_TIME){
                Toast.makeText(getContext(), "双击推出"+R.string.app_name, Toast.LENGTH_SHORT).show();
                TOUCH_TIME = System.currentTimeMillis();
            }else{
                _mActivity.finish();
                if(TOUCH_TIME != 0){
                    TOUCH_TIME = 0;
                }
            }
            return true;
        }
        return false;
    }
}
