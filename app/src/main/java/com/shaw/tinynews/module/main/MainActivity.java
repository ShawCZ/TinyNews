package com.shaw.tinynews.module.main;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableBadgeDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.shaw.core.base.activitys.BaseMaterialActivity;
import com.shaw.core.base.delegates.BaseMaterialDelegate;
import com.shaw.tinynews.R;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

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
				.withAccountHeader(new AccountHeaderBuilder()
						.addProfiles(new ProfileDrawerItem()
								.withName("shaw")
								.withEmail("shawcz@outlook.com")
								.withIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher))
								.withIdentifier(100))
						.withActivity(this)
						.withTranslucentStatusBar(true)
						.withHeaderBackground(R.color.colorPrimary)
						.build())
				.withHeaderDivider(true)
				.addDrawerItems(
						new SectionDrawerItem().withName("Source"),
						new SecondaryDrawerItem().withName("知乎日报"),
						new DividerDrawerItem(),
						new SecondaryDrawerItem().withName("About"),
						new ExpandableBadgeDrawerItem()
								.withName("开源库")
								.withIdentifier(2)
								.withBadge("10")
								.withSubItems(
										new SecondaryDrawerItem().withName("RxJava2").withIdentifier(1),
										new SecondaryDrawerItem().withName("Retrofit").withIdentifier(1),
										new SecondaryDrawerItem().withName("MaterialDrawer").withIdentifier(1),
										new SecondaryDrawerItem().withName("PhotoView").withIdentifier(1),
										new SecondaryDrawerItem().withName("JSoup").withIdentifier(1),
										new SecondaryDrawerItem().withName("ConvenientBanner").withIdentifier(1),
										new SecondaryDrawerItem().withName("ButterKnife").withIdentifier(1),
										new SecondaryDrawerItem().withName("Glide").withIdentifier(1),
										new SecondaryDrawerItem().withName("BaseRecyclerViewAdapterHelper").withIdentifier(1),
										new SecondaryDrawerItem().withName("AutoSize").withIdentifier(1)
								)
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
