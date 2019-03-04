package com.shaw.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.shaw.core.app.Core;


/**
 * Created by shaw on 2017/8/31.
 */

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Core.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Core.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
