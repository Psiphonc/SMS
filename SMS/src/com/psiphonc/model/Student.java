package com.psiphonc.model;

public class Student {
	private int id;
	private String name;
	private int classId;
	private String password;
	private String gender;
	public Student() {
		super();
	}
	public Student(int id, String name, int classId, String password,
			String gender) {
		super();
		this.id = id;
		this.name = name;
		this.classId = classId;
		this.password = password;
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
