package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
	int actno;
	String name;
	double balance;
	String type;
	
	public Customer(){}

	public Customer(int actno, String name, double balance, String type) {
		super();
		this.actno = actno;
		this.name = name;
		this.balance = balance;
		this.type = type;
	}

	public int getActno() {
		return actno;
	}

	public void setActno(int actno) {
		this.actno = actno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Customer [actno=" + actno + ", name=" + name + ", balance=" + balance + ", type=" + type + "]";
	}
	

}
