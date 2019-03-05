package com.shaw.tinynews.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.request.FutureTarget;
import com.shaw.core.app.Core;
import com.shaw.core.net.scalars.rx.RxRestClient;
import com.shaw.core.util.file.FileUtil;
import com.shaw.core.util.glide.GlideApp;
import com.shaw.core.util.storage.SPUtil;
import com.shaw.tinynews.app.Constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import androidx.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2019/3/5.
 *
 * @author XCZ
 */
public class SplashService extends IntentService {
	private static final String TAG = "SplashService";
	private static final String SPLASH_URL = "https://api.unsplash.com/photos/random?client_id=cea6887652f119ed84c893bd22885fd686fa545adc88c477abb30ceab2450a88&orientation=portrait&featured=true";
	private static final String CORPRIGHT = "&txtpad=50&txtfont=cursive&txt=Unsplash%20:%20Jason%20Leung&txtclr=FFFFFF&txtsize=36&txtalign=center";

	public SplashService() {
		super("SplashService");
	}

	@Override
	protected void onHandleIntent(@Nullable Intent intent) {
		Log.d(TAG, "onHandleIntent: ");
		downloadSplash();
	}

	private void downloadSplash() {
		Disposable disposable = RxRestClient.builder()
				.url(SPLASH_URL)
				.build()
				.get()
				.map(new Function<String, String>() {
					@Override
					public String apply(String s) throws Exception {
						JSONObject jsonObject = JSON.parseObject(s);
						JSONObject urlObject = jsonObject.getJSONObject("urls");
						String urlPic = urlObject.getString("regular");
						FutureTarget<File> target = GlideApp.with(Core.getApplicationContext()).asFile().load(urlPic + CORPRIGHT).submit();
						File file = target.get();
						if (file != null) {
							File localFile = savePic(file);
							if (localFile != null) {
								SPUtil.addCustomAppProfile(Constant.SPLASH_FILE_URL, localFile.getAbsolutePath());
							}
						}
						return "";
					}
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<String>() {
					@Override
					public void accept(String s) throws Exception {
						Log.d(TAG, "accept: result = " + s);
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(Throwable throwable) throws Exception {
						Log.d(TAG, "accept: throwable = " + throwable.getMessage());
					}
				});

	}

	private File savePic(File resource) {
		File mPic = null;
//		//首先保存图片
//		File pictureFolder = "Android/data/"+getPackageName();
//		String fileName = "splash.jpg";
//		try {
//			mPic = FileUtil.writeToDisk(new FileInputStream(resource), pictureFolder.getName(), fileName);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		return mPic;
	}
}
