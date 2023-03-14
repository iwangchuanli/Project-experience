package com.day02;
/**
 * 二、定义一个学生类Student，包含三个属性姓名、年龄、性别，创建三个学生对象存入ArrayList集合中。
	A：使用迭代器遍历集合。
	B：求出年龄最大的学生，然后将该对象的姓名变为：小猪佩奇。
 * @author Administrator
 *
 */
public class Student {

	private String name;
	private int age;
	private String sex;
	public Student(String name, int age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	public Student(){
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void show(){
		System.out.println("姓名："+name+"，年龄："+age+"，性别："+sex);
	}
}
