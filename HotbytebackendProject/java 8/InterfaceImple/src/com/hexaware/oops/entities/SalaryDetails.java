package com.hexaware.oops.entities;

public record SalaryDetails(double taxRate) {
    public double calculateTax(double salary) {
        return (taxRate / 100) * salary;
    }
}
