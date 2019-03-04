package com.shaw.core.net.interceptors;


import com.shaw.core.app.Core;
import com.shaw.core.util.NetUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created on 2019/3/4.
 *
 * @author XCZ
 */
public class ForceCacheInterceptor implements Interceptor {
	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		if (!NetUtils.isConnected(Core.getApplicationContext())) {
			// 没有网络连接
			request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
		}
		return chain.proceed(request);
	}
}
