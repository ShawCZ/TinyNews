package com.shaw.tinynews.module.main;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaw.core.mvp.PresenterDelegate;
import com.shaw.core.util.CommonUtil;
import com.shaw.tinynews.R;
import com.shaw.tinynews.R2;
import com.shaw.tinynews.model.main.Latest;
import com.shaw.tinynews.model.main.TopStory;
import com.shaw.tinynews.view.banner.BannerCreator;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

/**
 * Created on 2019/3/4.
 *
 * @author XCZ
 */
public class MainFragment extends PresenterDelegate<MainContract.Presenter> implements MainContract.View, BaseQuickAdapter.OnItemClickListener {
	@BindView(R2.id.rv_index)
	RecyclerView mRecyclerView = null;
	@BindView(R2.id.srl_index)
	SwipeRefreshLayout mRefreshLayout = null;

	private MainAdapter mAdapter = null;

	@Override
	public Object setLayout() {
		return R.layout.frag_main;
	}

	@Override
	public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
		mPresenter.latest(getContentActivity());
	}

	private void initRefreshLayout() {
		mRefreshLayout.setColorSchemeResources(
				android.R.color.holo_blue_bright,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light
		);
		mRefreshLayout.setProgressViewOffset(true, 120, 300);
	}

	private void initRecyclerView() {
		LinearLayoutManager manager = new LinearLayoutManager(getContentActivity());
		mAdapter = new MainAdapter(null);
		mRecyclerView.setLayoutManager(manager);
		mRecyclerView.setAdapter(mAdapter);
		mAdapter.setOnItemClickListener(this);
	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		super.onLazyInitView(savedInstanceState);
		initRefreshLayout();
		initRecyclerView();
	}

	@Override
	public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

	}

	@Override
	protected MainContract.Presenter initPresenter() {
		return new MainPresenter(this);
	}

	@Override
	public void loadLatest(Latest latest) {
		mAdapter.addData(latest.getStories());
		mAdapter.addHeaderView(loadBanner(latest.getTop_stories()));
		mAdapter.notifyDataSetChanged();
	}

	private View loadBanner(List<TopStory> topStories) {
		ConvenientBanner<String> banner = new ConvenientBanner<>(getContentActivity());
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtil.dip2px(getContentActivity(), 240));
		banner.setLayoutParams(params);

		List<String> imageUrls = new ArrayList<>();
		List<String> imageTitles = new ArrayList<>();
		topStories.iterator();
		for (TopStory topStory : topStories) {
			imageUrls.add(topStory.getImage());
			imageTitles.add(topStory.getTitle());
		}
		BannerCreator.setDefault(banner, imageUrls, imageTitles, new OnItemClickListener() {
			@Override
			public void onItemClick(int position) {
				topStories.get(position);
			}
		});
		return banner;
	}
}
