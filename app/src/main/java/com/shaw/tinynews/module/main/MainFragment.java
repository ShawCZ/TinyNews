package com.shaw.tinynews.module.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.shaw.core.mvp.PresenterDelegate;
import com.shaw.tinynews.R;
import com.shaw.tinynews.R2;
import com.shaw.tinynews.model.main.DateTitle;
import com.shaw.tinynews.model.main.Latest;
import com.shaw.tinynews.model.main.Story;
import com.shaw.tinynews.model.main.TopStory;
import com.shaw.tinynews.module.detail.DetailActivity;
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
public class MainFragment extends PresenterDelegate<MainContract.Presenter> implements MainContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
	@BindView(R2.id.rv_index)
	RecyclerView mRecyclerView = null;
	@BindView(R2.id.srl_index)
	SwipeRefreshLayout mRefreshLayout = null;

	private ConvenientBanner<String> mBanner = null;
	private MainAdapter mAdapter = null;
	private String mCurrentDate = null;

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
		mRefreshLayout.setOnRefreshListener(this);
	}

	private void initRecyclerView() {
		LinearLayoutManager manager = new LinearLayoutManager(getContentActivity());
		mAdapter = new MainAdapter(null);
		mRecyclerView.setLayoutManager(manager);
		mRecyclerView.setAdapter(mAdapter);
		mAdapter.bindToRecyclerView(mRecyclerView);
		mAdapter.setEnableLoadMore(true);
		mAdapter.disableLoadMoreIfNotFullPage();
		mAdapter.openLoadAnimation();
		mAdapter.setOnItemClickListener(this);
		mAdapter.setOnLoadMoreListener(this, mRecyclerView);
	}

	@Override
	public void onLazyInitView(@Nullable Bundle savedInstanceState) {
		super.onLazyInitView(savedInstanceState);
		initRefreshLayout();
		initRecyclerView();
	}

	@Override
	protected MainContract.Presenter initPresenter() {
		return new MainPresenter(this);
	}

	@Override
	public void loadLatest(Latest latest) {
		mCurrentDate = latest.getDate();
		List<MultiItemEntity> newData = new ArrayList<>();
		newData.add(new DateTitle("今日新闻"));
		newData.addAll(latest.getStories());
		mAdapter.setNewData(newData);
		//只需要加载一次头部banner
		if (mBanner == null) {
			mBanner = BannerCreator.createBanner(getContentActivity());
			mAdapter.addHeaderView(loadBanner(mBanner, latest.getTop_stories()));
		} else {
			//更新banner数据
			loadBanner(mBanner, latest.getTop_stories());
		}
		mAdapter.notifyDataSetChanged();
		mRefreshLayout.setEnabled(true);
	}

	@Override
	public void loadBefore(Latest latest) {
		Log.d(TAG, "loadBefore: ");
		mCurrentDate = latest.getDate();
		List<MultiItemEntity> newData = new ArrayList<>();
		newData.add(new DateTitle(mCurrentDate));
		newData.addAll(latest.getStories());
		mAdapter.addData(newData);
		mAdapter.loadMoreComplete();
	}

	private View loadBanner(ConvenientBanner<String> mBanner, List<TopStory> topStories) {
		List<String> imageUrls = new ArrayList<>();
		List<String> imageTitles = new ArrayList<>();
		topStories.iterator();
		for (TopStory topStory : topStories) {
			imageUrls.add(topStory.getImage());
			imageTitles.add(topStory.getTitle());
		}

		BannerCreator.setDefault(mBanner, imageUrls, imageTitles, new OnItemClickListener() {
			@Override
			public void onItemClick(int position) {
				TopStory topStory = topStories.get(position);
				List<String> images = new ArrayList<>();
				images.add(topStory.getImage());
				Story story = new Story(topStory.getId(), topStory.getTitle(), topStory.getType(), topStory.getGa_prefix(), images);
				DetailActivity.start(getContentActivity(), story);
			}
		});
		return mBanner;
	}

	@Override
	public void onLoadMoreRequested() {
		mPresenter.before(getContentActivity(), mCurrentDate);
	}

	@Override
	public void onRefresh() {
		mRefreshLayout.setEnabled(false);
		mPresenter.latest(getContentActivity());
	}

	@Override
	public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
		if (adapter.getItem(position) instanceof Story) {
			Story story = (Story) adapter.getItem(position);
			if (story != null) {
				DetailActivity.start(getContentActivity(), story);
			}
		}
	}
}
