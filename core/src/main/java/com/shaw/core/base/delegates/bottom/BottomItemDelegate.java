package com.shaw.core.base.delegates.bottom;

import android.widget.Toast;

import com.shaw.core.R;
import com.shaw.core.app.Core;
import com.shaw.core.base.delegates.ShawDelegate;

/**
 * Created by shaw on 2017/9/6.
 */

public abstract class BottomItemDelegate extends ShawDelegate {
    //再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Core.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
