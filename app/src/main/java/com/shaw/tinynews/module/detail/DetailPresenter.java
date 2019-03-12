package com.shaw.tinynews.module.detail;

import android.content.Context;
import android.util.Log;

import com.shaw.core.mvp.BasePresenter;
import com.shaw.tinynews.api.RestClient;
import com.shaw.tinynews.model.detail.Detail;
import com.shaw.tinynews.model.main.Latest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2019/3/11.
 *
 * @author XCZ
 */
public class DetailPresenter extends BasePresenter<DetailContract.View> implements DetailContract.Presenter {
	public DetailPresenter(DetailContract.View view) {
		super(view);
	}

	@Override
	public void detail(Context context, long storyId) {
		mDisposables.add(RestClient.getService()
				.detail(storyId)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Consumer<Detail>() {
					@Override
					public void accept(Detail detail) throws Exception {
						Log.d(TAG, "accept: detail = " + detail);
						if (detail != null) {
							getView().loadImage(detail);
							getView().loadDetail(detail);
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
