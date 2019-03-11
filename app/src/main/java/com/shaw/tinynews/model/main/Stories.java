/**
 * Copyright 2019 bejson.com
 */
package com.shaw.tinynews.model.main;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class Stories implements MultiItemEntity {

	private List<String> images;
	private int type;
	private long id;
	private String ga_prefix;
	private String title;

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<String> getImages() {
		return images;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setGa_prefix(String ga_prefix) {
		this.ga_prefix = ga_prefix;
	}

	public String getGa_prefix() {
		return ga_prefix;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int getItemType() {
		return 0;
	}
}