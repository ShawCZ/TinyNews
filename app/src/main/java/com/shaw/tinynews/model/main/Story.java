package com.shaw.tinynews.model.main;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.shaw.tinynews.module.main.MainAdapter;

import java.util.List;

public class Story implements MultiItemEntity, Parcelable {

	private List<String> images;
	private int type;
	private long id;
	private String ga_prefix;
	private String title;

	public Story(long id, String title, int type, String ga_prefix, List<String> images) {
		this.id = id;
		this.images = images;
		this.type = type;
		this.ga_prefix = ga_prefix;
		this.title = title;
	}

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
		return MainAdapter.TYPE_STORY;
	}

	@Override
	public String toString() {
		return "Story{" +
				"images=" + images +
				", type=" + type +
				", id=" + id +
				", ga_prefix='" + ga_prefix + '\'' +
				", title='" + title + '\'' +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.id);
		dest.writeString(this.title);
		dest.writeStringList(this.images);
		dest.writeString(this.ga_prefix);
		dest.writeInt(this.type);
	}

	protected Story(Parcel in) {
		id = in.readLong();
		title = in.readString();
		images = in.createStringArrayList();
		ga_prefix = in.readString();
		type = in.readInt();
	}

	public static final Creator<Story> CREATOR = new Creator<Story>() {
		@Override
		public Story createFromParcel(Parcel in) {
			return new Story(in);
		}

		@Override
		public Story[] newArray(int size) {
			return new Story[size];
		}
	};
}