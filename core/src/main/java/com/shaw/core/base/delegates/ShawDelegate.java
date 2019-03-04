package com.shaw.core.base.delegates;

/**
 * Created by shaw on 2017/8/31.
 */

public abstract class ShawDelegate extends BaseDelegate {

    @SuppressWarnings("unchecked")
    public <T extends ShawDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
