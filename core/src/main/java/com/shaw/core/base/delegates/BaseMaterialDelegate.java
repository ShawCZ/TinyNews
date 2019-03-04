package com.shaw.core.base.delegates;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaw.core.base.activitys.BaseMaterialActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by shaw on 2017/8/31.
 */

public abstract class BaseMaterialDelegate extends Fragment implements ISupportFragment {
	public final String TAG = getClass().getSimpleName();

	private final SupportFragmentDelegate DELEGATE = new SupportFragmentDelegate(this);
	protected FragmentActivity _mActivity = null;

	@SuppressWarnings("SpellCheckingInspection")
	private Unbinder mUnbinder = null;

	public abstract Object setLayout();

	public abstract void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView);

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		DELEGATE.onAttach((Activity) context);
		_mActivity = DELEGATE.getActivity();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DELEGATE.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		DELEGATE.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		DELEGATE.onSaveInstanceState(outState);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View rootview;
		if (setLayout() instanceof Integer) {
			rootview = inflater.inflate((Integer) setLayout(), container, false);
		} else if (setLayout() instanceof View) {
			rootview = (View) setLayout();
		} else {
			throw new ClassCastException("setLayout() type must be int or View");
		}

		//使用ButterKnife绑定视图
		mUnbinder = ButterKnife.bind(this, rootview);
		onBindView(savedInstanceState, rootview);

		return rootview;
	}

	public final BaseMaterialActivity getContentActivity() {
		return (BaseMaterialActivity) _mActivity;
	}

	@Override
	public void onResume() {
		super.onResume();
		DELEGATE.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		DELEGATE.onPause();
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		DELEGATE.onHiddenChanged(hidden);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (mUnbinder != null) {
			mUnbinder.unbind();
		}
	}

	@Override
	public SupportFragmentDelegate getSupportDelegate() {
		return DELEGATE;
	}

	@Override
	public ExtraTransaction extraTransaction() {
		return DELEGATE.extraTransaction();
	}

	@Override
	public void enqueueAction(Runnable runnable) {
		DELEGATE.enqueueAction(runnable);
	}

	@Override
	public void onEnterAnimationEnd(@Nullable Bundle savedInstanceState) {
		DELEGATE.onEnterAnimationEnd(savedInstanceState);
	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		DELEGATE.onLazyInitView(savedInstanceState);
	}

	@Override
	public void onSupportVisible() {
		DELEGATE.onSupportVisible();
	}

	@Override
	public void onSupportInvisible() {
		DELEGATE.onSupportInvisible();
	}

	@Override
	public boolean isSupportVisible() {
		return DELEGATE.isSupportVisible();
	}

	@Override
	public FragmentAnimator onCreateFragmentAnimator() {
		return DELEGATE.onCreateFragmentAnimator();
	}

	@Override
	public FragmentAnimator getFragmentAnimator() {
		return DELEGATE.getFragmentAnimator();
	}

	@Override
	public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
		DELEGATE.setFragmentAnimator(fragmentAnimator);
	}

	@Override
	public void setFragmentResult(int resultCode, Bundle bundle) {
		DELEGATE.setFragmentResult(resultCode, bundle);
	}

	@Override
	public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
		DELEGATE.onFragmentResult(requestCode, resultCode, data);
	}

	@Override
	public void onNewBundle(Bundle args) {
		DELEGATE.onNewBundle(args);
	}

	@Override
	public void putNewBundle(Bundle newBundle) {
		DELEGATE.putNewBundle(newBundle);
	}

	@Override
	public boolean onBackPressedSupport() {
		return DELEGATE.onBackPressedSupport();
	}

	@Override
	public void post(Runnable runnable) {
		DELEGATE.post(runnable);
	}
}
