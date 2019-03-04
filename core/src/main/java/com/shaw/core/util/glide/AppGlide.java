package com.shaw.core.util.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.InputStream;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;

/**
 * Created on 2018/12/17.
 *
 * @author XCZ
 */
@GlideModule
public class AppGlide extends AppGlideModule {
	//设置图片加载策略
	public static final RequestOptions RECYCLER_OPTIONS =
			new RequestOptions()
					.centerCrop()
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					.dontAnimate();

	@Override
	public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
		int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
		int diskCacheSizeBytes = 1024 * 1024 * 100;  //100 MB
		builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes))
				.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSizeBytes));
	}
	@Override
	public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
		// 自定义OkHttpClient
		OkHttpClient client = UnsafeOkHttpClient.getUnsafeOkHttpClient();
		// 采用自定义的CustomOkHttpUrlLoader
		registry.replace(GlideUrl.class, InputStream.class, new CustomOkHttpUrlLoader.Factory(client));
	}

	@Override
	public boolean isManifestParsingEnabled() {
		return false;
	}
}
