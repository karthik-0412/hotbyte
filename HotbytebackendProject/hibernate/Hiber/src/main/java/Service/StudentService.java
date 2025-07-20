package Service;

import Dao.StudentDao;
import Dao.StudentDaoImpl;
import Model.Student;

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

    public void searchByName(){
        System.out.print("enter name:");
        String name = sc.next();
        dao.searchByName(name);
    }


}
