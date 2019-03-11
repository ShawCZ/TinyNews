package com.shaw.tinynews.view.banner;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.shaw.tinynews.R;

import java.util.List;

/**
 * Created by
 */

public class BannerCreator {
	public static void setDefault(ConvenientBanner<String> convenientBanner,
								  List<String> bannerImages,
								  List<String> bannerTitles,
								  OnItemClickListener clickListener) {

		convenientBanner
				.setPages(new HolderCreator(bannerTitles), bannerImages)
				.setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
				.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
				.setOnItemClickListener(clickListener)
				.setPageTransformer(new DefaultTransformer())
				.startTurning(5000)
				.setCanLoop(true);
	}
}
