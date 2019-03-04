package com.shaw.core.util.callback;


import androidx.annotation.Nullable;

/**
 * Created by Shaw
 */

public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
