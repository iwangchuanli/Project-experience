package com.sf.minesweeper.bean;
/**Íæ¼ÒÄ£ÐÍ*/
public class Own implements Comparable<Own>{
	private int times;
	private String name;
	private String level;
	public Own(int times, String name, String level) {
		super();
		this.times = times;
		this.name = name;
		this.level = level;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int compareTo(Own arg0) {
		// TODO Auto-generated method stub
		int i = this.times - arg0.times;
		if(i==0){
			return this.name.hashCode() - arg0.name.hashCode();	
		}
		return i;
	}
	
	

}
