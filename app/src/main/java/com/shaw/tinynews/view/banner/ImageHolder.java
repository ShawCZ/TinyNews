package com.shaw.tinynews.view.banner;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by 傅令杰
 */

public class ImageHolder implements Holder<String> {
	private final List<String> bannerTitles;
	private final RelativeLayout.LayoutParams paramsImage;
	private final RelativeLayout.LayoutParams paramsText;


	public ImageHolder(List<String> bannerTitles) {
		this.bannerTitles = bannerTitles;
		paramsImage = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		paramsText = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		paramsText.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		paramsText.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		paramsText.leftMargin = 40;
		paramsText.bottomMargin = 80;
	}

	private RelativeLayout mView = null;
	private AppCompatImageView mImageView = null;
	private AppCompatTextView mTextView = null;

	private static final RequestOptions BANNER_OPTIONS = new RequestOptions()
			.diskCacheStrategy(DiskCacheStrategy.ALL)
			.dontAnimate()
			.centerCrop();

	@Override
	public View createView(Context context) {
		mView = new RelativeLayout(context);
		mImageView = new AppCompatImageView(context);
		mImageView.setLayoutParams(paramsImage);

		mTextView = new AppCompatTextView(context);
		mTextView.setLayoutParams(paramsText);
		mTextView.setTextSize(20);
		mTextView.setTextColor(Color.WHITE);

		mView.addView(mImageView);
		mView.addView(mTextView);
		return mView;
	}

	@Override
	public void UpdateUI(Context context, int position, String data) {
		Glide.with(context)
				.load(data)
				.apply(BANNER_OPTIONS)
				.into(mImageView);

		mTextView.setText(bannerTitles.get(position));
	}
}
