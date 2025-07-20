package org.springexample.SpringProject;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Employee implements InitializingBean, DisposableBean {
	private int empId;
	private String name;
	private double salary;
	private Map<String, String> address;
	private Project project;

	public Employee() {
	}

	public Employee(int empId, String name, double salary, Map<String, String> address, Project project) {
		this.empId = empId;
		this.name = name;
		this.salary = salary;
		this.address = address;
		this.project = project;
	}


	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Map<String, String> getAddress() {
		return address;
	}

	public void setAddress(Map<String, String> address) {
		this.address = address;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@PostConstruct
	public void init() {
		System.out.println("Starting.... using @PostConstruct");
	}

	@PreDestroy
	public void cleanup() {
		System.out.println("Ending.... using @PreDestroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Starting ..... afterPropertiesSet()");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Ending..... destroy() ");
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", salary=" + salary + ", address=" + address
				+ ", project=" + project + "]";
	}
}
