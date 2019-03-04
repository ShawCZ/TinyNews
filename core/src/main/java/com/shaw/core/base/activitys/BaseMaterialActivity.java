package com.shaw.core.base.activitys;

import android.os.Bundle;

import com.shaw.core.base.delegates.BaseMaterialDelegate;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.annotations.Nullable;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by shaw on 2017/8/31.
 */

public abstract class BaseMaterialActivity extends AppCompatActivity implements ISupportActivity {

	private final SupportActivityDelegate DELEGATE = new SupportActivityDelegate(this);

	public abstract BaseMaterialDelegate setRootDelegate();

	public abstract int setContainerId();


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DELEGATE.onCreate(savedInstanceState);
		if (savedInstanceState == null && setRootDelegate() != null) {
			DELEGATE.loadRootFragment(setContainerId(), setRootDelegate());
		}
	}

	@Override
	protected void onDestroy() {
		DELEGATE.onDestroy();
		super.onDestroy();
		System.gc();
		System.runFinalization();
	}

	@Override
	public SupportActivityDelegate getSupportDelegate() {
		return DELEGATE;
	}

	@Override
	public ExtraTransaction extraTransaction() {
		return DELEGATE.extraTransaction();
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
	public FragmentAnimator onCreateFragmentAnimator() {
		return DELEGATE.onCreateFragmentAnimator();
	}

	@Override
	public void onBackPressedSupport() {
		DELEGATE.onBackPressedSupport();
	}

	@Override
	public void onBackPressed() {
		DELEGATE.onBackPressed();
	}

	@Override
	public void post(Runnable runnable) {
		DELEGATE.post(runnable);
	}
}
