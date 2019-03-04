package com.shaw.tinynews;

import android.os.Bundle;

import com.shaw.core.base.activitys.BaseMaterialActivity;
import com.shaw.core.base.delegates.BaseMaterialDelegate;
import com.shaw.tinynews.R;

public class MainActivity extends BaseMaterialActivity {

	@Override
	public BaseMaterialDelegate setRootDelegate() {
		return new MainFragment();
	}

	@Override
	public int setContainerId() {
		return R.id.container;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
}
