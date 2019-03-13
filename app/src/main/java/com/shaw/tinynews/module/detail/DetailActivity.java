package com.shaw.tinynews.module.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shaw.core.base.activitys.BaseMaterialActivity;
import com.shaw.core.base.delegates.BaseMaterialDelegate;
import com.shaw.core.util.glide.GlideApp;
import com.shaw.tinynews.R;
import com.shaw.tinynews.app.Constant;
import com.shaw.tinynews.model.main.Story;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;


public class DetailActivity extends BaseMaterialActivity {
	private static final String TAG = "DetailActivity";
	private static final RequestOptions OPTIONS = new RequestOptions()
			.centerCrop()
			.diskCacheStrategy(DiskCacheStrategy.ALL)
			.dontAnimate();

	private Story story = null;
	private AppCompatImageView imageView = null;
	private AppCompatTextView tvTitle = null;

	public static void start(Context context, Story story) {
		Intent intent = new Intent(context, DetailActivity.class);
		intent.putExtra(Constant.STORY, story);
		context.startActivity(intent);
	}

	@Override
	public BaseMaterialDelegate setRootDelegate() {
		story = getIntent().getParcelableExtra(Constant.STORY);
		Log.d(TAG, "setRootDelegate: " + story.getId());

		DetailFragment detailFragment = new DetailFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable(Constant.STORY, story);
		detailFragment.setArguments(bundle);
		return detailFragment;
	}

	@Override
	public int setContainerId() {
		return R.id.container;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		imageView = findViewById(R.id.img_detail);
		tvTitle = findViewById(R.id.tv_detail);
		Toolbar toolbar = findViewById(R.id.toolbar);
		toolbar.setTitle("");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
		}
	}

	public void loadTitle(String urlImg, String title) {
		GlideApp.with(this)
				.load(urlImg)
				.apply(OPTIONS)
				.into(imageView);
		tvTitle.setText(title);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				break;
			default:
				break;
		}
		return true;
	}
}
