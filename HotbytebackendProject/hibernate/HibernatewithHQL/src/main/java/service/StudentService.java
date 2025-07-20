package service;

import Dao.StudentDao;
import Dao.StudentDaoImpl;
import model.Student;

import java.util.Scanner;

public class StudentService {
    Student s;
    StudentDao dao;

    Scanner sc;

    public StudentService() {

        s= new Student();
        sc=new Scanner(System.in);
        dao= new StudentDaoImpl();
    }

    public void saveService() {
        System.out.print("enter rollno:");
        s.setRollno(sc.nextInt());

        System.out.print("enter name:");
        sc.nextLine();
        s.setName(sc.nextLine());

        System.out.print("enter marks:");
        s.setMarks(sc.nextDouble());

        dao.saveData(s);

    }
    public void saveRemove() {
        System.out.print("enter rollno:");
        int roll = sc.nextInt();

        dao.removeByRoll(roll);
    }
    public void nsaveRemove() {
        System.out.print("enter rollno:");
        int roll = sc.nextInt();

        dao.nremoveByRoll(roll);
    }
    public void namesaveRemove() {
        System.out.print("enter rollno:");
        int roll = sc.nextInt();

        dao.nameremoveByRoll(roll);
    }
    public  void saveUpdate(){
        System.out.print("Enter roll number to update: ");
        int roll = sc.nextInt();

        sc.nextLine(); // consume leftover newline
        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        System.out.print("Enter new marks: ");
        double marks = sc.nextDouble();

        dao.updateByRoll(roll, name, marks);
    }
    public void searchByRoll(){
        System.out.print("enter rollno:");
        int roll = sc.nextInt();
        dao.searchByRoll(roll);
    }
    public void showData(){
        dao.ShowData();
    }
    public void nshowData(){
        dao.nShowData();
    }
    public void nameshowData(){
        dao.nameShowData();
    }

    public void searchByName(){
        System.out.print("enter name:");
        String name = sc.nextLine();
        dao.searchByName(name);
    }
    public void namesearchByName(){
        System.out.print("enter name:");
        String name = sc.nextLine();
        dao.namesearchByName(name);
    }
    public void searchByNameMarks(){
        System.out.print("enter name:");
        String name = sc.nextLine();
        System.out.println("enter marks: ");
        double marks = sc.nextDouble();
        dao.searchByNameMark(name,marks);
    }
    public void searchByNamegtMarks(){
//        System.out.print("enter name:");
//        String name = sc.nextLine();
        System.out.println("enter marks: ");
        double marks = sc.nextDouble();
        dao.searchByNamegtMark(marks);
    }
    public void removebyRollNo(){
        System.out.print("enter rollno:");
        int roll = sc.nextInt();
        dao.removeByRollNo(roll);
    }
    public  void saveUpdateByRoll(){
        System.out.print("Enter roll number to update: ");
        int roll = sc.nextInt();

        sc.nextLine(); // consume leftover newline
        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        dao.updateByRollNo( name,roll);
    }
    public  void nsaveUpdateByRoll(){
        System.out.print("Enter roll number to update: ");
        int roll = sc.nextInt();

        sc.nextLine(); // consume leftover newline
        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        dao.nupdateByRollNo(name,roll);
    }

}

