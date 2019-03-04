package com.shaw.core.mvp;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created on 2018/12/29.
 *
 * @author XCZ
 */
public class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter {
	public final String TAG = this.getClass().getSimpleName();

	private V mView;
	private final CompositeDisposable mDisposables;

	public BasePresenter(V view) {
		setView(view);
		mDisposables = new CompositeDisposable();
	}

	@Override
	public void start() {

	}

	@Override
	public void destroy() {
		V view = mView;
		mView = null;
		if (view != null) {
			// 把Presenter设置为NULL
			view.setPresenter(null);
		}
		if (mDisposables != null) {
			mDisposables.clear();
		}
	}

	/**
	 * 设置一个View，子类可以复写
	 */
	@SuppressWarnings("unchecked")
	protected void setView(V view) {
		this.mView = view;
		this.mView.setPresenter(this);
	}


	public final V getView() {
		return mView;
	}
}
