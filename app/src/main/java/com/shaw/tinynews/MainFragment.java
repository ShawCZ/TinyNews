package com.shaw.tinynews;

import android.os.Bundle;
import android.view.View;

import com.shaw.core.base.delegates.BaseMaterialDelegate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created on 2019/3/4.
 *
 * @author XCZ
 */
public class MainFragment extends BaseMaterialDelegate {
	@Override
	public Object setLayout() {
		return R.layout.frag_main;
	}

	@Override
	public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

	}
}
