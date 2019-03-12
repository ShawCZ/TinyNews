package com.shaw.tinynews.module.images;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.github.chrisbanes.photoview.PhotoView;
import com.shaw.tinynews.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Created on 2019/3/12.
 *
 * @author XCZ
 */
public class ImagesDialog extends Dialog {

	private static final RequestOptions OPTIONS = new RequestOptions()
			.centerCrop()
			.error(R.mipmap.ic_launcher)
			.placeholder(R.mipmap.ic_launcher)
			.diskCacheStrategy(DiskCacheStrategy.ALL)
			.dontAnimate();

	private View mView;
	private Context mContext;
	private ImagesViewPager mViewPager;
	private TextView mIndexText;
	private List<String> mImgUrls;
	private List<String> mTitles;
	private List<View> mViews;
	private ImagesAdapter mAdapter;

	public ImagesDialog(@NonNull Context context, List<String> imgUrls) {
		super(context, R.style.dialog);
		this.mContext = context;
		this.mImgUrls = imgUrls;
		initView();
		initData();
	}

	private void initView() {
		mView = View.inflate(mContext, R.layout.dialog_images_brower, null);
		mViewPager = mView.findViewById(R.id.vp_images);
		mIndexText = mView.findViewById(R.id.tv_image_index);
		mTitles = new ArrayList<>();
		mViews = new ArrayList<>();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(mView);
		Window window = getWindow();
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = 0;
		DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
		wl.height = metrics.heightPixels;
		wl.width = metrics.widthPixels;
		wl.gravity = Gravity.CENTER;
		window.setAttributes(wl);
	}

	private void initData() {
		for (int i = 0; i < mImgUrls.size(); i++) {
			final PhotoView photoView = new PhotoView(mContext);
			ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			photoView.setLayoutParams(layoutParams);
			photoView.setOnPhotoTapListener((view, x, y) -> dismiss());

			Glide.with(mContext)
					.load(mImgUrls.get(i))
					.apply(OPTIONS)
					.into(new ImageViewTarget<Drawable>(photoView) {
						@Override
						protected void setResource(@Nullable Drawable resource) {
							photoView.setImageDrawable(resource);
						}
					});
			mViews.add(photoView);
			mTitles.add(i + "");
		}

		mAdapter = new ImagesAdapter(mViews, mTitles);
		mViewPager.setAdapter(mAdapter);
		mIndexText.setText(1 + "/" + mImgUrls.size());
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				mIndexText.setText(position + 1 + "/" + mImgUrls.size());
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}
}
