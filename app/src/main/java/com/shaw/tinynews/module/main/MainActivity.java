package com.shaw.tinynews.module.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.shaw.core.base.activitys.BaseMaterialActivity;
import com.shaw.core.base.delegates.BaseMaterialDelegate;
import com.shaw.tinynews.R;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends BaseMaterialActivity {
	private static final String TAG = "MainActivity";

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
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		//create the drawer and remember the `Drawer` result object
		Drawer result = new DrawerBuilder()
				.withActivity(this)
				.withToolbar(toolbar)
				.addDrawerItems(
						new PrimaryDrawerItem().withName("News Source"),
						new DividerDrawerItem(),
						new SecondaryDrawerItem().withName("Zhi hu")
				)
				.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
					@Override
					public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
						// do something with the clicked item :D
						Log.d(TAG, "onItemClick: position = " + position);
						return false;
					}
				})
				.build();
	}
}
