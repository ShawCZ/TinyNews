package com.shaw.tinynews.module.detail;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.orhanobut.logger.Logger;
import com.shaw.core.app.Core;
import com.shaw.core.mvp.PresenterDelegate;
import com.shaw.tinynews.R;
import com.shaw.tinynews.app.Constant;
import com.shaw.tinynews.model.detail.Detail;
import com.shaw.tinynews.model.main.Story;
import com.shaw.tinynews.module.images.ImagesDialog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * Created on 2019/3/4.
 *
 * @author XCZ
 */
public class DetailFragment extends PresenterDelegate<DetailContract.Presenter> implements DetailContract.View {
	@BindView(R.id.web_view)
	WebView webContent = null;

	private Story story = null;

	@Override
	public Object setLayout() {
		return R.layout.frag_detail;
	}

	@Override
	public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
		initWebView();

		Bundle bundle = getArguments();
		if (bundle != null) {
			story = bundle.getParcelable(Constant.STORY);
			if (story != null) {
				Log.d(TAG, "onBindView: story.getId() = " + story.getId());
				mPresenter.detail(getContentActivity(), story.getId());
			}
		}
	}

	@SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
	private void initWebView() {
		WebSettings webSettings = webContent.getSettings();
		webSettings.setAllowFileAccess(true);
		webSettings.setAppCacheEnabled(true);
		webSettings.setAppCachePath(getContentActivity().getDir("cache", 0).getPath());
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setDomStorageEnabled(true);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setDisplayZoomControls(false);
		webSettings.setLoadsImagesAutomatically(true);
		// 调用js
		webContent.addJavascriptInterface(this, "TinyNews");
	}

	@Override
	protected DetailContract.Presenter initPresenter() {
		return new DetailPresenter(this);
	}

	@Override
	public void loadImage(Detail detail) {
		DetailActivity activity = (DetailActivity) getContentActivity();
		activity.loadTitle(detail.getImage(), detail.getTitle());
	}

	@Override
	public void loadDetail(Detail detail) {
		loadHtml(detail);
	}

	private List<String> imgUrlList = null;

	/**
	 * 替换html中的<img class="content-image">标签的属性
	 *
	 * @param html
	 * @return
	 */
	protected String replaceImgTagFromHTML(String html, boolean autoLoad, boolean nightMode) {
		imgUrlList = new ArrayList<>();
		Document doc = Jsoup.parse(html);
		Elements es = doc.getElementsByTag("img");
		for (Element e : es) {
			if (!"avatar".equals(e.attr("class"))) {
				String imgUrl = e.attr("src");
				imgUrlList.add(imgUrl);
				String src = String.format("file:///android_asset/default_pic_content_image_%s_%s.png",
						autoLoad ? "loading" : "download",
						nightMode ? "dark" : "light");
				e.attr("src", src);
				e.attr("zhimg-src", imgUrl);
				e.attr("onclick", "onImageClick(this)");
			}
		}
		return doc.html();
	}

	protected void loadHtml(Detail detailContent) {
		StringBuilder htmlSb = new StringBuilder("<!doctype html>\n<html><head>\n<meta charset=\"utf-8\">\n" +
				"\t<meta name=\"viewport\" content=\"width=device-width,user-scalable=no\">");

		String content = detailContent.getBody();
		String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/news_qa.min.css\" type=\"text/css\">\n";
		String img_replace = "<script src=\"file:///android_asset/img_replace.js\"></script>\n";
		String video = "<script src=\"file:///android_asset/video.js\"></script>\n";
		String zepto = "<script src=\"file:///android_asset/zepto.min.js\"></script>\n";
		String autoLoadImage = "onload=\"onLoaded()\"";

//		boolean autoLoad = NetUtils.isWifi(getContentActivity()) || !(boolean) SPUtils.getFromDefaultPref(this, Constant.KEY_NO_LOAD_IMAGE, false);
//		boolean nightMode = (boolean) SPUtils.get(this, Constant.KEY_NIGHT, false);
//		boolean largeFont = (boolean) SPUtils.getFromDefaultPref(this, Constant.KEY_BIG_FONT, false);

		boolean autoLoad = true;
		boolean nightMode = false;
		boolean largeFont = false;

		htmlSb.append(css)
				.append(zepto)
				.append(img_replace)
				.append(video)
				.append("</head><body className=\"\"")
				.append(autoLoad ? autoLoadImage : "")
				.append(" >")
				.append(content);
		if (nightMode) {
			String night = "<script src=\"file:///android_asset/night.js\"></script>\n";
			htmlSb.append(night);
		}
		if (largeFont) {
			String bigFont = "<script src=\"file:///android_asset/large-font.js\"></script>\n";
			htmlSb.append(bigFont);
		}
		htmlSb.append("</body></html>");
		String html = htmlSb.toString();

		html = html.replace("<div class=\"img-place-holder\">", "");
//		Log.e("html1", html);
		html = replaceImgTagFromHTML(html, autoLoad, nightMode);
//		Log.e("html2", html);
		Logger.e(html);
		webContent.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
	}

	// ======================= js ========================
	@JavascriptInterface
	public void clickToLoadImage(final String imgPath) {
		if (TextUtils.isEmpty(imgPath))
			return;
		webContent.post(new Runnable() {
			@Override
			public void run() {
				Glide.with(getContentActivity())
						.load(imgPath)
						.downloadOnly(new SimpleTarget<File>() {
							@Override
							public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
								String str = "file://" + resource.getAbsolutePath();//加载完成的图片地址
								try {
									String[] arrayOfString = new String[2];
									arrayOfString[0] = URLEncoder.encode(imgPath, "UTF-8");//旧url
									arrayOfString[1] = str;
									onImageLoadingComplete("onImageLoadingComplete", arrayOfString);
								} catch (Exception e) {
								}
							}
						});
			}
		});
	}

	@JavascriptInterface
	public void loadImage(final String imgPath) {
		if (TextUtils.isEmpty(imgPath)) {
			return;
		}
		webContent.post(new Runnable() {
			@Override
			public void run() {
				Glide.with(getContentActivity()).load(imgPath)
						.downloadOnly(new SimpleTarget<File>() {
							@Override
							public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
								String str = "file://" + resource.getAbsolutePath();//加载完成的图片地址
								try {
									String[] arrayOfString = new String[2];
									arrayOfString[0] = URLEncoder.encode(imgPath, "UTF-8");//旧url
									arrayOfString[1] = str;
									onImageLoadingComplete("onImageLoadingComplete", arrayOfString);
								} catch (Exception e) {
								}
							}
						});
			}
		});
	}

	@JavascriptInterface
	public void openImage(String imgPath) {
		Log.d(TAG, "openImage: ");
//		ActivityUtils.toImageViewActivity(this, imgPath, imgUrlList);
		Core.getHandler().post(new Runnable() {
			@Override
			public void run() {
				new ImagesDialog(getContentActivity(), imgUrlList).show();
			}
		});
	}

	public final void onImageLoadingComplete(String funName, String[] paramArray) {
		String str = "'" + TextUtils.join("','", paramArray) + "'";
		webContent.loadUrl("javascript:" + funName + "(" + str + ");");
	}
}
