package com.shaw.core.mvp;

/**
 * Created on 2018/12/29.
 *
 * @author XCZ
 */
public interface BaseContract {
	interface View<T extends Presenter> {
		void setPresenter(T t);

		// 公共的：显示进度条
		void showLoading(String message);

		// 公共的：关闭进度条
		void stopLoading();
	}

	interface Presenter {
		void start();

		void destroy();
	}
}
