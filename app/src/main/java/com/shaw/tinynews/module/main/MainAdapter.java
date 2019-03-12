package com.shaw.tinynews.module.main;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.shaw.core.util.glide.GlideApp;
import com.shaw.tinynews.R;
import com.shaw.tinynews.model.main.DateTitle;
import com.shaw.tinynews.model.main.Story;

import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created on 2019/3/11.
 *
 * @author XCZ
 */
public class MainAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
	private static final RequestOptions OPTIONS = new RequestOptions()
			.centerCrop()
			.diskCacheStrategy(DiskCacheStrategy.ALL)
			.dontAnimate();

	public static final int TYPE_DATE = 0;
	public static final int TYPE_STORY = 1;

	public MainAdapter(List data) {
		super(data);
		addItemType(TYPE_DATE, R.layout.item_date_title);
		addItemType(TYPE_STORY, R.layout.item_story);
	}

	@Override
	protected void convert(BaseViewHolder helper, MultiItemEntity item) {
		if (item instanceof Story) {
			Story story = (Story) item;
			helper.setText(R.id.tv_title, story.getTitle());
			AppCompatImageView imageView = helper.getView(R.id.img);
			GlideApp.with(imageView)
					.load(story.getImages().get(0))
					.apply(OPTIONS)
					.into(imageView);
		} else if (item instanceof DateTitle) {
			DateTitle dateTitle = (DateTitle) item;
			helper.setText(R.id.tv_date_title, dateTitle.getDate());
		}
	}
}
