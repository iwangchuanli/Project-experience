package com.day02;
/**
 * ��������һ��ѧ����Student�����������������������䡢�Ա𣬴�������ѧ���������ArrayList�����С�
	A��ʹ�õ������������ϡ�
	B�������������ѧ����Ȼ�󽫸ö����������Ϊ��С�����档
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
		System.out.println("������"+name+"�����䣺"+age+"���Ա�"+sex);
	}
}
