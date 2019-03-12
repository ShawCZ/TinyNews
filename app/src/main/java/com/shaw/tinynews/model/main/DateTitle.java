package com.shaw.tinynews.model.main;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.shaw.tinynews.module.main.MainAdapter;

/**
 * Created on 2019/3/12.
 *
 * @author XCZ
 */
public class DateTitle implements MultiItemEntity {
	private String date = null;

	public DateTitle(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int getItemType() {
		return MainAdapter.TYPE_DATE;
	}
}
