package com.hexaware.oops;

import java.util.Scanner;
import com.hexaware.oops.entities.Employee;
import com.hexaware.oops.entities.EmployeeOperations;
import com.hexaware.oops.entities.SalaryDetails;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Employee Name: ");
        String name = scanner.next();

        System.out.println("Enter Salary: ");
        double salary = scanner.nextDouble();

        EmployeeOperations emp = new Employee(name, salary); // Interface reference

        System.out.println("Do you want to apply a bonus? (yes/no): ");
        String bonusChoice = scanner.next();
        if (bonusChoice.equalsIgnoreCase("yes")) {
            emp.applyBonus();
        }

        SalaryDetails sD = new SalaryDetails(10.0); // Example tax rate
        double taxAmount = sD.calculateTax(((Employee) emp).getSalary());
        System.out.println("Tax Deducted: " + taxAmount);
        System.out.println("Net Salary after Tax: " + (((Employee) emp).getSalary() - taxAmount));

        System.out.println("Final Employee Details:");
        emp.displayEmployee();
    }
}