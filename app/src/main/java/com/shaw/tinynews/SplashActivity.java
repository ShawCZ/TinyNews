package com.shaw.tinynews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

//		ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(imageView, "scaleX", 1.2f, 1.0f);
//		ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 1.2f, 1.0f);
//		AnimatorSet animatorSet = new AnimatorSet();
//		animatorSet.setDuration(1000);
//		animatorSet.playTogether(objectAnimatorX, objectAnimatorY);
//		animatorSet.start();

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
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
			}
		}, 2000);
	}
}
