package loginsystem.LoginManagement;

import java.util.List;
import java.util.Scanner;

import dao.LoginDao;
import dao.LoginDaoImpl;
import model.Login;

public class App 
{
    public static void main( String[] args )
    {
    	LoginDao dao = new LoginDaoImpl();
    	Scanner sc = new Scanner(System.in);
    	 while (true) {
             System.out.println(" Login Management System ");
             System.out.println("1. Sign Up");
             System.out.println("2. Forgot Password");
             System.out.println("3. Sign In");
             System.out.println("4. List All Users");
             System.out.println("5. Exit");
             System.out.print("Enter your choice: ");

             int choice = sc.nextInt();
             sc.nextLine(); 
             switch (choice) {
                 case 1:
                    
                     System.out.print("Enter User ID: ");
                     int userId = sc.nextInt();
                     sc.nextLine();
                     System.out.print("Enter Name: ");
                     String name = sc.nextLine();
                     System.out.print("Enter Email: ");
                     String email = sc.nextLine();
                     System.out.print("Enter Password: ");
                     String password = sc.nextLine();

                     Login newUser = new Login(userId, name, email, password);
                     if (dao.signUp(newUser)) {
                         System.out.println("Sign Up Successful!");
                     } else {
                         System.out.println("Sign Up Failed.");
                     }
                     break;

                 case 2:
                     
                     System.out.print("Enter User ID: ");
                     int forgotId = sc.nextInt();
                     sc.nextLine();
                     System.out.print("Enter Email: ");
                     String forgotEmail = sc.nextLine();

                     String retrievedPassword = dao.forgotPassword(forgotId, forgotEmail);
                     if (retrievedPassword != null) {
                         System.out.println("Your password is: " + retrievedPassword);
                     } else {
                         System.out.println("No matching record found.");
                     }
                     break;

                 case 3:
                     
                     System.out.print("Enter User ID: ");
                     int signInId = sc.nextInt();
                     sc.nextLine();
                     System.out.print("Enter Password: ");
                     String signInPassword = sc.nextLine();

                     Login loggedInUser = dao.signIn(signInId, signInPassword);
                     if (loggedInUser != null) {
                         System.out.println("Welcome, " + loggedInUser.getName() + "!");
                     } else {
                         System.out.println("Invalid credentials.");
                     }
                     break;

                 case 4:

                     List<Login> users = dao.getAllUsers();
                     System.out.println("\nRegistered Users:");
                     for (Login user : users) {
                         System.out.println("User ID: " + user.getUserId() + ", Name: " + user.getName());
                     }
                     break;

                 case 5:
                     
                     System.out.println("Exiting. Goodbye!");
                     sc.close();
                     System.exit(0);

                 default:
                     System.out.println("Invalid choice.");
             }
         }
     }
        
    
}
