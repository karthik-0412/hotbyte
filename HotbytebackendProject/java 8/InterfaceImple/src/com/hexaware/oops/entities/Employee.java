package com.hexaware.oops.entities;

public class Employee implements EmployeeOperations {
    private String empName;
    private double salary;
    private static final double BONUS_PERCENTAGE = 5.0;

    public Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
    }

    public Employee(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Invalid salary amount");
        }
    }

    @Override
    public void applyBonus() {
        double bonus = (BONUS_PERCENTAGE / 100) * salary;
        this.salary += bonus;
        System.out.println("Bonus of " + bonus + " added! New salary: " + salary);
    }

    @Override
    public void displayEmployee() {
        System.out.println("Employee Name: " + empName + ", Salary: " + salary);
    }

    @Override
    public String toString() {
        return "Employee [Name=" + empName + ", Salary=" + salary + "]";
    }
}