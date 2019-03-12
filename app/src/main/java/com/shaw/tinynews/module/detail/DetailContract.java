package com.shaw.tinynews.module.detail;

import android.content.Context;

import com.shaw.core.mvp.BaseContract;
import com.shaw.tinynews.model.detail.Detail;

/**
 * Created on 2019/3/11.
 *
 * @author XCZ
 */
public interface DetailContract {
	interface View extends BaseContract.View<Presenter> {
		void loadImage(Detail detail);

		void loadDetail(Detail detail);
	}

	interface Presenter extends BaseContract.Presenter {
		void detail(Context context, long storyId);
	}
}
