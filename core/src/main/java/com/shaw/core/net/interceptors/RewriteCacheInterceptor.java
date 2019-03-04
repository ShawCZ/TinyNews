package com.shaw.core.net.interceptors;


import com.shaw.core.app.Core;
import com.shaw.core.util.NetUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created on 2019/3/4.
 *
 * @author XCZ
 */
public class RewriteCacheInterceptor implements Interceptor {
	@Override
	public Response intercept(Chain chain) throws IOException {
		Response originalResponse = chain.proceed(chain.request());
		if (NetUtils.isConnected(Core.getApplicationContext())) {
			int maxAge = 60; // 在线缓存在1分钟内可读取
			return originalResponse.newBuilder()
					.removeHeader("Pragma")
					.removeHeader("Cache-Control")
					.header("Cache-Control", "public, max-age=" + maxAge)
					.build();
		} else {
			int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
			return originalResponse.newBuilder()
					.removeHeader("Pragma")
					.removeHeader("Cache-Control")
					.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
					.build();
		}
	}
}
