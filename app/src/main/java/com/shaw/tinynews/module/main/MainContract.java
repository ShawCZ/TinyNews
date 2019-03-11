package com.shaw.tinynews.module.main;

import android.content.Context;

import com.shaw.core.mvp.BaseContract;
import com.shaw.tinynews.model.main.Latest;

/**
 * Created on 2019/3/11.
 *
 * @author XCZ
 */
public interface MainContract {
	interface View extends BaseContract.View<Presenter> {
		void loadLatest(Latest latest);
	}

	interface Presenter extends BaseContract.Presenter {
		void latest(Context context);
	}
}
