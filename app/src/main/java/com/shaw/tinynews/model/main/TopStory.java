/**
 * Copyright 2019 bejson.com
 */
package com.shaw.tinynews.model.main;

public class TopStory {

	private String image;
	private int type;
	private long id;
	private String ga_prefix;
	private String title;

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
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
}