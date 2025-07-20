package Dao;

import model.Student;

public interface StudentDao {
    void saveData(Student s);
    void removeByRoll(int rno);
    void updateByRoll(int rno,String name,double marks);
    void searchByRoll(int rno);
    void ShowData();
    void searchByName(String name);
    void searchByNameMark(String name,double marks);
    void searchByNamegtMark(double marks);
    void removeByRollNo(int rollno);
    void updateByRollNo(String name,int roll);
    void nShowData();
    void nremoveByRoll(int rno);
    void nupdateByRollNo(String name,int roll);
    void nameShowData();
    void nameremoveByRoll(int rno);
    void namesearchByName(String name);
}
