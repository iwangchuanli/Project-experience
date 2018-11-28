package com.company.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片类的Bean对象
 */
public class PictureBean {
	private String title;
	private List<String> pictureList;

	public PictureBean(String title,String path){
		this.title = title;
		this.pictureList = new ArrayList<>();
		pictureList.add(path);
	}

	public PictureBean() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<String> pictureList) {
		this.pictureList = pictureList;
	}

	@Override
	public String toString() {
		return "PictureBean{" +
				"title='" + title + '\'' +
				", pictureList=" + pictureList +
				'}';
	}
}
