package com.shaw.tinynews.api;

import com.shaw.core.app.ConfigKeys;
import com.shaw.core.app.Core;
import com.shaw.core.net.interceptors.ForceCacheInterceptor;
import com.shaw.core.net.interceptors.RewriteCacheInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 2019/3/4.
 *
 * @author XCZ
 */
public class RestClient {
	private static OkHttpClient mOkHttpClient;
	private static Retrofit mRetrofit;
	private static TinyService mTinyService;

	private static final int DEFAULT_TIMEOUT = 10;
	private static final int CACHE_SIZE = 10 * 1024 * 1024;

	public static TinyService createService() {
		if (mOkHttpClient == null) {
			File cacheFile = new File(Core.getApplicationContext().getCacheDir(), "okHttpCache");
			RewriteCacheInterceptor interceptor = new RewriteCacheInterceptor();
			ForceCacheInterceptor forceCacheInterceptor = new ForceCacheInterceptor();

			mOkHttpClient = new OkHttpClient.Builder()
					// 设置超时时间
					.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
					.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
					.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
					// 添加缓存
					.cache(new Cache(cacheFile, CACHE_SIZE))
					// 添加拦截器
					.addNetworkInterceptor(interceptor)
					.addInterceptor(interceptor)
					.addInterceptor(forceCacheInterceptor)
					.build();
		}
		if (mRetrofit == null) {
			mRetrofit = new Retrofit.Builder()
					.client(mOkHttpClient)
					.baseUrl((String) Core.getConfiguration(ConfigKeys.API_HOST))
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.build();
		}
		if (mTinyService == null) {
			mTinyService = mRetrofit.create(TinyService.class);
		}
		return mTinyService;
	}
}
