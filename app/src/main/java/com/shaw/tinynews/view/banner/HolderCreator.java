package com.shaw.tinynews.view.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 */

public class HolderCreator implements CBViewHolderCreator<ImageHolder> {
	private final List<String> bannerTitles;

	public HolderCreator(List<String> bannerTitles) {
		this.bannerTitles = bannerTitles;
	}

	@Override
	public ImageHolder createHolder() {
		return new ImageHolder(bannerTitles);
	}
}
