/**
 * Copyright 2019 bejson.com
 */
package com.shaw.tinynews.model.main;

import java.util.List;

public class Latest {

	private String date;
	private List<Story> stories;
	private List<TopStory> top_stories;

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setTop_stories(List<TopStory> top_stories) {
		this.top_stories = top_stories;
	}

	public List<TopStory> getTop_stories() {
		return top_stories;
	}
}