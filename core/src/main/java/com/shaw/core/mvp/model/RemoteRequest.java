package com.shaw.core.mvp.model;

import com.shaw.core.net.scalars.rx.RxRestClient;

import java.util.WeakHashMap;

import io.reactivex.Observable;

/**
 * Created on 2018/12/29.
 *
 * @author XCZ
 */
public class RemoteRequest {
	public static Observable<String> get(String url) {
		return get(url, Params.builder().build());
	}

	public static Observable<String> post(String url) {
		return get(url, Params.builder().build());
	}

	public static Observable<String> get(String url, WeakHashMap<String, Object> params) {
		return getClient(url, params).get();
	}

	public static Observable<String> post(String url, WeakHashMap<String, Object> params) {
		return getClient(url, params).post();
	}

	private static RxRestClient getClient(String url, WeakHashMap<String, Object> params) {
		return RxRestClient.builder()
				.url(url)
				.params(params)
				.build();
	}
}
