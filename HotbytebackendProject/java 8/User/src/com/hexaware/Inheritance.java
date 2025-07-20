package com.hexaware;

import java.util.Scanner;

class Employee{
	int id;
	String name;
	public void work() {
		System.out.println(name+" is working");
	}
}
class Manager extends Employee{
	String dept;
	public void manage() {
		System.out.println(name +" is working in "+ dept+" department. ");
	}
}
public class Inheritance {
	public static void main(String[] args) {
		Manager mg = new Manager();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name of the employee: ");
		mg.name = sc.next();
		System.out.println("Enter department of working: ");
		mg.dept = sc.next();
		mg.work();
		mg.manage();
	}

}
