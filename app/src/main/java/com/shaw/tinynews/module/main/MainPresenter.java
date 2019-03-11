package com.shaw.tinynews.module.main;

import android.content.Context;
import android.util.Log;

import com.shaw.core.mvp.BasePresenter;
import com.shaw.tinynews.api.RestClient;
import com.shaw.tinynews.model.main.Latest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2019/3/11.
 *
 * @author XCZ
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
	public MainPresenter(MainContract.View view) {
		super(view);
	}

	@Override
	public void latest(Context context) {
		mDisposables.add(RestClient.getService()
				.latest()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<Latest>() {
					@Override
					public void accept(Latest latest) throws Exception {
						Log.d(TAG, "accept: latest = " + latest);
						if (latest != null) {
							getView().loadLatest(latest);
						}
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(Throwable throwable) throws Exception {
						Log.d(TAG, "accept: throwable = " + throwable.getMessage());
					}
				}));
	}
}
