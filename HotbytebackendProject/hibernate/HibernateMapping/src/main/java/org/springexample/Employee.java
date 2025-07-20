package org.springexample;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {
    @Id
    int empid;
    String name;
    double salary;

//    @OneToOne(targetEntity = Department.class)
//    Department dept;
    Employee(){}

    public Employee(int empid,  String name, double salary) {
        this.empid = empid;
        this.salary = salary;
        this.name = name;
//        this.dept = dept;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

//    public Department getDept() {
//        return dept;
//    }
//
//    public void setDept(Department dept) {
//        this.dept = dept;
//    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emplyee{" +
                "empid=" + empid +
                ", name='" + name + '\'' +
                ", salary=" + salary +
//                ", dept=" + dept +
                '}';
    }
}
