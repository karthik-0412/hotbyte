package com.springexample.SpringExample;



public class Student {
	
	int rollno;
	String name;
	double fee;
	
	Student()
	{
		
	}
 
	public Student(int rollno, String name, double fee) {
		super();
		this.rollno = rollno;
		this.name = name;
		this.fee = fee;
	}
 
	public int getRollno() {
		return rollno;
	}
 
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public double getFee() {
		return fee;
	}
 
	public void setFee(double fee) {
		this.fee = fee;
	}
 
	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", name=" + name + ", fee=" + fee + "]";
	}
	
	
	
	
 
}
 
 