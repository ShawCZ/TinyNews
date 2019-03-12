package com.shaw.tinynews.module.images;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Created on 2019/3/12.
 *
 * @author XCZ
 */
public class ImagesViewPager extends ViewPager {
	public ImagesViewPager(@NonNull Context context) {
		super(context);
	}

	public ImagesViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	//解决Photo View导致的问题
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			return super.onInterceptTouchEvent(ev);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
