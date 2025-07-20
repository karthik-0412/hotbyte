package org.springexample.SpingABCD;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Book {
	@Override
	public String toString() {
		return "Book [code=" + code + ", name=" + name + ", price=" + price + "]";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	int code;
	String name;
	double price;
	
	public Book() {
		
	}

	public Book(int code, String name, double price) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
	}
	void init()
	{
		
		System.out.println("Starting .....");
	}
	
	public void destroy() throws Exception {
			System.out.println("Ending  ");
			
	}

	
//	
	@PostConstruct
	void start() {
		System.out.println("Starting...");
	}
	
	@PreDestroy
	void stop() {
		System.out.println("Ending...");
	}
	
	

}
