package com.shaw.tinynews;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shaw.core.util.glide.GlideApp;
import com.shaw.core.util.storage.SPUtil;
import com.shaw.tinynews.app.Constant;
import com.shaw.tinynews.module.main.MainActivity;
import com.shaw.tinynews.service.SplashService;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created on 2019/3/5.
 *
 * @author XCZ
 */
public class SplashActivity extends AppCompatActivity {
	private static final String TAG = "SplashActivity";
	private Disposable mDisposable = null;
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

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String today = calendar.get(Calendar.YEAR) + "" + calendar.get(Calendar.MONTH) + calendar.get(Calendar.DAY_OF_MONTH);
		String splashFileDate = SPUtil.getCustomAppProfile(Constant.SPLASH_FILE_DATE);
		if (!TextUtils.equals(today, splashFileDate)) {
			startService(new Intent(this, SplashService.class));
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mDisposable = Observable.timer(2, TimeUnit.SECONDS)
				.subscribe(new Consumer<Long>() {
					@Override
					public void accept(Long aLong) throws Exception {
						Intent intent = new Intent(SplashActivity.this, MainActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);
						SplashActivity.this.finish();
					}
				});
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mDisposable != null) {
			mDisposable.dispose();
		}
	}
}
