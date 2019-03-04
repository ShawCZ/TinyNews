package com.shaw.core.mvp;

import android.os.Bundle;

import com.shaw.core.base.delegates.BaseMaterialDelegate;
import com.shaw.core.util.LoaderUtil;

import androidx.annotation.Nullable;


/**
 * @author xcz
 * @version 1.0.0
 */
public abstract class PresenterDelegate<Presenter extends BaseContract.Presenter>
		extends BaseMaterialDelegate implements BaseContract.View<Presenter> {
	protected Presenter mPresenter;

	/**
	 * 初始化Presenter
	 *
	 * @return Presenter
	 */
	protected abstract Presenter initPresenter();


	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化Presenter
		initPresenter();
	}

	@Override
	public void onDestroy() {
		// 界面关闭时进行销毁的操作
		if (mPresenter != null) {
			mPresenter.destroy();
		}
		super.onDestroy();
	}

	@Override
	public void showLoading(String message) {
		LoaderUtil.showLoading(getContentActivity(), message);
	}

	@Override
	public void stopLoading() {
		LoaderUtil.stopLoading();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		// View中赋值Presenter
		mPresenter = presenter;
	}
}
