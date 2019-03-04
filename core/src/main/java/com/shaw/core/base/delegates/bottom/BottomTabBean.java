package com.shaw.core.base.delegates.bottom;

import androidx.annotation.DrawableRes;

/**
 * Created by shaw on 2017/9/6.
 */

public final class BottomTabBean {

    //变量定义为final类型，在构造函数里面传值赋值，可以防止多线程中的问题
    private final int ICON;
    private final CharSequence TITLE;

    public BottomTabBean(int icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    @DrawableRes
    public int getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
