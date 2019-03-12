package com.shaw.tinynews.view.banner;

import android.content.Context;
import android.view.ViewGroup;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.shaw.core.util.CommonUtil;
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

	public static ConvenientBanner<String> createBanner(Context context) {
		ConvenientBanner<String> banner = new ConvenientBanner<>(context);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtil.dip2px(context, 240));
		banner.setLayoutParams(params);
		return banner;
	}
}
