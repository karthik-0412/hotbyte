package com.hexaware;

import java.util.Scanner;

public class AgeValidator {

    public static void validateAge(int age) throws AgeValidatorException {
        if (age < 18 || age > 65) {
            throw new AgeValidatorException("Age between 18 - 35: " + age);
        }
        System.out.println("Age " + age + " is valid.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your age: ");
        int age = sc.nextInt();
    	try {
            validateAge(age);  
        } catch (AgeValidatorException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        
    }
}