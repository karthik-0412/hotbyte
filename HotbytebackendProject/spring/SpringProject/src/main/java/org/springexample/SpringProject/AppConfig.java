package org.springexample.SpringProject;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AppConfig {

	@Bean(name = "project1")
    public Project project1() {
		Project project = new Project();
		project.setpId(202);
		project.setDuration("1 year");
		project.setCost(30000.00);
		return project;
	}

	@Bean(name = "employee1")
    public Employee employee1() {
		Employee emp = new Employee();
		emp.setEmpId(2002);
		emp.setName("Prayaraj");
		emp.setSalary(85000.00);

		Map<String, String> addressMap = new HashMap<>();
		addressMap.put("home", "789 New Mahabalipuram, chennai");
		addressMap.put("office", "321 Corporate office, roundana");
		emp.setAddress(addressMap);

		emp.setProject(project1());
		return emp;
	}
}
