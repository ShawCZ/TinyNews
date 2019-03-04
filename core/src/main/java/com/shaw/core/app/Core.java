package com.shaw.core.app;

import android.content.Context;
import android.os.Handler;

/**
 * Created by shaw on 2017/8/30.
 */

public final class Core {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    //获取全局Context
    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    //获取全局的Handler
    public static Handler getHandler(){
        return getConfiguration(ConfigKeys.HANDLER);
    }
}
