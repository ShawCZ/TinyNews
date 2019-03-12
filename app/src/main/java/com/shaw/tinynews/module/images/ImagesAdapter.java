package com.shaw.tinynews.module.images;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Created on 2019/3/12.
 *
 * @author XCZ
 */
public class ImagesAdapter extends PagerAdapter {

	private List<View> views;
	private List<String> titles;

	public ImagesAdapter(List<View> views, List<String> titles) {
		this.views = views;
		this.titles = titles;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public int getCount() {
		return views.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView(views.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		((ViewPager) container).addView(views.get(position));
		return views.get(position);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titles == null ? "" : titles.get(position);
	}

}
