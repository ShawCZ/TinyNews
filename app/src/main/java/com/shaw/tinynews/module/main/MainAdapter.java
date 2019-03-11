package com.shaw.tinynews.module.main;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaw.core.util.glide.GlideApp;
import com.shaw.tinynews.R;
import com.shaw.tinynews.model.main.Stories;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created on 2019/3/11.
 *
 * @author XCZ
 */
public class MainAdapter extends BaseMultiItemQuickAdapter<Stories, BaseViewHolder> {
	private static final RequestOptions OPTIONS = new RequestOptions()
			.centerCrop()
			.diskCacheStrategy(DiskCacheStrategy.ALL)
			.dontAnimate();

	public MainAdapter(List data) {
		super(data);
		addItemType(0, R.layout.item_story);
	}

	@Override
	protected void convert(BaseViewHolder helper, Stories item) {
		helper.setText(R.id.tv_title, item.getTitle());
		AppCompatImageView imageView = helper.getView(R.id.img);
		GlideApp.with(imageView)
				.load(item.getImages().get(0))
				.apply(OPTIONS)
				.into(imageView);
	}
}
