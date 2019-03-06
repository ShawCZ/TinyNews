package com.shaw.tinynews.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.request.FutureTarget;
import com.shaw.core.app.Core;
import com.shaw.core.net.scalars.rx.RxRestClient;
import com.shaw.core.util.glide.GlideApp;
import com.shaw.core.util.storage.SPUtil;
import com.shaw.tinynews.app.Constant;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

/**
 * Created on 2019/3/5.
 *
 * @author XCZ
 */
public class SplashService extends IntentService {
	private static final String TAG = "SplashService";
	private static final String SPLASH_URL = "https://api.unsplash.com/photos/random?client_id=cea6887652f119ed84c893bd22885fd686fa545adc88c477abb30ceab2450a88&orientation=portrait&featured=true";
	private static final String CORPRIGHT = "&txtpad=50&txtfont=cursive&txtclr=FFFFFF&txtsize=36&txtalign=center&txt=Unsplash%20:%20";

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
						JSONObject userObject = jsonObject.getJSONObject("user");
						String urlPic = urlObject.getString("regular");
						String username = userObject.getString("username").replaceAll(" ",  "%20");
						FutureTarget<File> target = GlideApp.with(Core.getApplicationContext()).asFile().load(urlPic + CORPRIGHT+username).submit();
						File file = target.get();
						if (file != null) {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(new Date());
							String today = calendar.get(Calendar.YEAR) + "" + calendar.get(Calendar.MONTH) + calendar.get(Calendar.DAY_OF_MONTH);
							File localFile = savePic(file,"splash"+today+".jpg");
							if (localFile != null) {
								SPUtil.addCustomAppProfile(Constant.SPLASH_FILE_DATE, today);
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

	private File savePic(File resource,String fileName) throws IOException {
		File pictureFile = new File(getExternalCacheDir(), fileName);
		boolean fileExist = false;
		Sink mSink = null;
		Source mSource = null;
		BufferedSink mBufferedSink = null;
		BufferedSource mBufferedSource = null;
		if (!pictureFile.exists()) {
			//没有缓存文件，则创建
			fileExist = pictureFile.createNewFile();
		} else {
			fileExist = true;
		}

		if (fileExist) {
			mSink = Okio.sink(pictureFile);
			mSource = Okio.source(resource);
			mBufferedSource = Okio.buffer(mSource);
			mBufferedSink = Okio.buffer(mSink);

			byte[] bytes = mBufferedSource.readByteArray();
			mBufferedSink.write(bytes);
			mBufferedSink.flush();

			mBufferedSource.close();
			mBufferedSink.close();
		}

		return pictureFile;
	}
}
