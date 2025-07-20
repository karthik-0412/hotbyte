package com.hexaware.oops;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

public class EmployeeSalaryJaggedArray {
    private int[][] employees; 
    private int count; 

    public EmployeeSalaryJaggedArray(int size) {
        employees = new int[size][]; 
        count = 0;
    }

    public void addEmployee(int id, String name, int salary) {
        if (count < employees.length) {
            employees[count] = new int[]{id, name.hashCode(), salary};
            System.out.println("Employee added successfully!");
            count++;
        } else {
            System.out.println("Employee list is full!");
        }
    }

    public void fetchSalaries() {
        if (count == 0) {
            System.out.println("No employees found.");
            return;
        }
        Arrays.stream(employees, 0, count)
              .forEach(emp -> System.out.println("ID: " + emp[0] + ", Name Hash: " + emp[1] + ", Salary: " + emp[2]));
    }

    public void updateSalary(int id, int newSalary) {
        Optional<int[]> employee = Arrays.stream(employees, 0, count)
                                         .filter(emp -> emp[0] == id)
                                         .findFirst();

        employee.ifPresentOrElse(emp -> {
            emp[2] = newSalary;
            System.out.println("Salary updated successfully for ID: " + id);
        }, () -> System.out.println("Employee not found!"));
    }

    public void deleteEmployee(int id) {
        int index = IntStream.range(0, count)
                             .filter(i -> employees[i][0] == id)
                             .findFirst()
                             .orElse(-1);

        if (index != -1) {
            
            IntStream.range(index, count - 1)
                     .forEach(i -> employees[i] = employees[i + 1]);

            employees[count - 1] = null;
            count--;
            System.out.println("Employee with ID: " + id + " deleted successfully.");
        } else {
            System.out.println("Employee not found!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeSalaryJaggedArray manager = new EmployeeSalaryJaggedArray(10); // Max 10 employees

        while (true) {
            System.out.println("\nEmployee Salary Processing System (Jagged Array with Streams)");
            System.out.println("1. Add Employee");
            System.out.println("2. Fetch Salaries");
            System.out.println("3. Update Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Employee Salary: ");
                    int salary = scanner.nextInt();
                    manager.addEmployee(id, name, salary);
                    break;
                case 2:
                    System.out.println("\nFetching all salaries:");
                    manager.fetchSalaries();
                    break;
                case 3:
                    System.out.print("Enter Employee ID to update salary: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new Salary: ");
                    int newSalary = scanner.nextInt();
                    manager.updateSalary(updateId, newSalary);
                    break;
                case 4:
                    System.out.print("Enter Employee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    manager.deleteEmployee(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
