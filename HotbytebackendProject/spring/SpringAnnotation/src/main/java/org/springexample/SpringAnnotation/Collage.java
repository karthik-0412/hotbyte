package org.springexample.SpringAnnotation;

import org.springframework.beans.factory.annotation.Autowired;

public class Collage {
	
	int c_id;
	String c_nm;
	@Autowired
	Student s;
	
	Collage()
	{
		
	}
 
	public Collage(int c_id, String c_nm, Student s) {
		super();
		this.c_id = c_id;
		this.c_nm = c_nm;
		this.s = s;
	}
 
	
	
	
	public int getC_id() {
		return c_id;
	}
 
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
 
	public String getC_nm() {
		return c_nm;
	}
 
	public void setC_nm(String c_nm) {
		this.c_nm = c_nm;
	}
 
	public Student getS() {
		return s;
	}
 
	public void setS(Student s) {
		this.s = s;
	}
 
	@Override
	public String toString() {
		return "Collage [c_id=" + c_id + ", c_nm=" + c_nm + ", s=" + s + "]";
	}
	
	
	
 
}