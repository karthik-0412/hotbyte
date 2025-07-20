package org.springexample.SpringAPI;

import org.springframework.beans.factory.annotation.Autowired;

public class Car {
  
	
	
	String Company;
	String Name;
	int price ;
	
	@Autowired
	Type t1;
	
	
	public Car() {
		
	}
 
 
  
 
 
	public Type getT1() {
		return t1;
	}
 
 
 
 
 
	public void setT1(Type t1) {
		this.t1 = t1;
	}
 
 
 
 
 
	public Car(String company, String name, int price) {
		super();
		Company = company;
		Name = name;
		this.price = price;
	}
 
 
 
 
 
	public String getCompany() {
		return Company;
	}
 
 
 
 
 
	public void setCompany(String company) {
		Company = company;
	}
 
 
 
 
 
	public String getName() {
		return Name;
	}
 
 
 
 
 
	public void setName(String name) {
		Name = name;
	}
 
 
 
 
 
	public int getPrice() {
		return price;
	}
 
 
 
 
 
	public void setPrice(int price) {
		this.price = price;
	}
 
 
 
 
 
	@Override
	public String toString() {
		return "Car [Company=" + Company + ", Name=" + Name + ", price=" + price + "]";
	}
	
	
	
}
 
 
