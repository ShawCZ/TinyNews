package com.shaw.tinynews;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shaw.core.util.glide.GlideApp;
import com.shaw.core.util.storage.SPUtil;
import com.shaw.tinynews.app.Constant;
import com.shaw.tinynews.service.SplashService;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created on 2019/3/5.
 *
 * @author XCZ
 */
public class SplashActivity extends AppCompatActivity {
	private static final String TAG = "SplashActivity";
	private static final RequestOptions OPTIONS = new RequestOptions()
			.centerCrop()
			.diskCacheStrategy(DiskCacheStrategy.ALL)
			.dontAnimate();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		AppCompatImageView imageView = findViewById(R.id.image);

		String splashFileUrl = SPUtil.getCustomAppProfile(Constant.SPLASH_FILE_URL);
		Log.d(TAG, "onCreate: splashFileUrl = " + splashFileUrl);
		if (!TextUtils.isEmpty(splashFileUrl)) {
			GlideApp.with(imageView)
					.load(splashFileUrl)
					.apply(OPTIONS)
					.into(imageView);
		} else {
			GlideApp.with(imageView)
					.load(R.drawable.splash)
					.apply(OPTIONS)
					.into(imageView);
		}
		startService(new Intent(this, SplashService.class));
	}
}
