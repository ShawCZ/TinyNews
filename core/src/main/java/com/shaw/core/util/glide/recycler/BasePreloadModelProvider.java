package com.shaw.core.util.glide.recycler;

import android.app.Activity;
import android.text.TextUtils;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.shaw.core.util.glide.GlideApp;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created on 2018/12/26.
 *
 * @author XCZ
 */
public abstract class BasePreloadModelProvider<T> implements ListPreloader.PreloadModelProvider<String> {
	private final Activity mActivity;
	private final List<T> mUrls;

	public BasePreloadModelProvider(Activity activity, List<T> urls) {
		mActivity = activity;
		mUrls = urls;
	}

	@NonNull
	@Override
	public List<String> getPreloadItems(int position) {
		String url = getUrl(mUrls.get(position));
		if (TextUtils.isEmpty(url)) {
			return Collections.emptyList();
		}
		return Collections.singletonList(url);
	}

	@Nullable
	@Override
	public RequestBuilder getPreloadRequestBuilder(@NonNull String url) {
		return GlideApp.with(mActivity)
				.load(url);
	}

	public abstract <T>String getUrl(T t);
}
