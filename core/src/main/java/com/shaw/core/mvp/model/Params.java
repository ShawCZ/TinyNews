package com.shaw.core.mvp.model;

import java.util.WeakHashMap;

/**
 * Created on 2018/12/25.
 *
 * @author XCZ
 */
public class Params {
	private final WeakHashMap<String, Object> PARAMS;

	private Params() {
		PARAMS = new WeakHashMap<>();
	}

	public static Params builder() {
		return new Params();
	}

	public Params put(String key, Object value) {
		PARAMS.put(key, value);
		return this;
	}

	public WeakHashMap<String, Object> build() {
		return PARAMS;
	}
}
