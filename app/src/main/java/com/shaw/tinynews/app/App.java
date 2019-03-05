package com.shaw.tinynews.app;

import android.app.Application;

import com.shaw.core.app.Core;
import com.shaw.core.net.interceptors.RewriteCacheInterceptor;

/**
 * Created on 2019/3/5.
 *
 * @author XCZ
 */
public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Core.init(this)
				.withApiHost("http://news-at.zhihu.com/api/4/")
				.withInterceptor(new RewriteCacheInterceptor())
				.configure();
	}
}
