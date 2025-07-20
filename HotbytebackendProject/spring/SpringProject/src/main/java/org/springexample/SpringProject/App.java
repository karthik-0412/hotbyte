package org.springexample.SpringProject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		AbstractApplicationContext c = new ClassPathXmlApplicationContext("beans.xml");

		Employee emp = (Employee) c.getBean("e1");
		System.out.println(emp.toString());
		c.registerShutdownHook();

		AbstractApplicationContext con = new AnnotationConfigApplicationContext(AppConfig.class);

		Employee e = con.getBean("employee1", Employee.class);
		System.out.println(e.toString());
		con.registerShutdownHook();
	}
}
