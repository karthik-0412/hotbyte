package com.hexaware;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<User> us = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n====== User Menu ======");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Update Password");
            System.out.println("4. Remove User");
            System.out.println("5. Show All Users");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();  

            switch (choice) {
                case 1:
                    System.out.println("Sign In selected");

                    System.out.print("Enter username: ");
                    String unm = sc.nextLine();

                    System.out.print("Enter password: ");
                    String upass = sc.nextLine();

                    boolean flag = false;

                    for (User u : us) {
                        if (u.getUname().equals(unm) && u.getPassd().equals(upass)) {
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        System.out.println("Login Successful");
                    } else {
                        System.out.println("Login Failed ");
                    }

                    break; 

                case 2:
                    System.out.println("Sign Up selected");

                    User u = new User();

                    System.out.print("Enter username: ");
                    u.setUname(sc.nextLine());

                    System.out.print("Enter password: ");
                    u.setPassd(sc.nextLine());

                    System.out.print("Enter email: ");
                    u.setEmail(sc.nextLine());

                    System.out.print("Enter age: ");
                    u.setAge(sc.nextInt());
                    sc.nextLine(); 

                    us.add(u);
                    System.out.println("User added successfully!");

                    break;

                case 3:
                    System.out.println("Update Password selected");

                    System.out.print("Enter your username: ");
                    String unameToUpdate = sc.nextLine();

                    System.out.print("Enter your current password: ");
                    String currentPass = sc.nextLine();

                    boolean updated = false;

                    for (User usr : us) {
                        if (usr.getUname().equals(unameToUpdate) && usr.getPassd().equals(currentPass)) {
                            System.out.print("Enter new password: ");
                            String newPass = sc.nextLine();
                            usr.setPassd(newPass);
                            updated = true;
                            System.out.println("Password updated successfully.");
                            break;
                        }
                    }

                    if (!updated) {
                        System.out.println("Not updated ");
                    }

                case 4:
                	 	System.out.println("Remove User selected");

                	    System.out.print("Enter your username: ");
                	    String unameToRemove = sc.nextLine();

                	    System.out.print("Enter your password: ");
                	    String passToRemove = sc.nextLine();

                	    boolean removed = false;

                	    for (int i = 0; i < us.size(); i++) {
                	        User usr = us.get(i);
                	        if (usr.getUname().equals(unameToRemove) && usr.getPassd().equals(passToRemove)) {
                	            us.remove(i);
                	            removed = true;
                	            System.out.println("User removed successfully.");
                	            break;
                	        }
                	    }

                	    if (!removed) {
                	        System.out.println("Not removed");
                	    }

                	    break;

                case 5:
                    System.out.println("Show All Users selected");
                    for (User obj : us) {
                        System.out.println(obj.toString());
                    }
                    break;

                case 6:			
                    System.out.println("Exiting the application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}
