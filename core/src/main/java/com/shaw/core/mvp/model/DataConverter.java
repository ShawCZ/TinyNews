package com.shaw.core.mvp.model;

import android.text.TextUtils;

/**
 * Created on 2018/12/18.
 *
 * @author XCZ
 */
public abstract class DataConverter<T> {
	public final String TAG = getClass().getSimpleName();
	private final String data;

	public DataConverter(String data) {
		this.data = data;
	}

	public String getJsonData() {
		if (TextUtils.isEmpty(data)) {
			throw new NullPointerException("DATA IS BULL!!!");
		}
		return data;
	}

	public abstract T convert();
}
