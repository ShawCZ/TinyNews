package com.shaw.core.net.scalars.rx;

import android.text.TextUtils;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by shaw on 2017/8/31.
 */

public class RxRestClientBuilder {
	private String mUrl = null;
	private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
	private RequestBody mBody = null;
	private File mFile = null;
	private String mFileName = null;

	RxRestClientBuilder() {
	}

	public final RxRestClientBuilder url(String url) {
		this.mUrl = url;
		return this;
	}

	public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
		PARAMS.putAll(params);
		return this;
	}

	public final RxRestClientBuilder params(String key, Object value) {
		PARAMS.put(key, value);
		return this;
	}

	public final RxRestClientBuilder body(RequestBody body) {
		this.mBody = body;
		return this;
	}

	public final RxRestClientBuilder fileName(String fileName) {
		this.mFileName = fileName;
		return this;
	}

	public final RxRestClientBuilder file(String filePath) {
		this.mFile = new File(filePath);
		return this;
	}

	public final RxRestClientBuilder file(File file) {
		this.mFile = file;
		return this;
	}

	public final RxRestClientBuilder raw(String raw) {
		this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
		return this;
	}

	private void checkFileName() {
		if (TextUtils.isEmpty(mFileName)) {
			if (mFile != null) {
				mFileName = mFile.getName();
			}
		}
	}


	public final RxRestClient build() {
		checkFileName();
		return new RxRestClient(mUrl, PARAMS, mBody, mFile, mFileName);
	}


}
