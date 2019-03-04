package com.shaw.core.util.callback;

import java.util.WeakHashMap;

/**
 * Created by Shaw
 */

public class CallbackManager {

    private static final WeakHashMap<Object, IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    private static class Holder {
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance() {
        return Holder.INSTANCE;
    }

    public CallbackManager addCallback(Object tag, IGlobalCallback callback) {
        CALLBACKS.put(tag, callback);
        return this;
    }

    private IGlobalCallback getCallback(Object tag) {
        return CALLBACKS.get(tag);
    }

    public void excute(Object tag, Object arg) {
        IGlobalCallback callback = CALLBACKS.get(tag);
        if (callback != null) {
            callback.executeCallback(arg);
        }else {
            throw new NullPointerException("You need register this callback!");
        }
    }
}
